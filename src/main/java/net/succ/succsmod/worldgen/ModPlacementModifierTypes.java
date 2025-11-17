package net.succ.succsmod.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.worldgen.placement.ModEnvironmentScanPlacement;

public class ModPlacementModifierTypes {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER_TYPES =
            DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, SuccsMod.MOD_ID);

    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<ModEnvironmentScanPlacement>> ENVIRONMENT_SCAN =
            PLACEMENT_MODIFIER_TYPES.register("mod_environment_scan", () -> () -> ModEnvironmentScanPlacement.CODEC);

    public static void register(IEventBus eventBus) {
        PLACEMENT_MODIFIER_TYPES.register(eventBus);
    }
}

