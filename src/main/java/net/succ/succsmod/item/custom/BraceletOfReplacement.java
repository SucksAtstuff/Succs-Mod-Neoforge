package net.succ.succsmod.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.succ.succsmod.item.ModDataComponents;
import net.succ.succsmod.network.ModNetwork;

import java.util.List;
import java.util.Set;

/**
 * Bracelet of Replacement:
 *  - Reads the saved position (from Bracelet of Displacement) out of the
 *    SAVED_POSITION data component.
 *  - Teleports the player back to that exact position and dimension.
 *  - Clears the saved position after use.
 *  - Has a cooldown, synced to client for HUD display.
 */
public class BraceletOfReplacement extends Item {

    public BraceletOfReplacement(Properties properties) {
        super(properties);
    }

    /**
     * Vanilla right-click entry point.
     * Keybind packet should call {@link #activate(ItemStack, ServerPlayer)} directly.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (level.isClientSide) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        ServerPlayer serverPlayer = (ServerPlayer) player;
        ItemStack stack = player.getItemInHand(hand);

        activate(stack, serverPlayer);

        return InteractionResultHolder.success(stack);
    }

    /**
     * Core teleport logic used by:
     *  - right-click
     *  - keybind packet (UseReplacementPacket)
     *
     * @param stack  the ItemStack that holds the stored position
     * @param player server-side player
     */
    public void activate(ItemStack stack, ServerPlayer player) {

        // Respect cooldown server-side so packets can't bypass it.
        if (player.getCooldowns().isOnCooldown(this)) {
            player.displayClientMessage(
                    Component.literal("§cBracelet is recharging..."),
                    true
            );
            return;
        }

        // Read the stored position data from the data component.
        CompoundTag tag = stack.get(ModDataComponents.SAVED_POSITION.get());

        if (tag == null || !tag.getBoolean("has_saved_position")) {
            player.displayClientMessage(
                    Component.literal("No saved position!"),
                    true
            );
            return;
        }

        double x = tag.getDouble("saved_x");
        double y = tag.getDouble("saved_y");
        double z = tag.getDouble("saved_z");
        String dimStr = tag.getString("saved_dimension");

        if (dimStr == null || dimStr.isEmpty()) {
            player.displayClientMessage(
                    Component.literal("Invalid saved dimension!"),
                    true
            );
            return;
        }

        // Parse the dimension string into a ResourceKey<Level>.
        ResourceLocation dimID = ResourceLocation.parse(dimStr);
        ResourceKey<Level> targetKey =
                ResourceKey.create(net.minecraft.core.registries.Registries.DIMENSION, dimID);

        // Find the target level on the server.
        ServerLevel targetLevel = player.server.getLevel(targetKey);

        if (targetLevel == null) {
            player.displayClientMessage(
                    Component.literal("Target dimension does not exist!"),
                    true
            );
            return;
        }

        // Teleport the player back to the saved position.
        player.teleportTo(
                targetLevel,
                x, y, z,
                Set.of(),                     // no relative flags
                player.getYRot(),
                player.getXRot()
        );

        // Clear the saved data from this bracelet so it can't be reused
        // without a new displacement.
        stack.remove(ModDataComponents.SAVED_POSITION.get());

        // Apply cooldown and sync to client so HUD shows it.
        int cooldownTicks = 300; // 15 seconds, a bit longer than displacement
        player.getCooldowns().addCooldown(this, cooldownTicks);
        ModNetwork.sendCooldownToClient(player, this, cooldownTicks);
    }

    /**
     * Tooltip that shows the saved position, if any.
     */
    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext context,
                                List<Component> tooltip,
                                TooltipFlag flag) {

        // Read saved data from the data component, NOT raw NBT.
        CompoundTag tag = stack.get(ModDataComponents.SAVED_POSITION.get());

        if (tag == null || !tag.getBoolean("has_saved_position")) {
            tooltip.add(Component.literal("§7Saved Position: §c<none>"));
            return;
        }

        int x = (int) tag.getDouble("saved_x");
        int y = (int) tag.getDouble("saved_y");
        int z = (int) tag.getDouble("saved_z");
        String dim = tag.getString("saved_dimension");
        if (dim.isEmpty()) dim = "unknown";

        tooltip.add(Component.literal("§7Saved Position:"));
        tooltip.add(Component.literal(
                "  §eX:§f " + x +
                        "  §eY:§f " + y +
                        "  §eZ:§f " + z
        ));
        tooltip.add(Component.literal("  §eDim:§f " + dim));
    }
}
