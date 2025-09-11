package net.succ.succsmod.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, SuccsMod.MOD_ID);

    public static final Holder<Potion> TRUE_FIRE_POTION = POTIONS.register("true_fire_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.TRUE_FIRE_EFFECT, 200, 0))); // 5 seconds

    public static final Holder<Potion> CHILLING_POTION = POTIONS.register("chilling",
            () -> new Potion(new MobEffectInstance(ModEffects.CHILLING_EFFECT, 3 * 60 * 20, 0))); // 3:00

    public static final Holder<Potion> LONG_CHILLING_POTION = POTIONS.register("long_chilling",
            () -> new Potion(new MobEffectInstance(ModEffects.CHILLING_EFFECT, 8 * 60 * 20, 0))); // 8:00 (redstone)

    public static void register (IEventBus eventBus)
    {
        POTIONS.register(eventBus);
    }

}
