package net.succ.succsmod.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record DunePatchConfig(
        BlockState topBlock,
        BlockState coreBlock,
        int minRadius,
        int maxRadius,
        float heightScale,
        float shapeExponent
) implements FeatureConfiguration {
    public static final Codec<DunePatchConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockState.CODEC.fieldOf("top_block").forGetter(DunePatchConfig::topBlock),
                    BlockState.CODEC.fieldOf("core_block").forGetter(DunePatchConfig::coreBlock),
                    Codec.intRange(1, 32).fieldOf("min_radius").forGetter(DunePatchConfig::minRadius),
                    Codec.intRange(1, 64).fieldOf("max_radius").forGetter(DunePatchConfig::maxRadius),
                    Codec.floatRange(0.1F, 2.0F).fieldOf("height_scale").forGetter(DunePatchConfig::heightScale),
                    Codec.floatRange(0.5F, 3.0F).fieldOf("shape_exponent").forGetter(DunePatchConfig::shapeExponent)
            ).apply(instance, DunePatchConfig::new)
    );
}
