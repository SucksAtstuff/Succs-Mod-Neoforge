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

        System.out.println("Corrosion ticking on " + entity.getName().getString());

        /**
         * Durability damage scales with amplifier level:
         * amplifier 0 → 2 damage
         * amplifier 1 → 4 damage
         * amplifier 2 → 6 damage
         * amplifier 3 → 8 damage, etc.
         *
         * Formula ensures consistent scaling.
         */
        int durabilityDamage = 2 + (amplifier * 2);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack stack = entity.getItemBySlot(slot);

                // Only damage non-empty armor stacks
                if (!stack.isEmpty()) {

                    // This damages item durability but does NOT kill the entity if the item breaks
                    stack.hurtAndBreak(durabilityDamage, entity, null);

                    // Sends item break animation to nearby clients for this armor piece
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
        // Effect triggers once every 20 ticks (1 second)
        return duration % 20 == 0;
    }
}
