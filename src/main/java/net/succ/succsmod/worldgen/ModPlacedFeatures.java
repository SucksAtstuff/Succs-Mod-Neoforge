package net.succ.succsmod.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> END_ATHERIUM_ORE_PLACED_KEY = registerKey("end_atherium_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_RUBY_ORE_PLACED_KEY = registerKey("nether_ruby_ore_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> SUNSTONE_ORE_PLACED_KEY = registerKey("sunstone_ore_placed");
    public static final ResourceKey<PlacedFeature> MALACHITE_ORE_PLACED_KEY = registerKey("malachite_ore_placed");
    public static final ResourceKey<PlacedFeature> JASPILITE_ORE_PLACED_KEY = registerKey("jaspilite_ore_placed");

    public static final ResourceKey<PlacedFeature> SHATTERBLOOM_PLACED_KEY = registerKey("shatterbloom_placed");
    public static final ResourceKey<PlacedFeature> PATCH_SHATTERGROVE_FLOWERS_PLACED = registerKey("patch_shattergrove_flowers_placed");

    public static final ResourceKey<PlacedFeature> MYCELIAL_SPOREWOOD_PLACED_KEY = registerKey("mycelial_sporewood_placed");
    public static final ResourceKey<PlacedFeature> PATCH_VENOMOUS_FEN_FLOWERS_PLACED = registerKey("patch_venomous_fen_flowers_placed");

    public static final ResourceKey<PlacedFeature> CRYOHEART_PLACED_KEY = registerKey("cryoheart_placed");

    public static final ResourceKey<PlacedFeature> PATCH_GRASS_PLACED_KEY = registerKey("patch_grass_placed");
    public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS_PLACED_KEY = registerKey("patch_tall_grass_placed");



    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // --- Atherium Ore ---
        // Most rare ore in the mod; rarer than ancient debris.
        // Spawns from Y -32 to 16.
        // Most common around Y -8 (center of triangle distribution).
        // Only 1 vein per chunk.
        register(context, END_ATHERIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ATHERIUM_ORE_KEY),
                ModOrePlacement.rareOrePlacement(1, HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-32), VerticalAnchor.absolute(16))));

        // --- Ruby Ore ---
        // Third rarest.
        // Spawns in the Nether from Y 20 to 80.
        // Most common around Y 50.
        // 3 veins per chunk.
        register(context, NETHER_RUBY_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_RUBY_ORE_KEY),
                ModOrePlacement.rareOrePlacement(3, HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(20), VerticalAnchor.absolute(80))));

        // Register Sapphire ore placed feature with its configuration and placement modifiers
        // Sapphire Ore is most common around Y level 36 (range from 8 to 64)
        register(context, SAPPHIRE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_KEY),
                // pCount represents the number of ore veins to generate per chunk (5 veins per chunk for Sapphire ore)
                ModOrePlacement.commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(64))));

        // --- Sunstone Ore ---
        // Tied with Sapphire in rarity.
        // Spawns in the Overworld from Y 8 to 40.
        // Most common around Y 24.
        // 2 veins per chunk.
        register(context, SUNSTONE_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SUNSTONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(2, HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(8), VerticalAnchor.absolute(40))));

        // --- Malachite Ore ---
        // Second rarest overall.
        // Spawns in the Overworld from Y 0 to 48.
        // Most common around Y 24.
        // 2 veins per chunk.
        register(context, MALACHITE_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_MALACHITE_ORE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(0), VerticalAnchor.absolute(48))));

        // --- Jaspilite Ore ---
        // Most common rarer than diamonds but more common than ancient debris.
        // Spawns in the Overworld from Y -16 to 32.
        // Most common around Y 8.
        // 4 veins per chunk.
        register(context, JASPILITE_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_JASPILITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(4, HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-16), VerticalAnchor.absolute(32))));

        register(context, SHATTERBLOOM_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SHATTERBLOOM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2),
                        ModBlocks.SHATTERBLOOM_SAPLING.get()));

        register(context, MYCELIAL_SPOREWOOD_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.MYCELIAL_SPOREWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.1f, 3),
                        ModBlocks.MYCELIAL_SPOREWOOD_SAPLING.get()));

        register(context, CRYOHEART_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYOHEART_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.1f, 2),
                        ModBlocks.CRYOHEART_SAPLING.get()));


        register(context, PATCH_GRASS_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_GRASS_KEY),
                VegetationPlacements.worldSurfaceSquaredWithCount(4)
        );

        register(context, PATCH_TALL_GRASS_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_TALL_GRASS_KEY),
                VegetationPlacements.worldSurfaceSquaredWithCount(4)
        );

        register(
                context,
                PATCH_SHATTERGROVE_FLOWERS_PLACED,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_SHATTERGROVE_FLOWERS_KEY),
                VegetationPlacements.worldSurfaceSquaredWithCount(2)
        );

        register(
                context,
                PATCH_VENOMOUS_FEN_FLOWERS_PLACED,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_VENOMOUS_FEN_FLOWERS_KEY),
                VegetationPlacements.worldSurfaceSquaredWithCount(3)
        );

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
