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
                .pattern("MGG")
                .pattern(" G ")
                .define('M', ModItems.MALACHITE.get())
                .define('G', ModItems.GOLD_HANDLE.get())
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
