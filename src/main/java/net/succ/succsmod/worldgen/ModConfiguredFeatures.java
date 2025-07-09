package net.succ.succsmod.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    // Define ResourceKeys for custom configured features (Atherium ore and Deepslate Atherium ore)
    public static final ResourceKey<ConfiguredFeature<?,?>> END_ATHERIUM_ORE_KEY = registerKey("end_atherium_ore");

    public static final ResourceKey<ConfiguredFeature<?,?>> NETHER_RUBY_ORE_KEY = registerKey("nether_ruby_ore");

    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");

    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_SUNSTONE_ORE_KEY = registerKey("sunstone_ore");

    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_MALACHITE_ORE_KEY = registerKey("malachite_ore");

    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_JASPILITE_ORE_KEY = registerKey("jaspilite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHATTERBLOOM_KEY = registerKey("shatterbloom");


    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_GRASS_KEY = registerKey("patch_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SHATTERGROVE_FLOWERS_KEY = registerKey("patch_shattergrove_flowers");





    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        // Define RuleTests for blocks that can be replaced by ores
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplacables = new BlockMatchTest(Blocks.END_STONE);

        // Define target block states for Atherium ores
        List<OreConfiguration.TargetBlockState> endAtheriumOres = List.of(
                OreConfiguration.target(endReplacables, ModBlocks.END_ATHERIUM_ORE.get().defaultBlockState())
        );

        // Register End Atherium ore configured feature
        register(context, END_ATHERIUM_ORE_KEY, Feature.ORE, new OreConfiguration(endAtheriumOres, 5));

        // Define target block states for Sapphire ores
        List<OreConfiguration.TargetBlockState> overworldSapphireOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())
        );

        // Register Overworld Sapphire ore configured feature
        register(context, OVERWORLD_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 10));

        // Define target block states for Sunstone ores
        List<OreConfiguration.TargetBlockState> overworldSunstoneOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SUNSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SUNSTONE_ORE.get().defaultBlockState())
        );
        // Register Overworld Sunstone ore configured feature
        register(context, OVERWORLD_SUNSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSunstoneOres, 10));

        // Define target block states for Malachite ores
        List<OreConfiguration.TargetBlockState> overworldMalachiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.MALACHITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_MALACHITE_ORE.get().defaultBlockState())
        );

        // Register Overworld Malachite ore configured feature
        register(context, OVERWORLD_MALACHITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMalachiteOres, 10));

        // Define target block states for Nether Ruby ores
        List<OreConfiguration.TargetBlockState> netherRubyOres = List.of(
                OreConfiguration.target(netherrackReplaceables, ModBlocks.RUBY_ORE.get().defaultBlockState())
        );

        // Register Nether Ruby ore configured feature
        register(context, NETHER_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(netherRubyOres, 8));

        // Define target block states for Jaspilite ores
        List<OreConfiguration.TargetBlockState> overworldJaspiliteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.JASPILITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_JASPILITE_ORE.get().defaultBlockState())
        );

        // Register Overworld Jaspilite ore configured feature
        register(context, OVERWORLD_JASPILITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldJaspiliteOres, 10));

        // Register Shatterwood tree configured feature
        register(context, SHATTERBLOOM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SHATTERBLOOM_LOG.get()),
                new DarkOakTrunkPlacer(
                        5, // base height
                        2, // height variance
                        0 // max branch length
                ),
                BlockStateProvider.simple(ModBlocks.SHATTERBLOOM_LEAVES.get()),
                new DarkOakFoliagePlacer(
                        ConstantInt.of(0), // offset
                        UniformInt.of(0, 1) // radius

                ),
                new TwoLayersFeatureSize(1, 0, 2)
        )
                .dirt(BlockStateProvider.simple(Blocks.DIRT))
                .build());

        context.register(PATCH_GRASS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        12, // tries
                        6, // x spread
                        2, // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SHORT_GRASS))

                        )
                )
        ));

        WeightedStateProvider flowerProvider = new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.ALLIUM.defaultBlockState(), 3)
                        .add(Blocks.CORNFLOWER.defaultBlockState(), 3)
                        .add(Blocks.AZURE_BLUET.defaultBlockState(), 2)
                        .add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 2)
                        .add(Blocks.BLUE_ORCHID.defaultBlockState(), 1)
                        .add(Blocks.PITCHER_PLANT.defaultBlockState(), 1)
                        .build()
        );

        context.register(PATCH_SHATTERGROVE_FLOWERS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        64, // tries
                        6,  // x spread
                        2,  // y spread
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(flowerProvider)
                        )
                )
        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                           ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
