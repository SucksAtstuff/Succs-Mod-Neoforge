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

public class TjEntity extends Zombie {
    private final ServerBossEvent bossEvent = new ServerBossEvent(
            Component.translatable("entity.succsessentials.tj"), // Boss name
            BossEvent.BossBarColor.BLUE,           // Bar color
            BossEvent.BossBarOverlay.NOTCHED_10   // Bar style
    );

    public TjEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        this.bossEvent.setVisible(true); // Make sure it shows up for nearby players
        this.xpReward = 500; // <-- Give 500 XP on death
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player); // Add player to bar when they see the entity
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player); // Remove player when they stop seeing the entity
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            bossEvent.setProgress(this.getHealth() / this.getMaxHealth()); // Update boss bar progress
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
        return false; // doesn’t burn in sunlight
    }

    // Always adult
    @Override
    public void setBaby(boolean baby) {
        // TJ never becomes a baby, ignore vanilla logic
    }

    // Just in case (to prevent rendering bugs), make sure the game always sees it as an adult:
    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals(); // keeps normal zombie AI
    }



    // The boss overall has high stats because of the armour and tools included in the mod.
    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)    // very tanky
                .add(Attributes.ATTACK_DAMAGE, 25.0D)  // strong hits
                .add(Attributes.ARMOR, 20.0D)          //  big armour
                .add(Attributes.MOVEMENT_SPEED, 0.28D); // a bit faster
    }
}