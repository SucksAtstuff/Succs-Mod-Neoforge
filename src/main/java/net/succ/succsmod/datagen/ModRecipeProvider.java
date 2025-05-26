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
        // List of smeltable ores
        List<ItemLike> ATHERIUM_SMELTABLES = List.of(ModBlocks.ATHERIUM_ORE, ModBlocks.DEEPSLATE_ATHERIUM_ORE, ModBlocks.NETHER_ATHERIUM_ORE, ModBlocks.END_ATHERIUM_ORE);
        List<ItemLike> RUBY_SMELTABLES = List.of(ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.NETHER_RUBY_ORE, ModBlocks.END_RUBY_ORE);
        List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModBlocks.NETHER_SAPPHIRE_ORE, ModBlocks.END_SAPPHIRE_ORE);
        List<ItemLike> SUNSTONE_SMELTABLES = List.of(ModBlocks.SUNSTONE_ORE, ModBlocks.DEEPSLATE_SUNSTONE_ORE, ModBlocks.NETHER_SUNSTONE_ORE, ModBlocks.END_SUNSTONE_ORE);
        List<ItemLike> MALACHITE_SMELTABLES = List.of(ModBlocks.MALACHITE_ORE, ModBlocks.DEEPSLATE_MALACHITE_ORE, ModBlocks.NETHER_MALACHITE_ORE, ModBlocks.END_MALACHITE_ORE);

        // Register smelting and blasting recipes for Atherium ores
        oreSmelting(recipeOutput, ATHERIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ATHERIUM.get(), 0.25f, 200, "atherium");
        oreBlasting(recipeOutput, ATHERIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ATHERIUM.get(), 0.25f, 100, "atherium");

        // Register smelting and blasting recipes for Ruby ores
        oreSmelting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.25f, 200, "ruby");
        oreBlasting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.25f, 100, "ruby");

        // Register smelting and blasting recipes for Sapphire ores
        oreSmelting(recipeOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 200, "sapphire");
        oreBlasting(recipeOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 100, "sapphire");

        // Register smelting and blasting recipes for Sunstone ores
        oreSmelting(recipeOutput, SUNSTONE_SMELTABLES, RecipeCategory.MISC, ModItems.SUNSTONE.get(), 0.25f, 200, "sunstone");
        oreBlasting(recipeOutput, SUNSTONE_SMELTABLES, RecipeCategory.MISC, ModItems.SUNSTONE.get(), 0.25f, 100, "sunstone");

        // Register smelting and blasting recipes for Malachite ores
        oreSmelting(recipeOutput, MALACHITE_SMELTABLES, RecipeCategory.MISC, ModItems.MALACHITE.get(), 0.25f, 200, "malachite");
        oreBlasting(recipeOutput, MALACHITE_SMELTABLES, RecipeCategory.MISC, ModItems.MALACHITE.get(), 0.25f, 100, "malachite");

        // Register shapeless recipe to convert Atherium Block back to Atherium items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ATHERIUM.get(), 9)
                        .requires(ModBlocks.ATHERIUM_BLOCK)
                        .unlockedBy("has_atherium_block", has(ModBlocks.ATHERIUM_BLOCK)).save(recipeOutput);

        // Register shaped recipe for Atherium Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ATHERIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ATHERIUM.get())
                .unlockedBy("has_atherium", has(ModItems.ATHERIUM.get())).save(recipeOutput);

        // Register shaped recipe for Gold Handle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_HANDLE.get())
                .pattern("   ")
                .pattern(" G ")
                .pattern(" G ")
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(recipeOutput);

        // Register shapeless recipe to convert Ruby Block back to Ruby items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                        .requires(ModBlocks.RUBY_BLOCK)
                        .unlockedBy("has_ruby_block", has(ModBlocks.RUBY_BLOCK)).save(recipeOutput);

        // Register shaped recipe for Ruby Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .unlockedBy("has_ruby", has(ModItems.RUBY.get())).save(recipeOutput);

        // Register shapeless recipe to convert Sapphire Block back to Sapphire items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                        .requires(ModBlocks.SAPPHIRE_BLOCK)
                        .unlockedBy("has_sapphire_block", has(ModBlocks.SAPPHIRE_BLOCK)).save(recipeOutput);

        // Register shaped recipe for Sapphire Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy("has_sapphire", has(ModItems.SAPPHIRE.get())).save(recipeOutput);

        // Register shapeless recipe to convert Sunstone Block back to Sunstone items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SUNSTONE.get(), 9)
                        .requires(ModBlocks.SUNSTONE_BLOCK)
                        .unlockedBy("has_sunstone_block", has(ModBlocks.SUNSTONE_BLOCK)).save(recipeOutput);

        // Register shaped recipe for Sunstone Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SUNSTONE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SUNSTONE.get())
                .unlockedBy("has_sunstone", has(ModItems.SUNSTONE.get())).save(recipeOutput);

        // Register shapeless recipe to convert Malachite Block back to Malachite items
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MALACHITE.get(), 9)
                        .requires(ModBlocks.MALACHITE_BLOCK)
                        .unlockedBy("has_malachite_block", has(ModBlocks.MALACHITE_BLOCK)).save(recipeOutput);

        // Register shaped recipe for Malachite Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MALACHITE_BLOCK.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModItems.MALACHITE.get())
                .unlockedBy("has_malachite", has(ModItems.MALACHITE.get())).save(recipeOutput);

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
