package net.succ.succsmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.succ.succsmod.effect.ModEffects;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class CustomCurioMobEffectItem extends Item implements ICurioItem {

    private final MobEffectInstance mobEffectInstance;
    private final String slotType;

    public CustomCurioMobEffectItem(MobEffectInstance mobEffectInstance, String slotType) {
        super(new Properties().stacksTo(1).rarity(Rarity.COMMON));
        this.mobEffectInstance = mobEffectInstance;
        this.slotType = slotType;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        // If the effect is POISON, show only "Provides Poison Resistance"
        if (mobEffectInstance.getEffect() == MobEffects.POISON) {
            tooltipComponents.add(Component.literal("Grants Poison Resistance"));
        }
        else if (mobEffectInstance.getEffect() == MobEffects.HEALTH_BOOST) {
            tooltipComponents.add(Component.literal("Grants an extra row of hearts"));
        }
        else if (mobEffectInstance.getEffect() == MobEffects.DIG_SPEED) {
            tooltipComponents.add(Component.literal("Grants Haste"));
        }
        else if (mobEffectInstance.getEffect() == MobEffects.FIRE_RESISTANCE) {
            tooltipComponents.add(Component.literal("Grants Fire Resistance"));
        }
        else if (mobEffectInstance.getEffect() == MobEffects.LUCK) {
            tooltipComponents.add(Component.literal("Grants Luck"));
        }
        // If the effect is HEALTH_BOOST, show "Grants an extra row of hearts"
        else if (mobEffectInstance.getEffect() == ModEffects.ATTACK_SPEED_EFFECT) {
            tooltipComponents.add(Component.literal("Grants attack speed boost"));
        }
        // For other effects, show the effect name
        else {
            String effectName = mobEffectInstance.getEffect().getRegisteredName().toString();
            tooltipComponents.add(Component.literal("Grants " + effectName));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = (LivingEntity) slotContext.entity(); // Direct casting
        // Check if the player has the poison effect, and if so, remove it
        if (livingEntity != null && livingEntity.hasEffect(MobEffects.POISON)) {
            livingEntity.removeEffect(MobEffects.POISON);
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) slotContext.entity();
            entity.addEffect(new MobEffectInstance(mobEffectInstance));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) slotContext.entity();
            entity.removeEffect(mobEffectInstance.getEffect());
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals(slotType);
    }
}
