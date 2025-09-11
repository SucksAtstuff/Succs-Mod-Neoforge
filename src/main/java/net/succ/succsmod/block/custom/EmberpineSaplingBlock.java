package net.succ.succsmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.succ.succsmod.block.ModBlocks;

public class EmberpineSaplingBlock extends SaplingBlock {
    public EmberpineSaplingBlock(TreeGrower grower, Properties props) {
        super(grower, props);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        // allow vanilla soils + your scorched sand
        return super.mayPlaceOn(state, level, pos) || state.is(ModBlocks.SCORCHED_SAND.get());
    }
}
