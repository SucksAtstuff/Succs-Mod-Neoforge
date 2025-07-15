package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
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
                .add(ModBlocks.JASPILITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_JASPILITE_ORE.get())
                .add(ModBlocks.NETHER_JASPILITE_ORE.get())
                .add(ModBlocks.END_JASPILITE_ORE.get())

                .add(ModBlocks.SUNSTONE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get())
                .add(ModBlocks.NETHER_SUNSTONE_ORE.get())
                .add(ModBlocks.END_SUNSTONE_ORE.get())

                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(ModBlocks.NETHER_SAPPHIRE_ORE.get())
                .add(ModBlocks.END_SAPPHIRE_ORE.get())

                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE.get())
                .add(ModBlocks.NETHER_RUBY_ORE.get())
                .add(ModBlocks.END_RUBY_ORE.get())

                .add(ModBlocks.MALACHITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MALACHITE_ORE.get())
                .add(ModBlocks.NETHER_MALACHITE_ORE.get())
                .add(ModBlocks.END_MALACHITE_ORE.get())

                .add(ModBlocks.ATHERIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get())
                .add(ModBlocks.NETHER_ATHERIUM_ORE.get())
                .add(ModBlocks.END_ATHERIUM_ORE.get());

        // Paxel mineable inherits pickaxe
        tag(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE);

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.JASPILITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_JASPILITE_ORE.get())
                .add(ModBlocks.NETHER_JASPILITE_ORE.get())
                .add(ModBlocks.END_JASPILITE_ORE.get());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.SUNSTONE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get())
                .add(ModBlocks.NETHER_SUNSTONE_ORE.get())
                .add(ModBlocks.END_SUNSTONE_ORE.get())
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                .add(ModBlocks.NETHER_SAPPHIRE_ORE.get())
                .add(ModBlocks.END_SAPPHIRE_ORE.get());

        tag(ModTags.Blocks.NEEDS_JASPILITE_TOOL);

        tag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE.get())
                .add(ModBlocks.NETHER_RUBY_ORE.get())
                .add(ModBlocks.END_RUBY_ORE.get());

        tag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.DEEPSLATE_RUBY_ORE.get())
                .add(ModBlocks.NETHER_RUBY_ORE.get())
                .add(ModBlocks.END_RUBY_ORE.get());

        tag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .add(ModBlocks.MALACHITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_MALACHITE_ORE.get())
                .add(ModBlocks.NETHER_MALACHITE_ORE.get())
                .add(ModBlocks.END_MALACHITE_ORE.get());

        tag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .add(ModBlocks.ATHERIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get())
                .add(ModBlocks.NETHER_ATHERIUM_ORE.get())
                .add(ModBlocks.END_ATHERIUM_ORE.get());

        // return empty tag for Atherium, as it is the highest tier
        tag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        // Incorrect tool enforcement
        this.tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_JASPILITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_JASPILITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_JASPILITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_JASPILITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_JASPILITE_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_SUNSTONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL)
                .addTag(ModTags.Blocks.NEEDS_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_MALACHITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ATHERIUM_TOOL);

        // Atherium is the highest tier, so it has no incorrect tags
        tag(ModTags.Blocks.INCORRECT_FOR_ATHERIUM_TOOL);

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SHATTERBLOOM_LOG.get())
                .add(ModBlocks.SHATTERBLOOM_WOOD.get())
                .add(ModBlocks.STRIPPED_SHATTERBLOOM_LOG.get())
                .add(ModBlocks.STRIPPED_SHATTERBLOOM_WOOD.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_LOG.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_WOOD.get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.SHATTERBLOOM_BUTTON.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_BUTTON.get());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.SHATTERBLOOM_DOOR.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_DOOR.get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.SHATTERBLOOM_SLAB.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_SLAB.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.SHATTERBLOOM_STAIRS.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_STAIRS.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.SHATTERBLOOM_TRAPDOOR.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_TRAPDOOR.get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.SHATTERBLOOM_PRESSURE_PLATE.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_PRESSURE_PLATE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.SHATTERBLOOM_FENCE.get())
                .add(ModBlocks.SHATTERBLOOM_FENCE_GATE.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_FENCE.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_FENCE_GATE.get());

        tag(BlockTags.FENCES)
                .add(ModBlocks.SHATTERBLOOM_FENCE.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.SHATTERBLOOM_FENCE_GATE.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_FENCE_GATE.get());

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.SHATTERBLOOM_SAPLING.get())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_SAPLING.get());

        tag(BlockTags.CLIMBABLE)
                .add(ModBlocks.MYCELIAL_SPOREWOOD_VINE.get());

    }
}
