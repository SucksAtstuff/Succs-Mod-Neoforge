package net.succ.succsmod.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record GemSpireConfig(
        BlockState mainBlock,
        BlockState oreBlock,
        int minHeight,
        int maxHeight,
        int radius,
        float oreChance
) implements FeatureConfiguration {

    public static final Codec<GemSpireConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockState.CODEC.fieldOf("main_block").forGetter(GemSpireConfig::mainBlock),
                    BlockState.CODEC.fieldOf("ore_block").forGetter(GemSpireConfig::oreBlock),
                    Codec.intRange(3, 80).fieldOf("min_height").forGetter(GemSpireConfig::minHeight),
                    Codec.intRange(4, 120).fieldOf("max_height").forGetter(GemSpireConfig::maxHeight),
                    Codec.intRange(1, 8).fieldOf("radius").forGetter(GemSpireConfig::radius),
                    Codec.floatRange(0.0F, 1.0F).fieldOf("ore_chance").forGetter(GemSpireConfig::oreChance)
            ).apply(instance, GemSpireConfig::new)
    );
}
