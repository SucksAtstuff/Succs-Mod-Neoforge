package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SuccsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Pickaxe mineable ores
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SUNSTONE_ORE.get())
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.MALACHITE_ORE.get())
                .add(ModBlocks.ATHERIUM_ORE.get());

        // Paxel mineable inherits pickaxe
        tag(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE);

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.SUNSTONE_ORE.get())
                .add(ModBlocks.SAPPHIRE_ORE.get());

        tag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .add(ModBlocks.RUBY_ORE.get());

        tag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .add(ModBlocks.RUBY_ORE.get());

        tag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .add(ModBlocks.MALACHITE_ORE.get());

        tag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .add(ModBlocks.ATHERIUM_ORE.get());


        // Atherium is the highest tier, so it doesn't need any other tool


        // Incorrect tool enforcement
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL);


        // Malachite and Atherium are the highest tiers, so they don't have incorrect tags

    }


}
