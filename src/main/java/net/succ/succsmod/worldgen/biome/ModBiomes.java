package net.succ.succsmod.worldgen.biome;

import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.worldgen.biome.region.OverworldRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import terrablender.api.Regions;

public class ModBiomes {
    public static final ResourceKey<Biome> SHATTERGROVE = registerBiomeKey("shattergrove");
    public static final ResourceKey<Biome> VENOMOUS_FEN = registerBiomeKey("venomous_fen");
    public static final ResourceKey<Biome> CRYSTALFROST_VALE = registerBiomeKey("crystalfrost_vale");

    public static void registerBiomes() {
        Regions.register(new OverworldRegion(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succsessentials_overworld"), 20));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carver = context.lookup(Registries.CONFIGURED_CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, SHATTERGROVE, ModOverworldBiomes.shatterGrove(placedFeatures, carver));
        register(context, VENOMOUS_FEN, ModOverworldBiomes.venomousFen(placedFeatures, carver));
        register(context, CRYSTALFROST_VALE, ModOverworldBiomes.crystalfrostVale(placedFeatures, carver));
    }


    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
    }
}
