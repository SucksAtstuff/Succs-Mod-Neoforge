package net.succ.succsmod.worldgen.biome.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;
import terrablender.api.ParameterUtils.*;
import net.succ.succsmod.worldgen.biome.ModBiomes;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

/**
 * Custom Nether region for TerraBlender integration.
 * This class injects the Crimson Depths biome into the Nether generation system
 * in a way that mimics vanilla Crimson Forest behavior while allowing both to coexist.
 */
public class NetherRegion extends Region {

    public NetherRegion(ResourceLocation name, int weight) {
        // Define this as a Nether-type TerraBlender region with a given priority weight
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Create an overlay builder to inject our custom biome without replacing others
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        /*
         * Add Crimson Depths in the same parameter space used by vanilla Crimson Forest.
         * This ensures it spawns under the same hot, humid, mid-elevation Nether cavern regions.
         * Both biomes will now coexist and blend naturally.
         */
        new ParameterPointListBuilder()
                .temperature(Temperature.HOT)
                .humidity(Humidity.HUMID)
                .continentalness(Continentalness.span(Continentalness.COAST, Continentalness.MID_INLAND))
                .erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_3))
                .depth(Depth.SURFACE, Depth.FLOOR)
                .weirdness(Weirdness.span(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING))
                .build().forEach(point -> builder.add(point, ModBiomes.CRIMSON_DEPTHS));

        // Register everything
        builder.build().forEach(mapper);
    }
}
