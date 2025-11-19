package net.succ.succsmod.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

import java.util.Map;

public class ModdablePickaxeItem extends PickaxeItem {

    // Same system as the sword: registry keys + levels for enchantments
    // applied only when crafted.
    private final Map<ResourceKey<Enchantment>, Integer> enchants;

    public ModdablePickaxeItem(Tier tier,
                               Properties props,
                               Map<ResourceKey<Enchantment>, Integer> enchants) {
        super(tier, props);
        this.enchants = enchants;
    }

    /**
     * Applies all configured enchantments to this item using the provided
     * registry access. Called only when crafting.
     */
    private void applyEnchants(ItemStack stack, RegistryAccess access) {
        var enchantRegistry = access.registryOrThrow(Registries.ENCHANTMENT);

        enchants.forEach((key, level) -> {
            Holder<Enchantment> holder = enchantRegistry.getHolderOrThrow(key);
            stack.enchant(holder, level);
        });
    }

    /**
     * Immediately after crafting, we apply the enchantments using the
     * fully-available registry system.
     */
    @Override
    public void onCraftedPostProcess(ItemStack stack, Level level) {
        super.onCraftedPostProcess(stack, level);
        applyEnchants(stack, level.registryAccess());
    }

    /**
     * We do NOT reapply enchantments after load — grindstone removal
     * or other modifications should persist.
     */
    @Override
    public void verifyComponentsAfterLoad(ItemStack stack) {
        super.verifyComponentsAfterLoad(stack);
        // Keep empty to avoid forcing enchantments back onto the item.
    }

    /**
     * We avoid reapplying enchantments here as well to ensure players
     * can freely modify the item after crafting.
     */
    @Override
    public void inventoryTick(ItemStack stack,
                              Level level,
                              Entity entity,
                              int slot,
                              boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        // No auto-enchant behaviors.
    }

    /**
     * Allows grindstone usage as normal, letting players remove the
     * crafting-applied enchantments if desired.
     */
    @Override
    public boolean canGrindstoneRepair(ItemStack stack) {
        return true;
    }
}
