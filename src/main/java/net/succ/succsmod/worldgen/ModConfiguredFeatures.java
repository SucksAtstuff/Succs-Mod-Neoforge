package net.succ.succsmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
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

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                           ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
