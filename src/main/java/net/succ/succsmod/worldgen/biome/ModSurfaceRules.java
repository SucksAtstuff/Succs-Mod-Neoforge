package net.succ.succsmod.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final SurfaceRules.RuleSource BLUE_TERRACOTTA = makeStateRule(Blocks.BLUE_TERRACOTTA);
    private static final SurfaceRules.RuleSource GREEN_TERRACOTTA = makeStateRule(Blocks.GREEN_TERRACOTTA);

    private static final SurfaceRules.RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);

    private static final SurfaceRules.RuleSource GLOWSTONE = makeStateRule(Blocks.GLOWSTONE);
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);


    public static SurfaceRules.RuleSource makeShatterGroveRules() {
        return SurfaceRules.sequence(
                // Only apply to SHATTERGROVE biome
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SHATTERGROVE),
                        SurfaceRules.sequence(
                                // Grass on top (floor)
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                // Dirt just beneath the top (subsurface)
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT)))
        );
    }



    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
