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

        // Base ranges (Reinforced should return baseDepth = 0 so default is 3x3x3)
        int baseWidth = getBaseWidthRange(stack, null);
        int baseDepth = getBaseDepthRange(stack, null);

        // Effective width (radius) = base + Widening
        int widthRange = Math.max(0, baseWidth + widenLvl);
        int side = 2 * widthRange + 1;

        int third;
        if (this.minesVolume(stack, null)) {
            // Volume (reinforced): base is a cube with edge "side"
            // Only the Depth ENCHANT adds extra along the hit axis
            third = side + 2 * Math.max(0, depthLvl);
        } else {
            // Plane (normal): thickness is baseDepth + depthLvl
            third = 2 * Math.max(0, baseDepth + depthLvl) + 1;
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

    /** Volume mode: base cube (widthRange) + extension along hit face (depthRange). */
    public static List<BlockPos> getVolumeTargets(ServerPlayer player, BlockPos origin, int widthRange, int depthRange) {
        List<BlockPos> out = new ArrayList<>();
        // base cube
        for (int dx = -widthRange; dx <= widthRange; dx++)
            for (int dy = -widthRange; dy <= widthRange; dy++)
                for (int dz = -widthRange; dz <= widthRange; dz++)
                    out.add(origin.offset(dx, dy, dz));

        // add extra slices along the hit face
        BlockHitResult hit = player.level().clip(new ClipContext(
                player.getEyePosition(1f),
                player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f)),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if (hit.getType() == HitResult.Type.MISS || depthRange <= 0) return out;

        Direction face = hit.getDirection();
        if (face.getAxis() == Direction.Axis.X) {
            for (int sign : new int[]{-1, 1})
                for (int ex = widthRange + 1; ex <= widthRange + depthRange; ex++)
                    for (int dy = -widthRange; dy <= widthRange; dy++)
                        for (int dz = -widthRange; dz <= widthRange; dz++)
                            out.add(origin.offset(sign * ex, dy, dz));
        } else if (face.getAxis() == Direction.Axis.Y) {
            for (int sign : new int[]{-1, 1})
                for (int ey = widthRange + 1; ey <= widthRange + depthRange; ey++)
                    for (int dx = -widthRange; dx <= widthRange; dx++)
                        for (int dz = -widthRange; dz <= widthRange; dz++)
                            out.add(origin.offset(dx, sign * ey, dz));
        } else { // Z
            for (int sign : new int[]{-1, 1})
                for (int ez = widthRange + 1; ez <= widthRange + depthRange; ez++)
                    for (int dx = -widthRange; dx <= widthRange; dx++)
                        for (int dy = -widthRange; dy <= widthRange; dy++)
                            out.add(origin.offset(dx, dy, sign * ez));
        }
        return out;
    }
}
