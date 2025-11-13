package net.succ.succsmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.worldgen.feature.ModFeatures;
import net.succ.succsmod.worldgen.feature.config.DunePatchConfig;
import net.succ.succsmod.worldgen.feature.config.GemSpireConfig;
import net.succ.succsmod.worldgen.tree.custom.ModTreeDecorator;

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
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYCELIAL_SPOREWOOD_KEY = registerKey("mycelical_sporewood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYOHEART_KEY = registerKey("cryoheart");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMBERPINE_KEY = registerKey("emberpine");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_GRASS_KEY = registerKey("patch_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TALL_GRASS_KEY = registerKey("patch_tall_grass");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SHATTERGROVE_FLOWERS_KEY = registerKey("patch_shattergrove_flowers");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_VENOMOUS_FEN_FLOWERS_KEY = registerKey("patch_venomous_fen_flowers");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOLARBLIGHT_DUNE_PATCH =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "solarblight_dune_patch"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> CRIMSON_SPIRE_KEY =
            registerKey("crimson_spire");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        // Define RuleTests for blocks that can be replaced by ores
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplacables = new BlockMatchTest(Blocks.END_STONE);
        RuleTest packedIceReplaceables = new BlockMatchTest(Blocks.PACKED_ICE);

        // Define target block states for Atherium ores
        List<OreConfiguration.TargetBlockState> endAtheriumOres = List.of(
                OreConfiguration.target(endReplacables, ModBlocks.END_ATHERIUM_ORE.get().defaultBlockState())
        );

        // Register End Atherium ore configured feature
        register(context, END_ATHERIUM_ORE_KEY, Feature.ORE, new OreConfiguration(endAtheriumOres, 5));


        // Define target block states for Nether Ruby ores
        List<OreConfiguration.TargetBlockState> netherRubyOres = List.of(
                OreConfiguration.target(netherrackReplaceables, ModBlocks.NETHER_RUBY_ORE.get().defaultBlockState())
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

        // Register Mycelial Sporewood tree configured feature
        register(context, MYCELIAL_SPOREWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MYCELIAL_SPOREWOOD_LOG.get()),
                new BendingTrunkPlacer(
                        4,
                        1,
                        1,
                        4,
                        ConstantInt.of(3)),
                BlockStateProvider.simple(ModBlocks.MYCELIAL_SPOREWOOD_LEAVES.get()),
                new AcaciaFoliagePlacer(
                        ConstantInt.of(2), // wide canopy
                        ConstantInt.of(1)  // no vertical offset
                ),
        new TwoLayersFeatureSize(1, 0, 2)
        )
                .dirt(BlockStateProvider.simple(Blocks.MUD))
                .decorators(List.of(
                        new ModTreeDecorator(0.15f)))
                .build());

        // Register Cryoheart tree configured feature
        register(context, CRYOHEART_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.CRYOHEART_LOG.get()),
                new FancyTrunkPlacer(
                        4, // baseHeight
                        5, // heightRandA
                        3 // heightRandB
                ),
                BlockStateProvider.simple(ModBlocks.CRYOHEART_LEAVES.get()), // <-- no .setValue(PERSISTENT, true)
                new FancyFoliagePlacer(
                        ConstantInt.of(2), // foliageRadius
                        ConstantInt.of(2), // foliageOffset
                        2 // foliageHeight
                ),
                new TwoLayersFeatureSize(
                        1, // limit
                        0, // lower size
                        2  // upper size
                )
        )
                .dirt(BlockStateProvider.simple(Blocks.DIRT))
                .build());

        // Register Emberpine tree configured feature
        register(context, EMBERPINE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.EMBERPINE_LOG.get()),
                new StraightTrunkPlacer(
                        6, // baseHeight (taller than oak)
                        2, // heightRandA
                        1  // heightRandB
                ),
                BlockStateProvider.simple(ModBlocks.EMBERPINE_LEAVES.get()),
                new PineFoliagePlacer(
                        ConstantInt.of(2), // foliageRadius near top
                        ConstantInt.of(1), // foliageOffset
                        ConstantInt.of(4)  // foliageHeight (taller cone)
                ),
                new TwoLayersFeatureSize(
                        1, // limit
                        0, // lower size
                        2  // upper size
                )
        )
                // Use scorched sand as the “dirt” patch under the trunk
                .dirt(BlockStateProvider.simple(ModBlocks.SCORCHED_SAND.get()))
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

        context.register(PATCH_TALL_GRASS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        16, // tries
                        6, // x spread
                        2, // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.TALL_GRASS))

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

        WeightedStateProvider venomousFenFlowerProvider = new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.OXEYE_DAISY.defaultBlockState(), 2)
                        .add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1)
                        .add(ModBlocks.POISON_LILY.get().defaultBlockState(), 2)
                        .build()
        );

        context.register(PATCH_SHATTERGROVE_FLOWERS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        16, // tries
                        6,  // x spread
                        2,  // y spread
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(flowerProvider)
                        )
                )
        ));

        context.register(PATCH_VENOMOUS_FEN_FLOWERS_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        32, // tries
                        6,  // x spread
                        2,  // y spread
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(venomousFenFlowerProvider)
                        )
                )
        ));


        var top  = ModBlocks.SCORCHED_SAND.get().defaultBlockState();
        var core = Blocks.RED_SANDSTONE.defaultBlockState();

        var cfg = new DunePatchConfig(
                top, core,
                6, 12,        // radius range (tweak to taste)
                0.45f, 1.2f   // height scale & shape exponent (higher exponent = steeper center)
        );

        context.register(SOLARBLIGHT_DUNE_PATCH, new ConfiguredFeature<>(ModFeatures.DUNE_PATCH.get(), cfg));

        // --- Crimson Spire Feature (for Crimson Depths) ---
        var spireConfig = new GemSpireConfig(
                ModBlocks.CRIMSON_MYCELIUM.get().defaultBlockState(), // main block
                ModBlocks.NETHER_RUBY_ORE.get().defaultBlockState(),          // ore block
                10, 35,                                                // min & max height
                3,                                                     // radius
                0.05f                                                  // 5% ore chance
        );

        context.register(
                CRIMSON_SPIRE_KEY,
                new ConfiguredFeature<>(ModFeatures.GEM_SPIRE.get(), spireConfig)
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                           ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
