package net.succ.succsmod.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.succ.succsmod.SuccsMod;
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

    private int gasCooldown = 200;     // corrosive gas cooldown
    private int gasActiveTicks = 0;    // corrosive gas linger ticks

    // NEW — true fire gas timers
    private int trueFireCooldown = 200;
    private int trueFireActiveTicks = 0;

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

    // -------------------------------------------------------------
    //  PREVENT ZOMBIE → DROWNED CONVERSION
    // -------------------------------------------------------------
    @Override
    public boolean isUnderWaterConverting() {
        return false;
    }

    @Override
    protected void doUnderWaterConversion() {}

    // -------------------------------------------------------------
    //  ENTITY TICK LOGIC
    // -------------------------------------------------------------

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide) {
            bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

            // ====================
            // CORROSIVE GAS TIMER
            // ====================
            gasCooldown--;

            if (gasCooldown <= 0) {
                emitCorrosiveGas();
                gasCooldown = 200;
                gasActiveTicks = 60;
            }

            if (gasActiveTicks > 0) {
                spawnLingeringGasParticles();
                gasActiveTicks--;
            }

            // ============================
            // TRUE FIRE GAS (ONLY IF SURGING)
            // ============================
            if (powerSurging) {
                trueFireCooldown--;

                if (trueFireCooldown <= 0) {
                    emitTrueFireGas();
                    trueFireCooldown = 200;
                    trueFireActiveTicks = 60;
                }

                if (trueFireActiveTicks > 0) {
                    spawnTrueFireGasParticles();
                    trueFireActiveTicks--;
                }
            }

            powerSurge();
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

    // ============================================================
    //  CORROSIVE GAS
    // ============================================================

    private void emitCorrosiveGas() {

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        double radius = 6.0D;

        AABB area = new AABB(this.blockPosition()).inflate(radius);
        List<LivingEntity> targets =
                serverLevel.getEntitiesOfClass(LivingEntity.class, area,
                        e -> e != this && e.isAlive());

        for (LivingEntity entity : targets) {
            entity.addEffect(new MobEffectInstance(
                    ModEffects.CORROSION_EFFECT, 600, 2
            ));
        }
    }

    // ============================================================
    //  TRUE FIRE GAS (identical behaviour but different color/effect)
    // ============================================================

    private void emitTrueFireGas() {

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        double radius = 6.0D;

        AABB area = new AABB(this.blockPosition()).inflate(radius);
        List<LivingEntity> targets =
                serverLevel.getEntitiesOfClass(LivingEntity.class, area,
                        e -> e != this && e.isAlive());

        for (LivingEntity entity : targets) {

            entity.addEffect(new MobEffectInstance(
                    ModEffects.TRUE_FIRE_EFFECT, 600, 2
            ));
        }
    }

    // ============================================================
    //  TRUE FIRE GAS PARTICLES
    // ============================================================

    private void spawnTrueFireGasParticles() {

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        double radius = 6.0D;
        int particleCount = 120;

        // 0xFF0059 → RGB = (1.0, 0.0, 0.349)
        DustParticleOptions fireGas = new DustParticleOptions(
                new Vector3f(1.0f, 0.0f, 0.349f), 1.2F
        );

        for (int i = 0; i < particleCount; i++) {

            double dx = this.getX() + (random.nextDouble() - 0.5) * (radius * 2);
            double dy = this.getY() + 0.1 + random.nextDouble() * 1.5;
            double dz = this.getZ() + (random.nextDouble() - 0.5) * (radius * 2);

            serverLevel.sendParticles(
                    fireGas,
                    dx, dy, dz,
                    1,
                    0.0D, 0.01D, 0.0D,
                    0.0D
            );
        }
    }

    // ============================================================
    //  CORROSION PARTICLE CLOUD
    // ============================================================

    private void spawnLingeringGasParticles() {

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        double radius = 6.0D;
        int particleCount = 120;

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

    // ============================================================
    //  SURGE EFFECTS
    // ============================================================

    private void surgeEffects(boolean firstTick) {

        this.level().playSound(
                null, this.blockPosition(),
                ModSounds.TJ_ROAR.get(),
                SoundSource.HOSTILE, 3.0f, 1.0f
        );

        if (this.level() instanceof ServerLevel serverLevel) {

            if (firstTick) {

                DustParticleOptions burst = new DustParticleOptions(
                        new Vector3f(1.0f, 0.3f, 0.2f),
                        1.3f
                );

                for (int i = 0; i < 60; i++) {
                    double dx = this.getX() + (this.random.nextDouble() - 0.5) * 2.5;
                    double dy = this.getY() + 0.5 + this.random.nextDouble() * 1.5;
                    double dz = this.getZ() + (this.random.nextDouble() - 0.5) * 2.5;

                    serverLevel.sendParticles(burst, dx, dy, dz, 1, 0, 0, 0, 0);
                }

                bossEvent.setColor(BossEvent.BossBarColor.RED);
            }

            DustParticleOptions looping = new DustParticleOptions(
                    new Vector3f(1.0f, 0.0f, 0.0f),
                    1.0f
            );

            for (int i = 0; i < 10; i++) {
                double dx = this.getX() + (this.random.nextDouble() - 0.5) * 2.0;
                double dy = this.getY() + 0.3 + this.random.nextDouble() * 1.2;
                double dz = this.getZ() + (this.random.nextDouble() - 0.5) * 2.0;

                serverLevel.sendParticles(looping, dx, dy, dz, 1, 0, 0.01, 0, 0);
            }
        }
    }

    // ============================================================
    //  POWER SURGE LOGIC
    // ============================================================

    boolean powerSurging = false;

    ResourceLocation SURGE_DAMAGE =
            ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "tj_damage_boost");

    ResourceLocation SURGE_SPEED =
            ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "tj_speed_boost");

    private void powerSurge(){

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        float health = this.getHealth();
        float maxHealth = this.getMaxHealth();

        AttributeInstance damage = this.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance speed = this.getAttribute(Attributes.MOVEMENT_SPEED);

        if (damage == null || speed == null) return;

        Difficulty difficulty = serverLevel.getDifficulty();

        double damageBoost = damage.getBaseValue();
        double speedBoost = 0.5;
        float regen = 2.0f;

        if (difficulty == Difficulty.EASY) {
            damageBoost = damage.getBaseValue() * 0.5;
            speedBoost = 0.25;
            regen = 1.0f;
        }

        if (difficulty == Difficulty.HARD) {
            damageBoost = damage.getBaseValue() * 2.0;
            speedBoost = 1.0;
            regen = 10.0f;
        }

        // ============================
        // BELOW 50% HP → SURGE ACTIVE
        // ============================
        if (health <= maxHealth * 0.5f) {

            if (!powerSurging) {

                surgeEffects(true);

                damage.addOrUpdateTransientModifier(
                        new AttributeModifier(SURGE_DAMAGE, damageBoost,
                                AttributeModifier.Operation.ADD_VALUE));

                speed.addOrUpdateTransientModifier(
                        new AttributeModifier(SURGE_SPEED, speedBoost,
                                AttributeModifier.Operation.ADD_VALUE));

                // reset true fire timers at start of surge
                trueFireCooldown = 200;
                trueFireActiveTicks = 0;

                powerSurging = true;
            }

            surgeEffects(false);

            if (this.tickCount % 20 == 0)
                this.heal(regen);

            return;
        }

        // ============================
        // SURGE ENDED — CLEANUP
        // ============================
        if (powerSurging) {

            damage.removeModifier(SURGE_DAMAGE);
            speed.removeModifier(SURGE_SPEED);

            bossEvent.setColor(BossEvent.BossBarColor.BLUE);

            powerSurging = false;

            trueFireCooldown = 200;
            trueFireActiveTicks = 0;
        }
    }
}
