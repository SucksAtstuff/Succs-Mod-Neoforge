package net.succ.succsmod.worldgen.biome;

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
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.worldgen.ModPlacedFeatures;

public class ModNetherBiomes {

    private static void addFeature(BiomeGenerationSettings.Builder builder, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature) {
        builder.addFeature(step, feature);
    }

    public static Biome crimsonDepths(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 50, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 50, 2, 8));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HOGLIN, 15, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 30, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.TOXIC_SLIME.get(), 20, 1, 2));

        // --- WORLD GENERATION ---
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatures, carvers);

        addFeature(biomeBuilder, GenerationStep.Decoration.LOCAL_MODIFICATIONS, ModPlacedFeatures.CRIMSON_SPIRE_PLACED_KEY);


        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);     // handles caves + small lava lakes
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);  // handles netherrack/blackstone layers
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);             // lava spring placement

        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);          // quartz, gold, etc.
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);           // mushrooms & fungus patches

        // Custom placed features
        //addFeature(gen, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CRIMSON_SPORE_PATCH_PLACED_KEY);

        // --- AMBIENCE & VISUALS ---
        BiomeSpecialEffects.Builder fx = new BiomeSpecialEffects.Builder()
                .fogColor(0x4C0012)
                .waterColor(0xA31212)
                .waterFogColor(0x260008)
                .skyColor(calculateSkyColor(2.0F))
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.CRIMSON_SPORE, 0.010F))
                .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST))
                .grassColorOverride(0x9E2A2A);

        // --- BUILD BIOME ---
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(fx.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }


    // Sky color helper
    protected static int calculateSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = Mth.clamp(f, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
}
