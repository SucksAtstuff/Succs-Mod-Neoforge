package net.succ.succsmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.worldgen.tree.ModTreeDecoratorTypes;

public class ModTreeDecorator extends TreeDecorator {
    public static final MapCodec<ModTreeDecorator> CODEC =
            Codec.floatRange(0.0F, 1.0F)
                    .fieldOf("probability")
                    .xmap(ModTreeDecorator::new, (decorator) -> decorator.probability);

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.MYCELIAL_VINE.get();
    }



    private final float probability;

    public ModTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();

        context.leaves().forEach((pos) -> {
            tryPlaceCustomVine(context, pos.west(), Direction.EAST, random);
            tryPlaceCustomVine(context, pos.east(), Direction.WEST, random);
            tryPlaceCustomVine(context, pos.north(), Direction.SOUTH, random);
            tryPlaceCustomVine(context, pos.south(), Direction.NORTH, random);
        });
    }

    private void tryPlaceCustomVine(TreeDecorator.Context context, BlockPos pos, Direction attachDir, RandomSource random) {
        if (random.nextFloat() < this.probability && context.isAir(pos)) {
            placeCustomVineColumn(context, pos, attachDir);
        }
    }

    private void placeCustomVineColumn(TreeDecorator.Context context, BlockPos pos, Direction attachDir) {
        int maxLength = 4;
        BlockPos current = pos;
        for (int i = 0; i < maxLength && context.isAir(current); i++) {
            BlockState vineState = ModBlocks.MYCELIAL_SPOREWOOD_VINE.get().defaultBlockState()
                    .setValue(getDirectionProperty(attachDir), true);
            context.setBlock(current, vineState);
            current = current.below();
        }
    }

    private BooleanProperty getDirectionProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> VineBlock.NORTH;
            case SOUTH -> VineBlock.SOUTH;
            case EAST -> VineBlock.EAST;
            case WEST -> VineBlock.WEST;
            default -> throw new IllegalArgumentException("Invalid direction for vine attachment: " + direction);
        };
    }
}
