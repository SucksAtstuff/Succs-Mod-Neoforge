package net.succ.succsmod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.succ.succsmod.worldgen.feature.config.GemSpireConfig;

public class GemSpireFeature extends Feature<GemSpireConfig> {
    public GemSpireFeature(Codec<GemSpireConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GemSpireConfig> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        GemSpireConfig cfg = context.config();

        // Pick random height and radius
        int height = Mth.nextInt(random, cfg.minHeight(), cfg.maxHeight());
        int radius = cfg.radius();

        // --- Use our custom surface finder to locate the real Nether floor ---
        int yStart = findNetherGround(level, origin.getX(), origin.getZ());
        if (yStart < level.getMinBuildHeight() + 10 || yStart > 100) {
            // Outside expected nether floor range — skip to avoid roof placement
            return false;
        }

        BlockPos base = new BlockPos(origin.getX(), yStart, origin.getZ());
        boolean placed = false;

        // --- Generate the spire geometry ---
        for (int dy = 0; dy < height; dy++) {
            int y = base.getY() + dy;
            double taper = 1.0 - (double) dy / height; // thinner near top
            int currentRadius = Math.max(1, (int) (radius * taper));

            for (int dx = -currentRadius; dx <= currentRadius; dx++) {
                for (int dz = -currentRadius; dz <= currentRadius; dz++) {
                    double dist2 = dx * dx + dz * dz;
                    if (dist2 > currentRadius * currentRadius) continue;

                    BlockPos pos = base.offset(dx, dy, dz);

                    if (level.isEmptyBlock(pos) || level.getBlockState(pos).canBeReplaced()) {
                        BlockState state = (random.nextFloat() < cfg.oreChance())
                                ? cfg.oreBlock()
                                : cfg.mainBlock();
                        level.setBlock(pos, state, 2);
                        placed = true;
                    }
                }
            }
        }

        if (placed)
            System.out.println("[Succs Essentials] Placed Gem Spire at " + base);

        return placed;
    }

    /**
     * Scans downward from mid-Nether height to find the real ground surface.
     * Skips air and lava blocks, stops when it finds a solid surface with air above it.
     */
    private int findNetherGround(WorldGenLevel level, int x, int z) {
        // Start around middle Nether height to avoid hitting the bedrock roof
        int y = 90;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, y, z);

        while (y > level.getMinBuildHeight() + 5) {
            pos.setY(y);
            BlockState state = level.getBlockState(pos);

            boolean isSolid = state.blocksMotion() && !state.isAir();
            boolean aboveIsAir = level.isEmptyBlock(pos.above());
            boolean isLava = !state.getFluidState().isEmpty() && state.getFluidState().isSource();

            if (isSolid && aboveIsAir && !isLava) {
                return y;
            }

            y--;
        }

        // Fallback if nothing found
        return 64;
    }
}
