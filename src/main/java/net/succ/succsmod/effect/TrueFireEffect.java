package net.succ.succsmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSources;

public class TrueFireEffect extends MobEffect {
    public TrueFireEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // Check if entity should burn: not wet and not fire resistant
        boolean isWet = entity.isInWaterRainOrBubble();
        boolean isFireResistant = entity.hasEffect(MobEffects.FIRE_RESISTANCE) || entity.fireImmune();

        if (!isWet && !isFireResistant) {
            // Apply fire visual (optional)
            entity.setSharedFlagOnFire(true);

            // Deal fire damage
            entity.hurt(entity.level().damageSources().onFire(), 1.0F + amplifier);
        } else {
            // Stop visual fire if they're wet or resistant
            entity.clearFire();
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
