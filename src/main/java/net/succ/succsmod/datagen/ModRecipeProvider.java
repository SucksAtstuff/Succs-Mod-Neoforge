package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.util.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput){

        // Register recipe for crafting the Gem Polishing Table
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEM_POLISHING_TABLE.get(), 1)
                .pattern("QQQ")
                .pattern("BIB")
                .pattern("BBB")
                .define('Q', Items.QUARTZ_BLOCK)
                .define('I', Items.IRON_INGOT)
                .define('B', Items.BLACKSTONE)
                .unlockedBy(getHasName(Items.QUARTZ_BLOCK), has(Items.QUARTZ_BLOCK))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.BLACKSTONE), has(Items.BLACKSTONE))
                .save(recipeOutput);

        // Atherium Block Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ATHERIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ATHERIUM.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ATHERIUM.get(), 9)
                .requires(ModBlocks.ATHERIUM_BLOCK.get())
                .unlockedBy("has_atherium_block", has(ModBlocks.ATHERIUM_BLOCK.get()))
                .save(recipeOutput);

// Atherium Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_PICKAXE.get())
                .pattern("AAA")
                .pattern(" G ")
                .pattern(" G ")
                .define('A', ModItems.ATHERIUM.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_AXE.get())
                .pattern("AA ")
                .pattern("AG ")
                .pattern(" G ")
                .define('A', ModItems.ATHERIUM.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_SHOVEL.get())
                .pattern(" A ")
                .pattern(" G ")
                .pattern(" G ")
                .define('A', ModItems.ATHERIUM.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_HOE.get())
                .pattern("AA ")
                .pattern(" G ")
                .pattern(" G ")
                .define('A', ModItems.ATHERIUM.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" G ")
                .define('A', ModItems.ATHERIUM.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_HAMMER.get())
                .pattern("AAA")
                .pattern("AGA")
                .pattern(" G ")
                .define('A', ModItems.ATHERIUM.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_PAXEL.get())
                .pattern("APS")
                .pattern(" G ")
                .pattern(" G ")
                .define('A', ModItems.ATHERIUM_AXE.get())
                .define('P', ModItems.ATHERIUM_PICKAXE.get())
                .define('S', ModItems.ATHERIUM_SHOVEL.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        // Atherium Armour Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .define('A', ModItems.ATHERIUM.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ATHERIUM.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.ATHERIUM.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ATHERIUM_BOOTS.get())
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.ATHERIUM.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get()))
                .save(recipeOutput);

        // Malachite Block Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MALACHITE_BLOCK.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModItems.MALACHITE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MALACHITE.get(), 9)
                .requires(ModBlocks.MALACHITE_BLOCK.get())
                .unlockedBy("has_malachite_block", has(ModBlocks.MALACHITE_BLOCK.get()))
                .save(recipeOutput);

        // Malachite Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_PICKAXE.get())
                .pattern("MMM")
                .pattern(" G ")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_AXE.get())
                .pattern("MM ")
                .pattern("MG ")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_SHOVEL.get())
                .pattern(" M ")
                .pattern(" G ")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_HOE.get())
                .pattern("MM ")
                .pattern(" G ")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_SWORD.get())
                .pattern(" M ")
                .pattern(" M ")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_HAMMER.get())
                .pattern("MMM")
                .pattern("MGM")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_PAXEL.get())
                .pattern("MAP")
                .pattern(" G ")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE_AXE.get())
                .define('A', ModItems.MALACHITE_PICKAXE.get())
                .define('P', ModItems.MALACHITE_SHOVEL.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        // Malachite Armour Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_HELMET.get())
                .pattern("MMM")
                .pattern("M M")
                .define('M', ModItems.MALACHITE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_CHESTPLATE.get())
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModItems.MALACHITE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_LEGGINGS.get())
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M")
                .define('M', ModItems.MALACHITE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MALACHITE_BOOTS.get())
                .pattern("M M")
                .pattern("M M")
                .define('M', ModItems.MALACHITE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get()))
                .save(recipeOutput);

        // Ruby Block Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy("has_ruby_block", has(ModBlocks.RUBY_BLOCK.get()))
                .save(recipeOutput);

        // Ruby Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_PICKAXE.get())
                .pattern("RRR")
                .pattern(" G ")
                .pattern(" G ")
                .define('R', ModItems.RUBY.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_AXE.get())
                .pattern("RR ")
                .pattern("RG ")
                .pattern(" G ")
                .define('R', ModItems.RUBY.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_SHOVEL.get())
                .pattern(" R ")
                .pattern(" G ")
                .pattern(" G ")
                .define('R', ModItems.RUBY.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_HOE.get())
                .pattern("RR ")
                .pattern(" G ")
                .pattern(" G ")
                .define('R', ModItems.RUBY.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_SWORD.get())
                .pattern(" R ")
                .pattern(" R ")
                .pattern(" G ")
                .define('R', ModItems.RUBY.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_HAMMER.get())
                .pattern("RRR")
                .pattern("RGR")
                .pattern(" G ")
                .define('R', ModItems.RUBY.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_PAXEL.get())
                .pattern("RAP")
                .pattern(" G ")
                .pattern(" G ")
                .define('R', ModItems.RUBY_AXE.get())
                .define('A', ModItems.RUBY_PICKAXE.get())
                .define('P', ModItems.RUBY_SHOVEL.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        // Ruby Armour Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_HELMET.get())
                .pattern("RRR")
                .pattern("R R")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_CHESTPLATE.get())
                .pattern("R R")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_LEGGINGS.get())
                .pattern("RRR")
                .pattern("R R")
                .pattern("R R")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_BOOTS.get())
                .pattern("R R")
                .pattern("R R")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(recipeOutput);

        // Sapphire Block Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy("has_sapphire_block", has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(recipeOutput);

        // Sapphire Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_PICKAXE.get())
                .pattern("SSS")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SAPPHIRE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_AXE.get())
                .pattern("SS ")
                .pattern("SG ")
                .pattern(" G ")
                .define('S', ModItems.SAPPHIRE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_SHOVEL.get())
                .pattern(" S ")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SAPPHIRE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_HOE.get())
                .pattern("SS ")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SAPPHIRE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_SWORD.get())
                .pattern(" S ")
                .pattern(" S ")
                .pattern(" G ")
                .define('S', ModItems.SAPPHIRE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_HAMMER.get())
                .pattern("SSS")
                .pattern("SGS")
                .pattern(" G ")
                .define('S', ModItems.SAPPHIRE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_PAXEL.get())
                .pattern("SAP")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SAPPHIRE_AXE.get())
                .define('A', ModItems.SAPPHIRE_PICKAXE.get())
                .define('P', ModItems.SAPPHIRE_SHOVEL.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        // Sapphire Armour Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_HELMET.get())
                .pattern("SSS")
                .pattern("S S")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_CHESTPLATE.get())
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_LEGGINGS.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAPPHIRE_BOOTS.get())
                .pattern("S S")
                .pattern("S S")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get()))
                .save(recipeOutput);

        // Sunstone Block Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SUNSTONE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SUNSTONE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SUNSTONE.get(), 9)
                .requires(ModBlocks.SUNSTONE_BLOCK.get())
                .unlockedBy("has_sunstone_block", has(ModBlocks.SUNSTONE_BLOCK.get()))
                .save(recipeOutput);

        // Sunstone Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_PICKAXE.get())
                .pattern("SSS")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SUNSTONE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_AXE.get())
                .pattern("SS ")
                .pattern("SG ")
                .pattern(" G ")
                .define('S', ModItems.SUNSTONE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_SHOVEL.get())
                .pattern(" S ")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SUNSTONE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_HOE.get())
                .pattern("SS ")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SUNSTONE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_SWORD.get())
                .pattern(" S ")
                .pattern(" S ")
                .pattern(" G ")
                .define('S', ModItems.SUNSTONE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_HAMMER.get())
                .pattern("SSS")
                .pattern("SGS")
                .pattern(" G ")
                .define('S', ModItems.SUNSTONE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_PAXEL.get())
                .pattern("SAP")
                .pattern(" G ")
                .pattern(" G ")
                .define('S', ModItems.SUNSTONE_AXE.get())
                .define('A', ModItems.SUNSTONE_PICKAXE.get())
                .define('P', ModItems.SUNSTONE_SHOVEL.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        // Sunstone Armour Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_HELMET.get())
                .pattern("SSS")
                .pattern("S S")
                .define('S', ModItems.SUNSTONE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_CHESTPLATE.get())
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SUNSTONE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_LEGGINGS.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ModItems.SUNSTONE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUNSTONE_BOOTS.get())
                .pattern("S S")
                .pattern("S S")
                .define('S', ModItems.SUNSTONE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get()))
                .save(recipeOutput);

        // Jaspilite Block Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.JASPILITE_BLOCK.get())
                .pattern("JJJ")
                .pattern("JJJ")
                .pattern("JJJ")
                .define('J', ModItems.JASPILITE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.JASPILITE.get(), 9)
                .requires(ModBlocks.JASPILITE_BLOCK.get())
                .unlockedBy("has_jaspilite_block", has(ModBlocks.JASPILITE_BLOCK.get()))
                .save(recipeOutput);

        // Jaspilite Tools
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_PICKAXE.get())
                .pattern("JJJ")
                .pattern(" G ")
                .pattern(" G ")
                .define('J', ModItems.JASPILITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_AXE.get())
                .pattern("JJ ")
                .pattern("JG ")
                .pattern(" G ")
                .define('J', ModItems.JASPILITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_SHOVEL.get())
                .pattern(" J ")
                .pattern(" G ")
                .pattern(" G ")
                .define('J', ModItems.JASPILITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_HOE.get())
                .pattern("JJ ")
                .pattern(" G ")
                .pattern(" G ")
                .define('J', ModItems.JASPILITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_SWORD.get())
                .pattern(" J ")
                .pattern(" J ")
                .pattern(" G ")
                .define('J', ModItems.JASPILITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_HAMMER.get())
                .pattern("JJJ")
                .pattern("JGJ")
                .pattern(" G ")
                .define('J', ModItems.JASPILITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_PAXEL.get())
                .pattern("ASP")
                .pattern(" G ")
                .pattern(" G ")
                .define('A', ModItems.JASPILITE_AXE.get())
                .define('S', ModItems.JASPILITE_SHOVEL.get())
                .define('P', ModItems.JASPILITE_PICKAXE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        // Jaspilite Armour Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_HELMET.get())
                .pattern("JJJ")
                .pattern("J J")
                .define('J', ModItems.JASPILITE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_CHESTPLATE.get())
                .pattern("J J")
                .pattern("JJJ")
                .pattern("JJJ")
                .define('J', ModItems.JASPILITE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_LEGGINGS.get())
                .pattern("JJJ")
                .pattern("J J")
                .pattern("J J")
                .define('J', ModItems.JASPILITE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.JASPILITE_BOOTS.get())
                .pattern("J J")
                .pattern("J J")
                .define('J', ModItems.JASPILITE.get())
                .unlockedBy("has_jaspilite", has(ModItems.JASPILITE.get()))
                .save(recipeOutput);

        // Register shaped recipe for Gold Handle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_HANDLE.get())
                .pattern("   ")
                .pattern(" G ")
                .pattern(" G ")
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(recipeOutput);

        // Register shapeless recipe for Garlic Bread
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GARLIC_BREAD.get())
                .requires(ModItems.GARLIC.get())
                .requires(Items.BREAD)
                .unlockedBy("has_garlic", has(ModItems.GARLIC.get()))
                .unlockedBy("has_bread", has(Items.BREAD))
                .save(recipeOutput, "garlic_bread");


        // Register recipes for turning logs to planks
        planksFromLog(recipeOutput, ModBlocks.SHATTERBLOOM_PLANKS.get(), ModTags.Items.SHATTERBLOOM_LOGS, 4);
        woodFromLogs(recipeOutput, ModBlocks.SHATTERBLOOM_WOOD.get(), ModBlocks.SHATTERBLOOM_LOG.get());
        planksFromLog(recipeOutput, ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get(), ModTags.Items.MYCELIAL_SPOREWOOD_LOGS, 4);
        woodFromLogs(recipeOutput, ModBlocks.MYCELIAL_SPOREWOOD_WOOD.get(), ModBlocks.MYCELIAL_SPOREWOOD_LOG.get());
        planksFromLog(recipeOutput, ModBlocks.CRYOHEART_PLANKS.get(), ModTags.Items.CRYOHEART_LOGS, 4);
        woodFromLogs(recipeOutput, ModBlocks.CRYOHEART_WOOD.get(), ModBlocks.CRYOHEART_LOG.get());


        // Register recipes for turning planks to door and trapdoors
        doorBuilder(ModBlocks.SHATTERBLOOM_DOOR.get(), Ingredient.of(ModBlocks.SHATTERBLOOM_PLANKS.get())).group("shatterbloom")
                .unlockedBy("has_shatterbloom_planks", has(ModBlocks.SHATTERBLOOM_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.SHATTERBLOOM_TRAPDOOR.get(), Ingredient.of(ModBlocks.SHATTERBLOOM_PLANKS.get())).group("shatterbloom")
                .unlockedBy("has_shatterbloom_planks", has(ModBlocks.SHATTERBLOOM_PLANKS.get())).save(recipeOutput);
        doorBuilder(ModBlocks.MYCELIAL_SPOREWOOD_DOOR.get(), Ingredient.of(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).group("mycelial_sporewood")
                .unlockedBy("has_mycelial_sporewood_planks", has(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.MYCELIAL_SPOREWOOD_TRAPDOOR.get(), Ingredient.of(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).group("mycelial_sporewood")
                .unlockedBy("has_mycelial_sporewood_planks", has(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).save(recipeOutput);
        doorBuilder(ModBlocks.CRYOHEART_DOOR.get(), Ingredient.of(ModBlocks.CRYOHEART_PLANKS.get())).group("cryoheart")
                .unlockedBy("has_cryoheart_planks", has(ModBlocks.CRYOHEART_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.CRYOHEART_TRAPDOOR.get(), Ingredient.of(ModBlocks.CRYOHEART_PLANKS.get())).group("cryoheart")
                .unlockedBy("has_cryoheart_planks", has(ModBlocks.CRYOHEART_PLANKS.get())).save(recipeOutput);

        // Register recipes for turning planks slabs and stairs
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHATTERBLOOM_SLAB.get(), ModBlocks.SHATTERBLOOM_PLANKS.get());
        stairBuilder(ModBlocks.SHATTERBLOOM_STAIRS.get(), Ingredient.of(ModBlocks.SHATTERBLOOM_PLANKS.get())).group("shatterbloom")
                .unlockedBy("has_shatterbloom_planks", has(ModBlocks.SHATTERBLOOM_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MYCELIAL_SPOREWOOD_SLAB.get(), ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get());
        stairBuilder(ModBlocks.MYCELIAL_SPOREWOOD_STAIRS.get(), Ingredient.of(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).group("mycelial_sporewood")
                .unlockedBy("has_mycelial_sporewood_planks", has(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYOHEART_SLAB.get(), ModBlocks.CRYOHEART_PLANKS.get());
        stairBuilder(ModBlocks.CRYOHEART_STAIRS.get(), Ingredient.of(ModBlocks.CRYOHEART_PLANKS.get())).group("cryoheart")
                .unlockedBy("has_cryoheart_planks", has(ModBlocks.CRYOHEART_PLANKS.get())).save(recipeOutput);

        // Register recipes for turning planks to buttons and pressure plates
        pressurePlate(recipeOutput, ModBlocks.SHATTERBLOOM_PRESSURE_PLATE.get(), ModBlocks.SHATTERBLOOM_PLANKS.get());
        buttonBuilder(ModBlocks.SHATTERBLOOM_BUTTON.get(), Ingredient.of(ModBlocks.SHATTERBLOOM_PLANKS.get())).group("shatterbloom")
                .unlockedBy("has_shatterbloom_planks", has(ModBlocks.SHATTERBLOOM_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.MYCELIAL_SPOREWOOD_PRESSURE_PLATE.get(), ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get());
        buttonBuilder(ModBlocks.MYCELIAL_SPOREWOOD_BUTTON.get(), Ingredient.of(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).group("mycelial_sporewood")
                .unlockedBy("has_mycelial_sporewood_planks", has(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.CRYOHEART_PRESSURE_PLATE.get(), ModBlocks.CRYOHEART_PLANKS.get());
        buttonBuilder(ModBlocks.CRYOHEART_BUTTON.get(), Ingredient.of(ModBlocks.CRYOHEART_PLANKS.get())).group("cryoheart")
                .unlockedBy("has_cryoheart_planks", has(ModBlocks.CRYOHEART_PLANKS.get())).save(recipeOutput);

        // Register recipes for turning planks to fences and fence gates
        fenceBuilder(ModBlocks.SHATTERBLOOM_FENCE.get(), Ingredient.of(ModBlocks.SHATTERBLOOM_PLANKS.get())).group("shatterbloom")
                .unlockedBy("has_shatterbloom_planks", has(ModBlocks.SHATTERBLOOM_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.SHATTERBLOOM_FENCE_GATE.get(), Ingredient.of(ModBlocks.SHATTERBLOOM_PLANKS.get())).group("shatterbloom")
                .unlockedBy("has_shatterbloom_planks", has(ModBlocks.SHATTERBLOOM_PLANKS.get())).save(recipeOutput);
        fenceBuilder(ModBlocks.MYCELIAL_SPOREWOOD_FENCE.get(), Ingredient.of(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).group("mycelial_sporewood")
                .unlockedBy("has_mycelial_sporewood_planks", has(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.MYCELIAL_SPOREWOOD_FENCE_GATE.get(), Ingredient.of(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).group("mycelial_sporewood")
                .unlockedBy("has_mycelial_sporewood_planks", has(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get())).save(recipeOutput);
        fenceBuilder(ModBlocks.CRYOHEART_FENCE.get(), Ingredient.of(ModBlocks.CRYOHEART_PLANKS.get())).group("cryoheart")
                .unlockedBy("has_cryoheart_planks", has(ModBlocks.CRYOHEART_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.CRYOHEART_FENCE_GATE.get(), Ingredient.of(ModBlocks.CRYOHEART_PLANKS.get())).group("cryoheart")
                .unlockedBy("has_cryoheart_planks", has(ModBlocks.CRYOHEART_PLANKS.get())).save(recipeOutput);

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List <ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, SuccsMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
