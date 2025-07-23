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
        // Remove corrosion effect if submerged in water
        if (entity.isInWater()) {
            System.out.println("Removing Corrosion from " + entity.getName().getString() + " due to water");
            entity.removeEffect(ModEffects.CORROSION_EFFECT);
            return false; // stop ticking
        }

        System.out.println("Corrosion ticking on " + entity.getName().getString());

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack stack = entity.getItemBySlot(slot);
                if (!stack.isEmpty()) {
                    stack.hurtAndBreak(2, entity, null);
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
        return duration % 20 == 0;
    }
}
