package net.succ.succsmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.entity.goals.ToxicSlimeAttackGoal;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ToxicSlimeEntity extends Slime {

    public ToxicSlimeEntity(EntityType<? extends Slime> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new ToxicSlimeAttackGoal(this));

        super.registerGoals();
    }

    @Override
    public void setSize(int size, boolean resetHealth)
    {
        super.setSize(size, resetHealth);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(size * 4.0D); // More HP per size
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(size); // Scales with size
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity living) {
                // Apply poison effect
                living.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 1));
                living.addEffect(new MobEffectInstance(ModEffects.CORROSION_EFFECT, 100, 0));
            }
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide && this.tickCount % 20 == 0) {
            createPoisonAura();
        }
    }

    private void createPoisonAura() {
        AABB area = this.getBoundingBox().inflate(2.0D);
        List<LivingEntity> nearby = level().getEntitiesOfClass(LivingEntity.class, area, e -> e != this && !(e instanceof Slime));
        for (LivingEntity entity : nearby) {
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 0));
        }
    }

    public static boolean checkToxicSlimeSpawnRules(EntityType<ToxicSlimeEntity> entityType,
                                                    LevelAccessor level,
                                                    MobSpawnType spawnType,
                                                    BlockPos pos,
                                                    RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL
                && level.getMaxLocalRawBrightness(pos) <= 7
                && level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }


    @Override
    public void die(DamageSource source) {
        int size = this.getSize();
        if (!this.level().isClientSide && size > 1 && this.isDeadOrDying()) {
            int splitCount = 2 + this.random.nextInt(3);
            for (int i = 0; i < splitCount; ++i) {
                float offset = (i - splitCount / 2.0F) * 0.5F;
                ToxicSlimeEntity mini = new ToxicSlimeEntity(this.getType(), this.level());
                mini.setSize(size / 2, true);
                mini.moveTo(this.getX() + offset, this.getY(), this.getZ() + offset, this.getYRot(), 0.0F);
                this.level().addFreshEntity(mini);
            }
        }
        super.die(source);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Slime.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        this.setSize(2 + random.nextInt(2), true);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }
}
