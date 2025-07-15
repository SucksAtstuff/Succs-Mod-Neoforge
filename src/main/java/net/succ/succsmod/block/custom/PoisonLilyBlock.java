package net.succ.succsmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PoisonLilyBlock extends FlowerBlock {
    public PoisonLilyBlock(Holder<MobEffect> effectHolder, int duration, Properties properties) {
        super(effectHolder, duration, properties.randomTicks());
    }


    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        level.scheduleTick(pos, this, 20); // 1 second delay
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        applyPoisonEffect(level, pos);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        applyPoisonEffect(level, pos);
        level.scheduleTick(pos, this, 60); // repeat every 3 seconds
    }

    private void applyPoisonEffect(Level level, BlockPos pos) {
        AABB effectBox = new AABB(pos).inflate(3); // 3-block radius
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, effectBox);

        for (LivingEntity entity : entities) {
            if (!entity.isInvulnerable() && !entity.hasEffect(MobEffects.POISON)) {
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0)); // 3 seconds poison
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.2F) {
            level.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR,
                    pos.getX() + 0.5 + (random.nextFloat() - 0.5),
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5 + (random.nextFloat() - 0.5),
                    0.0D, 0.0D, 0.0D);
        }
    }

}

