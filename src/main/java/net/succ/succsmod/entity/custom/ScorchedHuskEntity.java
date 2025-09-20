package net.succ.succsmod.entity.custom;

import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.item.ModItems;


public class ScorchedHuskEntity extends Husk {
    // 20% chance to inflict TRUE_FIRE on hit
    private static final float TRUE_FIRE_CHANCE = 0.20f;
    private static final int TRUE_FIRE_DURATION = 5 * 20; // 5s

    public ScorchedHuskEntity(EntityType<? extends Husk> type, Level level) {
        super(type, level);
    }

    /* --- Heat & flare immunities --- */
    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        // Fire/heat flavors (covers lava, on_fire, in_fire, hot_floor, etc.)
        if (source.is(DamageTypeTags.IS_FIRE)) return true;

        // If you use custom damage types for heat / solar flare, block them too
        Holder<DamageType> t = source.typeHolder();
        return super.isInvulnerableTo(source);
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        // Immune to your custom burning effect
        if (effect.getEffect() == ModEffects.TRUE_FIRE_EFFECT /* .get() if RegistryObject */) return false;
        return super.canBeAffected(effect);
    }

    /* --- On-hit: apply TRUE_FIRE unless target is cooled --- */

    @Override
    public boolean doHurtTarget(net.minecraft.world.entity.Entity target) {
        boolean hit = super.doHurtTarget(target);
        if (!hit) return false;

        if (target instanceof Player p && this.random.nextFloat() < TRUE_FIRE_CHANCE) {
            if (!isHeatProtected(p) && !p.hasEffect(ModEffects.TRUE_FIRE_EFFECT)) {
                p.addEffect(new MobEffectInstance(ModEffects.TRUE_FIRE_EFFECT, TRUE_FIRE_DURATION, 0, false, true));
            }
        }
        return true;
    }

    private static boolean isHeatProtected(Player p) {
        return wearingCoolingSet(p);
    }

    private static boolean wearingCoolingSet(Player p) {
        ItemStack head  = p.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = p.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs  = p.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet  = p.getItemBySlot(EquipmentSlot.FEET);

        boolean h = !head.isEmpty(); // not used, but kept for syntax consistency
        boolean c = !chest.isEmpty() && chest.is(ModItems.BIKINI_TOP.get());
        boolean l = !legs.isEmpty()  && legs.is(ModItems.SWIM_TRUNKS.get());
        boolean f = !feet.isEmpty(); // not used, but kept for syntax consistency

        return c && l;
    }

}
