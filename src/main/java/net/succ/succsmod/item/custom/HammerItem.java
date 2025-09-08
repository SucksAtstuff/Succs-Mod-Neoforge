package net.succ.succsmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.succ.succsmod.enchant.ModEnchants;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    /** Plane by default; ReinforcedHammerItem overrides to return true. */
    public boolean minesVolume(ItemStack stack, Player player) {
        return false;
    }

    /** Base radius (1 => 3x3 plane or 3x3x3 cube). */
    public int getBaseWidthRange(ItemStack stack, Player player) {
        return 1;
    }

    /** Base depth (0 => 1 layer thick; 1 => 3 layers, etc.). */
    public int getBaseDepthRange(ItemStack stack, Player player) {
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext ctx,
                                java.util.List<Component> tooltip,
                                TooltipFlag flag) {
        // Registry lookup via TooltipContext (1.21.1 style)
        HolderLookup.RegistryLookup<Enchantment> reg =
                ctx.registries().lookupOrThrow(Registries.ENCHANTMENT);

        Holder<Enchantment> widening = reg.getOrThrow(ModEnchants.WIDENING);
        Holder<Enchantment> depth    = reg.getOrThrow(ModEnchants.DEPTH);

        int widenLvl = EnchantmentHelper.getTagEnchantmentLevel(widening, stack);
        int depthLvl = EnchantmentHelper.getTagEnchantmentLevel(depth, stack);

        // Base ranges (Reinforced returns baseDepth = 1 so default is 3x3x3)
        int baseWidth = getBaseWidthRange(stack, null);
        int baseDepth = getBaseDepthRange(stack, null);

        // Effective radii
        int widthRange = Math.max(0, baseWidth + widenLvl);
        int depthRange = Math.max(0, baseDepth + depthLvl);

        // Visualized size (width × height × depth)
        int side = 2 * widthRange + 1;
        int third;

        if (this.minesVolume(stack, null)) {
            // Volume (reinforced): DEPTH is independent of widening
            // thickness = 2 * (baseDepth + depthLvl) + 1
            third = 2 * depthRange + 1;
        } else {
            // Plane (normal): thickness is baseDepth + depthLvl
            third = 2 * depthRange + 1;
        }

        tooltip.add(Component.translatable("tooltip.succsessentials.area", side, side, third)
                .withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, ctx, tooltip, flag);
    }

    /** Radius expansion from Widening. */
    public int getWidthRange(ItemStack stack, Player player) {
        int base = getBaseWidthRange(stack, player);
        HolderLookup.RegistryLookup<Enchantment> reg =
                player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> widening = reg.getOrThrow(ModEnchants.WIDENING);
        int widenLvl = EnchantmentHelper.getTagEnchantmentLevel(widening, stack);
        return base + Math.max(0, widenLvl);
    }

    /** Depth expansion from Depth. */
    public int getDepthRange(ItemStack stack, Player player) {
        int base = getBaseDepthRange(stack, player);
        HolderLookup.RegistryLookup<Enchantment> reg =
                player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> depth = reg.getOrThrow(ModEnchants.DEPTH);
        int depthLvl = EnchantmentHelper.getTagEnchantmentLevel(depth, stack);
        return base + Math.max(0, depthLvl);
    }

    /** Plane mode: break a square aligned to the face (width × width × depth). */
    public static List<BlockPos> getPlaneTargets(ServerPlayer player, BlockPos origin, int widthRange, int depthRange) {
        List<BlockPos> out = new ArrayList<>();
        BlockHitResult hit = player.level().clip(new ClipContext(
                player.getEyePosition(1f),
                player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f)),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if (hit.getType() == HitResult.Type.MISS) return out;
        Direction face = hit.getDirection();

        int min = -widthRange, max = widthRange;

        if (face == Direction.UP || face == Direction.DOWN) {
            // X-Z plane; depth along Y
            for (int dx = min; dx <= max; dx++)
                for (int dz = min; dz <= max; dz++)
                    for (int dy = -depthRange; dy <= depthRange; dy++)
                        out.add(origin.offset(dx, dy, dz));
        } else if (face == Direction.NORTH || face == Direction.SOUTH) {
            // X-Y plane; depth along Z
            for (int dx = min; dx <= max; dx++)
                for (int dy = min; dy <= max; dy++)
                    for (int dz = -depthRange; dz <= depthRange; dz++)
                        out.add(origin.offset(dx, dy, dz));
        } else { // EAST/WEST: Y-Z plane; depth along X
            for (int dz = min; dz <= max; dz++)
                for (int dy = min; dy <= max; dy++)
                    for (int dx = -depthRange; dx <= depthRange; dx++)
                        out.add(origin.offset(dx, dy, dz));
        }
        return out;
    }

    /**
     * Volume mode:
     * Build a base prism using widthRange on the 2 perpendicular axes,
     * and ONLY the *base depth* on the face axis, then extend along the face by (totalDepthRange - baseDepthRange).
     *
     * @param widthRange          radius for width/height (includes Widening)
     * @param baseDepthRange      base depth half-thickness (without Depth enchant)
     * @param totalDepthRange     baseDepthRange + Depth levels
     */
    public static List<BlockPos> getVolumeTargets(ServerPlayer player, BlockPos origin, int widthRange, int baseDepthRange, int totalDepthRange) {
        List<BlockPos> out = new ArrayList<>();

        BlockHitResult hit = player.level().clip(new ClipContext(
                player.getEyePosition(1f),
                player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f)),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if (hit.getType() == HitResult.Type.MISS) return out;

        Direction face = hit.getDirection();
        Direction.Axis axis = face.getAxis();

        // 1) Base prism: depth uses *baseDepthRange*, not widthRange
        for (int a = -widthRange; a <= widthRange; a++) {
            for (int b = -widthRange; b <= widthRange; b++) {
                for (int c = -baseDepthRange; c <= baseDepthRange; c++) {
                    int dx = 0, dy = 0, dz = 0;
                    switch (axis) {
                        case X -> { dy = a; dz = b; dx = face.getAxisDirection().getStep() * c; }
                        case Y -> { dx = a; dz = b; dy = face.getAxisDirection().getStep() * c; }
                        case Z -> { dx = a; dy = b; dz = face.getAxisDirection().getStep() * c; }
                    }
                    out.add(origin.offset(dx, dy, dz));
                }
            }
        }

        // 2) Extra slices for Depth enchant beyond the base thickness
        int extraDepth = Math.max(0, totalDepthRange - baseDepthRange);
        if (extraDepth <= 0) return out;

        for (int extra = baseDepthRange + 1; extra <= baseDepthRange + extraDepth; extra++) {
            for (int a = -widthRange; a <= widthRange; a++) {
                for (int b = -widthRange; b <= widthRange; b++) {
                    int dx = 0, dy = 0, dz = 0;
                    switch (axis) {
                        case X -> { dy = a; dz = b; dx = face.getAxisDirection().getStep() * extra; }
                        case Y -> { dx = a; dz = b; dy = face.getAxisDirection().getStep() * extra; }
                        case Z -> { dx = a; dy = b; dz = face.getAxisDirection().getStep() * extra; }
                    }
                    out.add(origin.offset(dx, dy, dz));
                }
            }
        }

        return out;
    }
}
