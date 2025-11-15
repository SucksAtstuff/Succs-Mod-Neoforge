package net.succ.succsmod.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.succ.succsmod.sound.ModSounds;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.phys.AABB;
import net.succ.succsmod.effect.ModEffects;
import org.joml.Vector3f;

import java.util.List;

public class TjEntity extends Zombie {

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            Component.translatable("entity.succsessentials.tj"),
            BossEvent.BossBarColor.BLUE,
            BossEvent.BossBarOverlay.NOTCHED_10
    );

    private int gasCooldown = 200;     // 10 seconds between gas attacks
    private int gasActiveTicks = 0;    // number of ticks the gas cloud remains visible

    public TjEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        this.bossEvent.setVisible(true);
        this.xpReward = 500;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide) {
            bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

            // Countdown to next gas attack
            gasCooldown--;

            if (gasCooldown <= 0) {
                emitCorrosiveGas();   // apply effect
                gasCooldown = 200;    // reset cooldown
                gasActiveTicks = 60;  // gas lingers visually for 3 seconds
            }

            // Linger the cloud
            if (gasActiveTicks > 0) {
                spawnLingeringGasParticles();
                gasActiveTicks--;
            }
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.TJ_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.TJ_DEATH.get();
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public void setBaby(boolean baby) {}

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.ATTACK_DAMAGE, 45.0D)
                .add(Attributes.ARMOR, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D);
    }

    /**
     * Applies the corrosion effect to all nearby entities.
     */
    private void emitCorrosiveGas() {

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        double radius = 6.0D;

        // Apply corrosion to entities in radius
        AABB area = new AABB(this.blockPosition()).inflate(radius);
        List<LivingEntity> targets = serverLevel.getEntitiesOfClass(
                LivingEntity.class,
                area,
                e -> e != this && e.isAlive()
        );

        for (LivingEntity entity : targets) {
            entity.addEffect(new MobEffectInstance(
                    ModEffects.CORROSION_EFFECT, // your custom effect
                    600, // 30 seconds
                    2    // strong version
            ));
        }
    }

    /**
     * Creates the lingering visual fog particles around TJ.
     */
    private void spawnLingeringGasParticles() {

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        double radius = 6.0D;
        int particleCount = 120; // per tick

        // Green toxic gas color
        DustParticleOptions gas = new DustParticleOptions(
                new Vector3f(0.2F, 0.9F, 0.1F),
                1.2F
        );

        for (int i = 0; i < particleCount; i++) {
            double dx = this.getX() + (random.nextDouble() - 0.5) * (radius * 2);
            double dy = this.getY() + 0.1 + random.nextDouble() * 1.5;
            double dz = this.getZ() + (random.nextDouble() - 0.5) * (radius * 2);

            serverLevel.sendParticles(
                    gas,
                    dx, dy, dz,
                    1,
                    0.0D, 0.01D, 0.0D,
                    0.0D
            );
        }
    }
}
