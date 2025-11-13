package net.succ.succsmod.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;
import java.util.Optional;
import java.util.function.Supplier;

public class ModFungusBlock extends BushBlock implements BonemealableBlock {

    // Same vanilla shape
    protected static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);

    // The feature this fungus grows into (giant fungus)
    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    /**
     * Supplier of the block this fungus must be placed on (your Crimson Mycelium)
     * Instead of storing a Block directly, we store a Supplier to avoid registry lifecycle crashes.
     */
    private final Supplier<Block> requiredBaseBlock;

    public ModFungusBlock(ResourceKey<ConfiguredFeature<?, ?>> feature,
                          Supplier<Block> requiredBaseBlock,
                          BlockBehaviour.Properties properties) {

        super(properties);
        this.feature = feature;
        this.requiredBaseBlock = requiredBaseBlock;  // SAFE: no .get() here
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return null;
    }

    /**
     * Determines what blocks this fungus is allowed to be placed on.
     * We mimic vanilla here, but allow the custom mycelium to work.
     */
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return
                state.is(BlockTags.NYLIUM) ||           // warped/crimson nylium
                        state.is(Blocks.MYCELIUM) ||            // overworld mycelium
                        state.is(Blocks.SOUL_SOIL) ||           // soul soil
                        state.is(requiredBaseBlock.get());      // your custom mycelium
    }

    /**
     * Retrieves the configured feature (giant fungus feature) from the registry.
     */
    private Optional<? extends Holder<ConfiguredFeature<?, ?>>> getFeature(LevelReader level) {
        return level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE)
                .getHolder(this.feature);
    }

    /**
     * Bonemeal is valid only if the block below matches the required base.
     */
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.below()).is(requiredBaseBlock.get());
    }


    /**
     * Vanilla 40% chance bonemeal success.
     */
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.4f;
    }

    /**
     * Grows the giant fungus. Uses NeoForge's event hooks (like vanilla).
     */
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {

        // Fire event so mods/datapacks can cancel/modify the growth feature
        BlockGrowFeatureEvent event = EventHooks.fireBlockGrowFeature(
                level, random, pos, getFeature(level).orElse(null)
        );

        if (!event.isCanceled()) {
            Optional.ofNullable(event.getFeature())
                    .ifPresent(featureHolder ->
                            featureHolder.value().place(
                                    level,
                                    level.getChunkSource().getGenerator(),
                                    random,
                                    pos
                            )
                    );
        }
    }
}
