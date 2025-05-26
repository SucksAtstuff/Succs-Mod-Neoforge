package net.succ.succsmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SuccsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Register models for gems
        basicItem(ModItems.ATHERIUM.get());
        basicItem(ModItems.RUBY.get());
        basicItem(ModItems.SAPPHIRE.get());
        basicItem(ModItems.SUNSTONE.get());
        basicItem(ModItems.MALACHITE.get());

        // Register models for dirty gems
        basicItem(ModItems.DIRTY_ATHERIUM.get());
        basicItem(ModItems.DIRTY_RUBY.get());
        basicItem(ModItems.DIRTY_SAPPHIRE.get());
        basicItem(ModItems.DIRTY_SUNSTONE.get());
        basicItem(ModItems.DIRTY_MALACHITE.get());

        // Register models for other items
        basicItem(ModItems.GOLD_HANDLE.get());
        basicItem(ModItems.GARLIC.get());
        basicItem(ModItems.GARLIC_BREAD.get());

    }
}
