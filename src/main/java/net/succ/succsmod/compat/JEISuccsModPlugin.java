package net.succ.succsmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.recipe.GemPolishingRecipe;
import net.succ.succsmod.recipe.ModRecipes;
import net.succ.succsmod.screen.custom.GemPolishingTableBlockScreen;

import java.util.List;

@JeiPlugin
public class JEISuccsModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
       registration.addRecipeCategories(new GemPolishingRecipeCategory(
               registration.getJeiHelpers().getGuiHelper()));;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<GemPolishingRecipe> gemPolishingRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.GEM_POLISHING_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(GemPolishingRecipeCategory.GEM_POLISHING_RECIPE_RECIPE_TYPE, gemPolishingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
       registration.addRecipeClickArea(GemPolishingTableBlockScreen.class, 70, 30, 25, 20,
               GemPolishingRecipeCategory.GEM_POLISHING_RECIPE_RECIPE_TYPE);
    }
}
