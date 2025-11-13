package net.succ.succsmod.worldgen.tree;

import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower SHATTERBLOOM = new TreeGrower(SuccsMod.MOD_ID + ":shatterbloom",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SHATTERBLOOM_KEY), Optional.empty());

    public static final TreeGrower MYCELIAL_SPOREWOOD_KEY = new TreeGrower(SuccsMod.MOD_ID + ":mycelial_sporewood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.MYCELIAL_SPOREWOOD_KEY), Optional.empty());

    public static final TreeGrower CRYOHEART = new TreeGrower(SuccsMod.MOD_ID + ":cryoheart",
            Optional.empty(), Optional.of(ModConfiguredFeatures.CRYOHEART_KEY), Optional.empty());

    public static final TreeGrower EMBERPINE = new TreeGrower(SuccsMod.MOD_ID + ":emberpine",
            Optional.empty(), Optional.of(ModConfiguredFeatures.EMBERPINE_KEY), Optional.empty());

}