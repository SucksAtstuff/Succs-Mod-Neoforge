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

public class ModdableSwordItem extends PickaxeItem {

    // Enchantments to apply when crafted: stored as registry keys + levels.
    // Keys are used because registry access is unavailable during static item construction.
    private final Map<ResourceKey<Enchantment>, Integer> enchants;

    public ModdableSwordItem(Tier tier,
                             Properties props,
                             Map<ResourceKey<Enchantment>, Integer> enchants) {
        super(tier, props);
        this.enchants = enchants;
    }

    /**
     * Applies every enchantment stored in the `enchants` map to the given stack.
     * This requires a RegistryAccess (available once items exist in the world).
     */
    private void applyEnchants(ItemStack stack, RegistryAccess access) {
        var enchantRegistry = access.registryOrThrow(Registries.ENCHANTMENT);

        enchants.forEach((key, level) -> {
            Holder<Enchantment> holder = enchantRegistry.getHolderOrThrow(key);
            stack.enchant(holder, level);
        });
    }

    /**
     * Runs immediately after crafting. This is the correct moment to apply
     * enchantments, because the registry is fully available here.
     */
    @Override
    public void onCraftedPostProcess(ItemStack stack, Level level) {
        super.onCraftedPostProcess(stack, level);
        applyEnchants(stack, level.registryAccess());
    }

    /**
     * Called when loading a saved ItemStack. We intentionally do NOT
     * reapply enchantments here — the player may have removed them via grindstone.
     */
    @Override
    public void verifyComponentsAfterLoad(ItemStack stack) {
        super.verifyComponentsAfterLoad(stack);
        // Leave empty to respect saved state of enchantments.
    }

    /**
     * Runs every tick while in the inventory. We avoid reapplying enchantments
     * so they can safely be removed by a grindstone or commands.
     */
    @Override
    public void inventoryTick(ItemStack stack,
                              Level level,
                              Entity entity,
                              int slot,
                              boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        // No reenchanting here by design.
    }

    /**
     * Allows the item to be used in a grindstone normally — the player may
     * choose to remove the pre-applied enchantments.
     */
    @Override
    public boolean canGrindstoneRepair(ItemStack stack) {
        return true;
    }
}
