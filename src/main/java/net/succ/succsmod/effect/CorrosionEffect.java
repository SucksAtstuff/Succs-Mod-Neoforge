package net.succ.succsmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class CorrosionEffect extends MobEffect {

    public CorrosionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // If entity is submerged in water, instantly cleanse the corrosion
        if (entity.isInWater()) {
            System.out.println("Removing Corrosion from " + entity.getName().getString() + " due to water");
            entity.removeEffect(ModEffects.CORROSION_EFFECT);
            return false;
        }

        // Damage scales with effect strength
        int durabilityDamage = 2 + (amplifier * 2);

        // Loop armor slots
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {

                ItemStack stack = entity.getItemBySlot(slot);

                if (!stack.isEmpty()) {

                    /**
                     * FIX:
                     * NeoForge 1.21 requires the EquipmentSlot directly for hurtAndBreak.
                     * Passing null crashes; passing a lambda is invalid.
                     *
                     * This safely damages the armor in the correct slot.
                     */
                    stack.hurtAndBreak(durabilityDamage, entity, slot);

                    // Show break animation
                    if (!entity.level().isClientSide) {
                        entity.level().broadcastEntityEvent(entity, (byte) (0x1F + slot.getIndex()));
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0; // Tick once every second
    }
}
