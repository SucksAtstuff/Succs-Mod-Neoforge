package net.succ.succsmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.succ.succsmod.item.ModItems;

/**
 * Renders a 2-icon ability bar showing the cooldown of:
 * - Bracelet of Displacement
 * - Bracelet of Replacement
 *
 * This HUD appears only when the player has the bracelets
 * (in inventory OR Curios slots).
 */
public class BraceletCooldownHud {

    private static final int ICON_SIZE = 16;     // Standard item icon size
    private static final int ICON_PADDING = 4;   // Spacing between icons
    private static final int Y_OFFSET = -40;     // Height above the hotbar

    @SubscribeEvent
    public void onRenderHud(RenderGuiEvent.Post event) {

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;

        // event data
        GuiGraphics gui = event.getGuiGraphics();
        float partialTicks = event.getPartialTick().getGameTimeDeltaPartialTick(false);


        // Find bracelet stacks (from Curios OR inventory)
        ItemStack displacement = findBracelet(player, ModItems.BRACELET_OF_DISPLACEMENT.get());
        ItemStack replacement  = findBracelet(player, ModItems.BRACELET_OF_REPLACEMENT.get());

        // If neither bracelet exists, stop rendering the UI
        if (displacement.isEmpty() && replacement.isEmpty()) return;

        // Screen dimensions
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // Base position (bottom center, slightly above hotbar)
        int baseX = screenWidth / 2;
        int baseY = screenHeight + Y_OFFSET;

        // Calculate icon positions (centered)
        int totalWidth = 2 * ICON_SIZE + ICON_PADDING;
        int startX = baseX - (totalWidth / 2);

        int displacementX = startX;
        int replacementX  = startX + ICON_SIZE + ICON_PADDING;

        // Render each icon if player has the item
        if (!displacement.isEmpty()) {
            renderIcon(gui, player, displacement, displacementX, baseY, partialTicks);
        }

        if (!replacement.isEmpty()) {
            renderIcon(gui, player, replacement, replacementX, baseY, partialTicks);
        }
    }

    /**
     * Renders an item icon and its cooldown overlay.
     */
    private void renderIcon(GuiGraphics gui, LocalPlayer player, ItemStack stack,
                            int x, int y, float partialTicks) {

        // Render item icon
        gui.renderItem(stack, x, y);

        // Query cooldown (0 = ready, 1 = full cooldown)
        double cooldown = player.getCooldowns().getCooldownPercent(stack.getItem(), partialTicks);

        if (cooldown > 0) {

            int overlayHeight = (int) (ICON_SIZE * cooldown);

            RenderSystem.enableBlend();

            // Render semi-transparent black overlay
            gui.fill(
                    x,
                    y + (ICON_SIZE - overlayHeight),
                    x + ICON_SIZE,
                    y + ICON_SIZE,
                    0xAA000000 // 170 alpha black
            );
        }
    }

    /**
     * Searches Curios FIRST, then normal inventory.
     */
    private ItemStack findBracelet(LocalPlayer player, Item item) {

        // 1. Check Curios
        var curios = top.theillusivec4.curios.api.CuriosApi.getCuriosInventory(player);

        if (curios.isPresent()) {
            var inv = curios.get();

            for (var handler : inv.getCurios().values()) {
                var stacks = handler.getStacks();
                for (int i = 0; i < stacks.getSlots(); i++) {
                    ItemStack s = stacks.getStackInSlot(i);
                    if (s.is(item)) return s;
                }
            }
        }

        // 2. Check regular inventory
        for (ItemStack s : player.getInventory().items) {
            if (s.is(item)) return s;
        }

        return ItemStack.EMPTY;
    }
}
