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

        // Add Atherium block tags to the "mineable with pickaxe" tag
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ATHERIUM_BLOCK.get())
                .add(ModBlocks.ATHERIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get())
                .add(ModBlocks.NETHER_ATHERIUM_ORE.get())
                .add(ModBlocks.END_ATHERIUM_ORE.get());

        // Add Ruby block tags to the "mineable with pickaxe" tag
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RUBY_BLOCK.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE.get())
                .add(ModBlocks.NETHER_RUBY_ORE.get())
                .add(ModBlocks.END_RUBY_ORE.get());

        // Add Sunstone block tags to the "mineable with pickaxe" tag
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SUNSTONE_BLOCK.get())
                .add(ModBlocks.SUNSTONE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get())
                .add(ModBlocks.NETHER_SUNSTONE_ORE.get())
                .add(ModBlocks.END_SUNSTONE_ORE.get());

        // Add Malachite block tags to the "mineable with pickaxe" tag
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MALACHITE_BLOCK.get())
                .add(ModBlocks.MALACHITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MALACHITE_ORE.get())
                .add(ModBlocks.NETHER_MALACHITE_ORE.get())
                .add(ModBlocks.END_MALACHITE_ORE.get());

        // Add Sapphire block tags to the "mineable with pickaxe" tag
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(ModBlocks.NETHER_SAPPHIRE_ORE.get())
                .add(ModBlocks.END_SAPPHIRE_ORE.get());

        // Add Sapphire blocks to the "needs Netherite tool" tag
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(ModBlocks.NETHER_SAPPHIRE_ORE.get())
                .add(ModBlocks.END_SAPPHIRE_ORE.get());

        // Add Sunstone blocks to the "needs Netherite tool" tag
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.SUNSTONE_BLOCK.get())
                .add(ModBlocks.SUNSTONE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get())
                .add(ModBlocks.NETHER_SUNSTONE_ORE.get())
                .add(ModBlocks.END_SUNSTONE_ORE.get());

        // Add Atherium blocks to the "needs Malachite tool" tag
        tag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .add(ModBlocks.ATHERIUM_BLOCK.get())
                .add(ModBlocks.ATHERIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get())
                .add(ModBlocks.NETHER_ATHERIUM_ORE.get())
                .add(ModBlocks.END_ATHERIUM_ORE.get());

        // Add Malachite blocks to the "needs Ruby tool" tag
        tag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .add(ModBlocks.MALACHITE_BLOCK.get())
                .add(ModBlocks.MALACHITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MALACHITE_ORE.get())
                .add(ModBlocks.NETHER_MALACHITE_ORE.get())
                .add(ModBlocks.END_MALACHITE_ORE.get());

        // Add Ruby blocks and ores to a custom tag that requires Sunstone or Sapphire tools
        tag(ModTags.Blocks.MINEABLE_WITH_SUNSTONE_OR_SAPPHIRE)
                .add(ModBlocks.RUBY_BLOCK.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE.get())
                .add(ModBlocks.NETHER_RUBY_ORE.get())
                .add(ModBlocks.END_RUBY_ORE.get());

        // Blocks Sunstone should NOT be able to mine
        tag(ModTags.Blocks.INCORRECT_FOR_SUNSTONE_TOOL)
                .add(ModBlocks.MALACHITE_BLOCK.get())
                .add(ModBlocks.MALACHITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MALACHITE_ORE.get())
                .add(ModBlocks.NETHER_MALACHITE_ORE.get())
                .add(ModBlocks.END_MALACHITE_ORE.get())
                .add(ModBlocks.ATHERIUM_BLOCK.get())
                .add(ModBlocks.ATHERIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get())
                .add(ModBlocks.NETHER_ATHERIUM_ORE.get())
                .add(ModBlocks.END_ATHERIUM_ORE.get())
                .remove(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .remove(ModTags.Blocks.MINEABLE_WITH_SUNSTONE_OR_SAPPHIRE); // if you want to clear Ruby access too

        // Blocks Sapphire should NOT be able to mine
        tag(ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.INCORRECT_FOR_SUNSTONE_TOOL)
                .remove(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL);

        // Blocks Ruby should NOT be able to mine
        tag(ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL)
                .add(ModBlocks.ATHERIUM_BLOCK.get())
                .add(ModBlocks.ATHERIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get())
                .add(ModBlocks.NETHER_ATHERIUM_ORE.get())
                .add(ModBlocks.END_ATHERIUM_ORE.get())
                .remove(ModTags.Blocks.NEEDS_RUBY_TOOL);

        // Blocks Netherite should NOT be able to mine
        this.tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .add(ModBlocks.ATHERIUM_BLOCK.get())
                .add(ModBlocks.ATHERIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get())
                .add(ModBlocks.NETHER_ATHERIUM_ORE.get())
                .add(ModBlocks.END_ATHERIUM_ORE.get())
                .remove(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

    }
}
