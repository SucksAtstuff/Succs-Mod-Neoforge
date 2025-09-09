package net.succ.succsmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;

public class ScorchedSandBlock extends FallingBlock {
    public static final MapCodec<ScorchedSandBlock> CODEC = simpleCodec(ScorchedSandBlock::new);

    public ScorchedSandBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }
}

