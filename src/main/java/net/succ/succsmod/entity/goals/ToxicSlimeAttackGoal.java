package net.succ.succsmod.entity.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.LivingEntity;

import java.util.EnumSet;

public class ToxicSlimeAttackGoal extends Goal {
    private final Mob mob;
    private int attackCooldown;

    public ToxicSlimeAttackGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public void start() {
        this.attackCooldown = 20;
    }

    @Override
    public void stop() {
        // Optional cleanup
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target == null) return;

        this.mob.getLookControl().setLookAt(target, 10.0F, 10.0F);
        if (this.mob.distanceToSqr(target) < 2.0D) {
            if (this.attackCooldown <= 0) {
                this.attackCooldown = 20;
                this.mob.doHurtTarget(target);
            } else {
                this.attackCooldown--;
            }
        } else {
            this.mob.getNavigation().moveTo(target, 1.0D);
        }
    }
}
