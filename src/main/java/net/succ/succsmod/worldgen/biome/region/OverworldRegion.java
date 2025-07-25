package net.succ.succsmod.worldgen.biome.region;

import com.mojang.datafixers.util.Pair;
import net.succ.succsmod.worldgen.biome.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class OverworldRegion extends Region {
    public OverworldRegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        // Overlap Vanilla's parameters with our own for our COLD_BLUE biome.
        // The parameters for this biome are chosen arbitrarily.
        new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.COOL, Temperature.FROZEN))
            .humidity(Humidity.span(Humidity.ARID, Humidity.DRY))
            .continentalness(Continentalness.INLAND)
            .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
            .depth(Depth.SURFACE, Depth.FLOOR)
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
            .build().forEach(point -> builder.add(point, ModBiomes.SHATTERGROVE));

        // Add the VENOMOUS_FEN biome with its own parameters.
        new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.WARM, Temperature.HOT))
            .humidity(Humidity.span(Humidity.HUMID, Humidity.WET))
            .continentalness(Continentalness.COAST)
            .erosion(Erosion.EROSION_2, Erosion.EROSION_3)
            .depth(Depth.SURFACE, Depth.FLOOR)
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
            .build().forEach(point -> builder.add(point, ModBiomes.VENOMOUS_FEN));

        // Add the CRYSTALFROST_VALE biome: cold, rare, high inland
        new ParameterPointListBuilder()
                .temperature(Temperature.FROZEN)
                .humidity(Humidity.span(Humidity.DRY, Humidity.NEUTRAL))
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.EROSION_0) // High cliffs / low erosion = icy peaks
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING) // Optional: isolate it more
                .build().forEach(point -> builder.add(point, ModBiomes.CRYSTALFROST_VALE));


        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}