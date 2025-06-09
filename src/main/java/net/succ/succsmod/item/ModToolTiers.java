package net.succ.succsmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.util.ModTags;


import java.util.List;

public class ModToolTiers {
    // Register a new custom tool tier for Sapphire
    public static final Tier SAPPHIRE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL,
            1561, 9.5F, 3.0F, 12, () -> Ingredient.of(ModItems.SAPPHIRE));

    // Register a new custom tool tier for Sunstone
    public static final Tier SUNSTONE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_SUNSTONE_TOOL,
            1561, 9.5F, 3.0F, 12, () -> Ingredient.of(ModItems.SUNSTONE));

    // Register a new custom tool tier for Ruby
    public static final Tier RUBY = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL,
            1600, 10.0F, 4.0F, 14, () -> Ingredient.of(ModItems.RUBY));

    // Register a new custom tool tier for Malachite
    public static final Tier MALACHITE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_MALACHITE_TOOL,
            1854, 10.5F, 5.0F, 16, () -> Ingredient.of(ModItems.MALACHITE));

    // Register a new custom tool tier for Atherium
    public static final Tier ATHERIUM = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_ATHERIUM_TOOL,
            2565, 12.0F, 5.0F, 18, () -> Ingredient.of(ModItems.ATHERIUM));




}
