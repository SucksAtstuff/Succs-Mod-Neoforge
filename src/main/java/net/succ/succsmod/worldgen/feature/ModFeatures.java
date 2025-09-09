package net.succ.succsmod.worldgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.worldgen.feature.config.DunePatchConfig;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, SuccsMod.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<DunePatchConfig>> DUNE_PATCH =
            FEATURES.register("dune_patch", () -> new DunePatchFeature(DunePatchConfig.CODEC));
}
