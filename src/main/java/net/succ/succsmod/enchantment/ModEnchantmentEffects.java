package net.succ.succsmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod; // Replace with your actual mod class if different
import net.succ.succsmod.enchantment.custom.Excavation;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    // 🔧 This is the registry NeoForge uses for entity-based enchantment logic
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, SuccsMod.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> EXCAVATION =
            ENTITY_ENCHANTMENT_EFFECTS.register("excavation", () -> Excavation.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}

