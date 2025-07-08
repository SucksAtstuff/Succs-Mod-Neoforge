package net.succ.succsmod.worldgen.tree;

import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower SHATTERBLOOM = new TreeGrower(SuccsMod.MOD_ID + ":shatterbloom",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SHATTERBLOOM_KEY), Optional.empty());
}