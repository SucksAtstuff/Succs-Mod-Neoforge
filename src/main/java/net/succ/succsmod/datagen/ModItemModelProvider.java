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

        // Register models for atherium tools
        handheldItem(ModItems.ATHERIUM_SWORD.get());
        handheldItem(ModItems.ATHERIUM_PICKAXE.get());
        handheldItem(ModItems.ATHERIUM_AXE.get());
        handheldItem(ModItems.ATHERIUM_SHOVEL.get());
        handheldItem(ModItems.ATHERIUM_HOE.get());

        // Register models for ruby tools
        handheldItem(ModItems.RUBY_SWORD.get());
        handheldItem(ModItems.RUBY_PICKAXE.get());
        handheldItem(ModItems.RUBY_AXE.get());
        handheldItem(ModItems.RUBY_SHOVEL.get());
        handheldItem(ModItems.RUBY_HOE.get());

        // Register models for sapphire tools
        handheldItem(ModItems.SAPPHIRE_SWORD.get());
        handheldItem(ModItems.SAPPHIRE_PICKAXE.get());
        handheldItem(ModItems.SAPPHIRE_AXE.get());
        handheldItem(ModItems.SAPPHIRE_SHOVEL.get());
        handheldItem(ModItems.SAPPHIRE_HOE.get());

        // Register models for sunstone tools
        handheldItem(ModItems.SUNSTONE_SWORD.get());
        handheldItem(ModItems.SUNSTONE_PICKAXE.get());
        handheldItem(ModItems.SUNSTONE_AXE.get());
        handheldItem(ModItems.SUNSTONE_SHOVEL.get());
        handheldItem(ModItems.SUNSTONE_HOE.get());

        // Register models for malachite tools
        handheldItem(ModItems.MALACHITE_SWORD.get());
        handheldItem(ModItems.MALACHITE_PICKAXE.get());
        handheldItem(ModItems.MALACHITE_AXE.get());
        handheldItem(ModItems.MALACHITE_SHOVEL.get());
        handheldItem(ModItems.MALACHITE_HOE.get());


    }
}
