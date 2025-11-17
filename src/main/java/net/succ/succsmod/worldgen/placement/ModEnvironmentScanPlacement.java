package net.succ.succsmod.worldgen.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.succ.succsmod.worldgen.ModPlacementModifierTypes;

import java.util.stream.Stream;

/**
 * Custom copy of vanilla EnvironmentScanPlacement to work around private constructor.
 * Allows scanning downward for a block that matches a predicate (e.g. custom mycelium).
 */
public class ModEnvironmentScanPlacement extends PlacementModifier {

    public static final MapCodec<ModEnvironmentScanPlacement> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Direction.CODEC.fieldOf("direction_of_search").forGetter(p -> p.directionOfSearch),
                    BlockPredicate.CODEC.fieldOf("target_condition").forGetter(p -> p.targetCondition),
                    BlockPredicate.CODEC.optionalFieldOf("allowed_search_condition", BlockPredicate.alwaysTrue()).forGetter(p -> p.allowedSearchCondition),
                    Codec.intRange(1, 32).fieldOf("max_steps").forGetter(p -> p.maxSteps)
            ).apply(instance, ModEnvironmentScanPlacement::new)
    );

    private final Direction directionOfSearch;
    private final BlockPredicate targetCondition;
    private final BlockPredicate allowedSearchCondition;
    private final int maxSteps;

    public ModEnvironmentScanPlacement(Direction directionOfSearch, BlockPredicate targetCondition, BlockPredicate allowedSearchCondition, int maxSteps) {
        this.directionOfSearch = directionOfSearch;
        this.targetCondition = targetCondition;
        this.allowedSearchCondition = allowedSearchCondition;
        this.maxSteps = maxSteps;
    }

    public static ModEnvironmentScanPlacement scanningFor(Direction directionOfSearch, BlockPredicate targetCondition, BlockPredicate allowedSearchCondition, int maxSteps) {
        return new ModEnvironmentScanPlacement(directionOfSearch, targetCondition, allowedSearchCondition, maxSteps);
    }

    public static ModEnvironmentScanPlacement scanningFor(Direction directionOfSearch, BlockPredicate targetCondition, int maxSteps) {
        return scanningFor(directionOfSearch, targetCondition, BlockPredicate.alwaysTrue(), maxSteps);
    }

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        BlockPos.MutableBlockPos current = pos.mutable();
        WorldGenLevel level = context.getLevel();

        if (!allowedSearchCondition.test(level, current)) {
            return Stream.of();
        }

        int steps = 0;
        while (steps < maxSteps) {
            if (targetCondition.test(level, current)) {
                return Stream.of(current);
            }

            current.move(directionOfSearch);
            if (level.isOutsideBuildHeight(current.getY())) {
                return Stream.of();
            }

            if (allowedSearchCondition.test(level, current)) {
                steps++;
            } else {
                break;
            }
        }

        return targetCondition.test(level, current) ? Stream.of(current) : Stream.of();
    }

    @Override
    public PlacementModifierType<?> type() {
        return ModPlacementModifierTypes.ENVIRONMENT_SCAN.get();
    }
}
