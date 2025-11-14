package net.succ.succsmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.succ.succsmod.item.ModDataComponents;
import net.succ.succsmod.network.ModNetwork;
import top.theillusivec4.curios.api.CuriosApi;

/**
 * Bracelet of Displacement:
 *  - When activated, teleports the player from the Overworld to the
 *    corresponding Nether coordinates (x / 8, z / 8), trying to find
 *    a safe position.
 *  - Before teleporting, it stores the player's current position
 *    (including dimension) into any Bracelet of Replacement the player
 *    has, preferring Curio slots first, then inventory.
 *  - Has a cooldown so it can't be spammed. Cooldown is also synced
 *    to the client via SyncCooldownPacket for HUD display.
 */
public class BraceletOfDisplacement extends Item {

    public BraceletOfDisplacement(Properties properties) {
        super(properties);
    }

    /**
     * Vanilla right-click entry point.
     * Keybind / packet should call {@link #activate(ItemStack, ServerPlayer)} directly
     * with the correct ItemStack.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Client side just pretends success; real logic happens server-side.
        if (level.isClientSide) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        ServerPlayer serverPlayer = (ServerPlayer) player;
        ItemStack stack = player.getItemInHand(hand);

        // Use the common ability code so right-click and keybind behave the same.
        activate(stack, serverPlayer);

        return InteractionResultHolder.success(stack);
    }

    /**
     * Core ability logic used by:
     *  - right-click in hand
     *  - keybind packet (UseDisplacementPacket)
     *  - Curio slot activation (if you ever call it from there)
     *
     * @param stack  the exact ItemStack representing this bracelet
     * @param player the server-side player
     */
    public void activate(ItemStack stack, ServerPlayer player) {

        Level level = player.level();

        // Respect cooldown on the server so packets can't spam.
        if (player.getCooldowns().isOnCooldown(this)) {
            player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal("§cBracelet is recharging..."),
                    true
            );
            return;
        }

        // Ability is only valid in the Overworld.
        if (!level.dimension().equals(Level.OVERWORLD)) {
            player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal("This bracelet only works in the Overworld."),
                    true
            );
            return;
        }

        // Convert Overworld coordinates to Nether coordinates (vanilla ratio 8:1).
        double newX = player.getX() / 8.0;
        double newZ = player.getZ() / 8.0;

        // Get the Nether world on the server.
        ServerLevel nether = player.server.getLevel(Level.NETHER);
        if (nether == null) {
            return;
        }

        // Try to find a safe teleport location around the target Nether X/Z.
        BlockPos safePos = findSafeTeleportLocation(nether, (int) newX, (int) newZ);

        if (safePos == null) {
            player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal("No safe location found!"),
                    true
            );
            return;
        }

        // Store the *current* position so Replacement can send the player back later.
        savePositionToReplacementBracelet(player);

        // Perform the actual teleport.
        player.teleportTo(
                nether,
                safePos.getX() + 0.5,
                safePos.getY() + 1,
                safePos.getZ() + 0.5,
                player.getYRot(),
                player.getXRot()
        );

        // Apply cooldown and sync it to client for HUD.
        int cooldownTicks = 200; // 10 seconds (20 ticks per second)
        player.getCooldowns().addCooldown(this, cooldownTicks);
        ModNetwork.sendCooldownToClient(player, this, cooldownTicks);
    }

    /**
     * Scans downward for a "safe" teleport location:
     *  - block below is solid and not lava / air
     *  - head and feet spaces are air
     */
    private BlockPos findSafeTeleportLocation(Level nether, int x, int z) {
        // Scan from top down so player is more likely to end up somewhere reasonable.
        for (int y = 120; y > 20; y--) {

            BlockPos pos = new BlockPos(x, y, z);

            boolean headAir = nether.getBlockState(pos).isAir();
            boolean feetAir = nether.getBlockState(pos.above()).isAir();
            boolean solidGround =
                    !nether.getBlockState(pos.below()).is(Blocks.AIR) &&
                            !nether.getBlockState(pos.below()).is(Blocks.LAVA);

            if (headAir && feetAir && solidGround) {
                return pos;
            }
        }

        // No valid spot found in our scan range.
        return null;
    }

    /**
     * Stores the player's current position and dimension into the first
     * {@link BraceletOfReplacement} it finds:
     *  1) Checks all Curio slots
     *  2) If none found, checks the normal inventory
     */
    private void savePositionToReplacementBracelet(Player player) {

        // Build a CompoundTag with position + dimension.
        CompoundTag tag = new CompoundTag();
        tag.putDouble("saved_x", player.getX());
        tag.putDouble("saved_y", player.getY());
        tag.putDouble("saved_z", player.getZ());
        tag.putBoolean("has_saved_position", true);

        ResourceLocation dimLoc = player.level().dimension().location();
        tag.putString("saved_dimension", dimLoc.toString());

        // --------- 1) Try Curio slots first ---------
        CuriosApi.getCuriosInventory(player).ifPresent(inv -> {
            inv.getCurios().forEach((slotType, handler) -> {
                var stacks = handler.getStacks();
                for (int i = 0; i < stacks.getSlots(); i++) {
                    ItemStack s = stacks.getStackInSlot(i);

                    if (s.getItem() instanceof BraceletOfReplacement) {
                        // Save the position into this bracelet's data component.
                        s.set(ModDataComponents.SAVED_POSITION.get(), tag);
                        return;
                    }
                }
            });
        });

        // --------- 2) Fallback: normal inventory ---------
        for (ItemStack s : player.getInventory().items) {
            if (s.getItem() instanceof BraceletOfReplacement) {
                s.set(ModDataComponents.SAVED_POSITION.get(), tag);
                return;
            }
        }
    }
}
