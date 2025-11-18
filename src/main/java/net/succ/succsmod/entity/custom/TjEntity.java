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

    // -------------------------------------------------------------
    //  PREVENT ZOMBIE → DROWNED CONVERSION
    // -------------------------------------------------------------

    @Override
    public boolean isUnderWaterConverting() {
        // Always false → the zombie will *never* begin drowning conversion
        return false;
    }

    @Override
    protected void doUnderWaterConversion() {
        // Override and block the actual conversion process from executing
        // This guarantees 100% immunity to becoming a drowned
    }

    // -------------------------------------------------------------
    //  ENTITY TICK LOGIC
    // -------------------------------------------------------------

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
                    ModEffects.CORROSION_EFFECT, // custom effect
                    600, // 30 seconds
                    2    // strong version
            ));
        }
    }

    private void surgeEffects() {
        // This plays a 3D sound at TJ's position. Because we're on the server,
        // clients within range will automatically hear it.
        this.level().playSound(
                null, // null = broadcast to all nearby players
                this.blockPosition(), // location of TJ
                ModSounds.TJ_ROAR.get(),// custom roar sound
                SoundSource.HOSTILE,
                3.0f, // volume
                1.0f // pitch
        );
        System.out.println("SURGE EFFECTS RAN");
    }


    // Tracks whether the TJ entity is currently empowered
    boolean powerSurging = false;

    // Fixed resource locations for attribute modifiers.
    ResourceLocation SURGE_DAMAGE = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "tj_damage_boost");
    ResourceLocation SURGE_SPEED = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "tj_speed_boost");

    private void powerSurge(){
        // If we are not on the server level, stop immediately
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        // Current and max HP
        float health = this.getHealth();
        float maxHealth = this.getMaxHealth();

        // Attribute instances on THIS entity
        AttributeInstance damageAttribute = this.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance speedAttribute  = this.getAttribute(Attributes.MOVEMENT_SPEED);

        // If somehow the attributes don't exist, return so the game doesn't crash
        if (damageAttribute == null || speedAttribute == null) return;

        // Fetch the worlds difficulty level
        Difficulty difficulty = serverLevel.getDifficulty();

        double damageBoostAmount = damageAttribute.getBaseValue(); // doubles damage
        double speedBoostPercent = 0.5; // +50% speed
        float regenPerSecond = 2.0f; // regen 2 HP/sec

        if (difficulty == Difficulty.EASY) {
            damageBoostAmount = damageAttribute.getBaseValue() * 0.5; // only +50% damage
            speedBoostPercent = 0.25; // +25% speed
            regenPerSecond    = 1.0f; // regen 1 HP/sec
        }

        if (difficulty == Difficulty.HARD) {
            damageBoostAmount = damageAttribute.getBaseValue() * 2.0; // triple damage total
            speedBoostPercent = 1.0; // +100% speed (double)
            regenPerSecond    = 10.0f; // regen 10 HP/sec
        }

        // Activates power surge if HP is below 50%
        if (health <= maxHealth * 0.5f) {
            if (!powerSurging)
            {
                surgeEffects();

                AttributeModifier damageBoost = new AttributeModifier(
                        SURGE_DAMAGE, // Resource location
                        damageBoostAmount,
                        AttributeModifier.Operation.ADD_VALUE
                );

                AttributeModifier speedBoost = new AttributeModifier(
                        SURGE_SPEED, // Resource location
                        speedBoostPercent,
                        AttributeModifier.Operation.ADD_VALUE
                );

                damageAttribute.addOrUpdateTransientModifier(damageBoost);
                speedAttribute.addOrUpdateTransientModifier(speedBoost);

                // Mark the entity as surging so we don't re-apply every tick
                powerSurging = true;
            }

            // Heals the entity slowly while surging
            // Heal every 20 ticks = once per second
            if (this.tickCount % 20 == 0) {
                this.heal(regenPerSecond);
            }

          return;
        }

        if (powerSurging) {
            // Remove the modifiers
            damageAttribute.removeModifier(SURGE_DAMAGE);
            damageAttribute.removeModifier(SURGE_SPEED);

            powerSurging = false; // reset state
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
