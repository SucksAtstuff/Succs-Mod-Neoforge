package net.succ.succsmod.worldgen.biome.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;
import terrablender.api.ParameterUtils.*;
import net.succ.succsmod.worldgen.biome.ModBiomes;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class NetherRegion extends Region {
    public NetherRegion(ResourceLocation name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Vanilla overlay builder for extending Nether generation
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        /*
         * Crimson Depths — fiery, fungal surface biome.
         * Hot, dry, low erosion, mostly surface-level.
         * We use similar parameters to Crimson Forest / Nether Wastes,
         * but shift them slightly so it mixes in unique slices.
         */
        new ParameterPointListBuilder()
                .temperature(Temperature.HOT)                                        // Always hot
                .humidity(Humidity.ARID)                                             // Dry, fiery
                .continentalness(Continentalness.span(Continentalness.COAST, Continentalness.MID_INLAND))
                .erosion(Erosion.span(Erosion.EROSION_0, Erosion.EROSION_2))         // Rugged, low erosion
                .depth(Depth.SURFACE, Depth.FLOOR)                                   // Surface to lower layers
                .weirdness(Weirdness.span(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING))
                .build().forEach(point -> builder.add(point, ModBiomes.CRIMSON_DEPTHS));

        /*
         * Optional: widen the range a bit so it blends naturally with existing Nether biomes.
         * This extra slice lets it appear across multiple weirdness layers.
         */
        new ParameterPointListBuilder()
                .temperature(Temperature.HOT)
                .humidity(Humidity.ARID)
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.span(Erosion.EROSION_1, Erosion.EROSION_3))
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.span(Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING))
                .build().forEach(point -> builder.add(point, ModBiomes.CRIMSON_DEPTHS));

        // Register all points
        builder.build().forEach(mapper);
    }
}
