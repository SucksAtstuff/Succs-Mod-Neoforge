package net.succ.succsmod.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect>  MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SuccsMod.MOD_ID);

    public static final Holder<MobEffect> TRUE_FIRE_EFFECT = MOB_EFFECTS.register("true_fire",
            () -> new TrueFireEffect(MobEffectCategory.HARMFUL, 0xFF0059));

    public static final Holder<MobEffect> ATTACK_SPEED_EFFECT = MOB_EFFECTS.register("attack_speed",
            () -> new AttackSpeedEffect(MobEffectCategory.BENEFICIAL, 0xFFD700));

    public static final Holder<MobEffect> CORROSION_EFFECT = MOB_EFFECTS.register("corrosion",
            () -> new CorrosionEffect(MobEffectCategory.HARMFUL, 0x00FF00));

    public static void register (IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
