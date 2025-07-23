package net.succ.succsmod.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.succ.succsmod.util.ModTags;

public class ModToolTiers {
    // Register a new custom tool tier for Jaspilite
    public static final Tier JASPILITE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_JASPILITE_TOOL,
            1700, 8.5F, 3.5F, 12, () -> Ingredient.of(ModItems.JASPILITE));

    // Register a new custom tool tier for Sapphire
    public static final Tier SAPPHIRE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_SAPPHIRE_TOOL,
            2150, 9.25F, 4.25F, 16, () -> Ingredient.of(ModItems.SAPPHIRE));

    // Register a new custom tool tier for Sunstone
    public static final Tier SUNSTONE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_SUNSTONE_TOOL,
            2150, 9.25F, 4.25F, 16, () -> Ingredient.of(ModItems.SUNSTONE));

    // Register a new custom tool tier for Ruby
    public static final Tier RUBY = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL,
            2300, 9.6F, 4.5F, 17, () -> Ingredient.of(ModItems.RUBY));

    // Register a new custom tool tier for Malachite
    public static final Tier MALACHITE = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_MALACHITE_TOOL,
            2450, 10.0F, 4.75F, 18, () -> Ingredient.of(ModItems.MALACHITE));

    // Register a new custom tool tier for Atherium
    public static final Tier ATHERIUM = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_ATHERIUM_TOOL,
            2700, 10.5F, 5.0F, 20, () -> Ingredient.of(ModItems.ATHERIUM));




}
