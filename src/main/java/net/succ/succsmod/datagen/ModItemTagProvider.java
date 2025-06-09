package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, SuccsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.POLISHABLE_GEMS)
                .add(ModItems.DIRTY_ATHERIUM.get())
                .add(ModItems.DIRTY_RUBY.get())
                .add(ModItems.DIRTY_SAPPHIRE.get())
                .add(ModItems.DIRTY_SUNSTONE.get())
                .add(ModItems.DIRTY_MALACHITE.get());

        tag(ModTags.Items.SUNSTONE_SAPPHIRE_TOOLS)
                .add(ModItems.SUNSTONE_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get())
                .add(ModItems.SUNSTONE_HAMMER.get())
                .add(ModItems.SAPPHIRE_HAMMER.get())
                .add(ModItems.SUNSTONE_PAXEL.get())
                .add(ModItems.SAPPHIRE_PAXEL.get());


        // Add the tools to the regular tool tags
        tag(ItemTags.SWORDS)
                .add(ModItems.ATHERIUM_SWORD.get())
                .add(ModItems.RUBY_SWORD.get())
                .add(ModItems.MALACHITE_SWORD.get())
                .add(ModItems.SUNSTONE_SWORD.get())
                .add(ModItems.SAPPHIRE_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.ATHERIUM_PICKAXE.get())
                .add(ModItems.RUBY_PICKAXE.get())
                .add(ModItems.MALACHITE_PICKAXE.get())
                .add(ModItems.SUNSTONE_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(ModItems.ATHERIUM_AXE.get())
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.MALACHITE_AXE.get())
                .add(ModItems.SUNSTONE_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.ATHERIUM_SHOVEL.get())
                .add(ModItems.RUBY_SHOVEL.get())
                .add(ModItems.MALACHITE_SHOVEL.get())
                .add(ModItems.SUNSTONE_SHOVEL.get())
                .add(ModItems.SAPPHIRE_SHOVEL.get());
        tag(ItemTags.HOES)
                .add(ModItems.ATHERIUM_HOE.get())
                .add(ModItems.RUBY_HOE.get())
                .add(ModItems.MALACHITE_HOE.get())
                .add(ModItems.SUNSTONE_HOE.get())
                .add(ModItems.SAPPHIRE_HOE.get());

        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.GARLIC.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ATHERIUM_HELMET.get())
                .add(ModItems.ATHERIUM_CHESTPLATE.get())
                .add(ModItems.ATHERIUM_LEGGINGS.get())
                .add(ModItems.ATHERIUM_BOOTS.get())
                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.RUBY_BOOTS.get())
                .add(ModItems.MALACHITE_HELMET.get())
                .add(ModItems.MALACHITE_CHESTPLATE.get())
                .add(ModItems.MALACHITE_LEGGINGS.get())
                .add(ModItems.MALACHITE_BOOTS.get())
                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get())
                .add(ModItems.SAPPHIRE_BOOTS.get())
                .add(ModItems.SUNSTONE_HELMET.get())
                .add(ModItems.SUNSTONE_CHESTPLATE.get())
                .add(ModItems.SUNSTONE_LEGGINGS.get())
                .add(ModItems.SUNSTONE_BOOTS.get());

        tag(ModTags.Items.HAMMER_ENCHANTABLE)
                .add(ModItems.SUNSTONE_HAMMER.get())
                .add(ModItems.SAPPHIRE_HAMMER.get())
                .add(ModItems.ATHERIUM_HAMMER.get())
                .add(ModItems.RUBY_HAMMER.get())
                .add(ModItems.MALACHITE_HAMMER.get());
    }


}
