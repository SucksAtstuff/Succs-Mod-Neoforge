package net.succ.succsmod.block.custom;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.function.Supplier;

public class ModFungusBlock extends FungusBlock {

    public ModFungusBlock(
            ResourceKey<ConfiguredFeature<?, ?>> feature,
            Supplier<Block> requiredBlock,
            Properties props
    ) {
        super(feature, requiredBlock.get(), props);
    }
}
