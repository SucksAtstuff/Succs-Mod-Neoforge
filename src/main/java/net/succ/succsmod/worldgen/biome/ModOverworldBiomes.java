package net.succ.succsmod.worldgen.biome;

import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModOverworldBiomes {
    private static void addFeature(BiomeGenerationSettings.Builder builder, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature) {
        builder.addFeature(step, feature);
    }

    public static Biome shatterGrove(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 30);

        // spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData()) // Add pukeko later
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.HEDGEHOG.get(), 200, 2, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.PUKEKO.get(), 200, 3, 5));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_GRASS_PLACED_KEY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_SHATTERGROVE_FLOWERS_PLACED);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SHATTERBLOOM_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false).temperature(4.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x935ad8)                // Mystic amethyst water
                        .waterFogColor(0x3fc5ff)             // Mana blue underwater
                        .fogColor(0xa8e6ff)                  // Subtle sky-colored fog
                        .skyColor(calculateSkyColor(2.0F))
                        .grassColorOverride(0x7DBE4C)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.GLOW, 0.00725f))
                        .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 90000, 8, 2.0D))
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP))
                        .build()))
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome venomousFen(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 10); // Lower general density, more custom mobs

        // custom mobs
        //spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.FEN_SERPENT.get(), 80, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.TOXIC_SLIME.get(), 100, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 45, 2, 4));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_TALL_GRASS_PLACED_KEY);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_VENOMOUS_FEN_FLOWERS_PLACED);
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MYCELIAL_SPOREWOOD_PLACED_KEY);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8F)
                .downfall(0.9F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x223311) // Murky toxic swamp water
                        .waterFogColor(0x334422)
                        .fogColor(0x2d4c3d) // Dense green fog
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.SPORE_BLOSSOM_AIR, 0.02F))
                        .skyColor(calculateSkyColor(0.8F))
                        .grassColorOverride(0x507F3E) // Dark green grass
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D))
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP))
                        .build()))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }

    public static Biome crystalfrostVale(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 20);
        // TODO: Replace with frozen mob variants when added
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.STRAY, 80, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SNOW_GOLEM, 5, 1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatures, carvers);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        // Custom features (you can register these in ModPlacedFeatures)
        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CRYOHEART_PLACED_KEY);
//        addFeature(biomeBuilder, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CRYSTALFROST_FROSTFRUIT_PATCH);
//        addFeature(biomeBuilder, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.SAPPHIRE_GEODE_UNDER_ICE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-0.5F)
                .downfall(0.5F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x4FD6FF) // Pale icy cyan
                        .waterFogColor(0xACCBE1)
                        .fogColor(0xD6F3FF)    // Cold airy fog
                        .skyColor(calculateSkyColor(-0.5F))
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.SNOWFLAKE, 0.0065F))
                        .grassColorOverride(0xA0D8EF) // Frosted blue-green
                        .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                        .build()))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }

    public static Biome solarBlightExpanse(HolderGetter<PlacedFeature> placed, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        // --- Spawns ---
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(spawns);

        // --- Generation ---
        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder(placed, carvers);

        // ⛏️ Carvers WITHOUT lakes (prefer this if available in your mappings)
        BiomeDefaultFeatures.addDefaultCarversAndLakes(gen);

        // Underground + ores + sand/gravel patches
        BiomeDefaultFeatures.addDefaultUndergroundVariety(gen);
        BiomeDefaultFeatures.addDefaultOres(gen);
        BiomeDefaultFeatures.addDefaultSoftDisks(gen);

        // Desert vegetation (cactus, dead bush). No trees/flowers by default.
        BiomeDefaultFeatures.addDesertVegetation(gen);

        // Optional: your custom dunes / emberpine, enable when registered
        addFeature(gen, GenerationStep.Decoration.LOCAL_MODIFICATIONS, ModPlacedFeatures.SOLARBLIGHT_DUNE_PATCH_PLACED);
        // addFeature(gen, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.EMBERPINE_PLACED_KEY);

        // --- Visuals / Ambience ---
        BiomeSpecialEffects.Builder fx = new BiomeSpecialEffects.Builder()
                .waterColor(0xD6A06E) // has no effect if there’s no water source, harmless to keep
                .waterFogColor(0x8E4A20)
                .fogColor(0xFFC77A)
                .skyColor(calculateSkyColor(2.0F))
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.010F))
                .ambientLoopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(fx.build())
                .mobSpawnSettings(spawns.build())
                .generationSettings(gen.build())
                .build();
    }


    protected static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
