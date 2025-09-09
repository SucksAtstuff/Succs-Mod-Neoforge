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
import net.succ.succsmod.worldgen.feature.config.DunePatchConfig;

public class DunePatchFeature extends Feature<DunePatchConfig> {
    public DunePatchFeature(Codec<DunePatchConfig> codec) { super(codec); }

    @Override
    public boolean place(FeaturePlaceContext<DunePatchConfig> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        DunePatchConfig cfg = context.config();

        int r = Mth.nextInt(random, cfg.minRadius(), cfg.maxRadius());
        if (r < 2) return false;

        int centerY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, origin.getX(), origin.getZ());
        BlockPos center = new BlockPos(origin.getX(), centerY, origin.getZ());

        // Abort if not flat enough (keeps dunes on plains)
        int maxDelta = 0;
        for (int dx : new int[]{-r, 0, r}) {
            for (int dz : new int[]{-r, 0, r}) {
                int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, center.getX()+dx, center.getZ()+dz);
                maxDelta = Math.max(maxDelta, Math.abs(y - centerY));
            }
        }
        if (maxDelta > 2) return false;

        int hMax = Math.max(1, Mth.floor(r * cfg.heightScale()));
        double r2 = r * r;
        boolean placedAny = false;

        for (int dx = -r; dx <= r; dx++) {
            for (int dz = -r; dz <= r; dz++) {
                double dist2 = dx*dx + dz*dz;
                if (dist2 > r2) continue;

                double t = 1.0 - (dist2 / r2); // 1 at center → 0 at edge
                int h = Mth.ceil(hMax * Math.pow(t, cfg.shapeExponent()));
                if (h <= 0) continue;

                int x = center.getX() + dx;
                int z = center.getZ() + dz;
                int baseY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);

                for (int dy = 0; dy < h; dy++) {
                    BlockPos p = new BlockPos(x, baseY + dy, z);

                    // Keep dunes dry: abort if we’d place into water
                    if (!level.getBlockState(p).getFluidState().isEmpty()) return false;

                    BlockState state = (dy < h - 1) ? cfg.coreBlock() : cfg.topBlock();
                    if (level.isEmptyBlock(p) || level.getBlockState(p).canBeReplaced()) {
                        level.setBlock(p, state, 2);
                        placedAny = true;
                    }
                }
            }
        }
        return placedAny;
    }
}
