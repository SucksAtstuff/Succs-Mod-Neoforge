package net.succ.succsmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.recipe.GemPolishingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GemPolishingRecipeCategory implements IRecipeCategory<GemPolishingRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "gem_polishing");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "textures/gui/gem_polishing_table/gem_polishing_table_gui.png");

    public static final RecipeType<GemPolishingRecipe> GEM_POLISHING_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, GemPolishingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public GemPolishingRecipeCategory(IGuiHelper helper) {
       this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
         this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GEM_POLISHING_TABLE.get()));
    }

    @Override
    public RecipeType<GemPolishingRecipe> getRecipeType() {
        return GEM_POLISHING_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.succsessentials.gem_polishing_table");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GemPolishingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().get(0));

//        builder.addSlot(RecipeIngredientRole.INPUT, 8, 7)
//                .addIngredients(NeoForgeTypes.FLUID_STACK, List.of(recipe.getFluid()))
//                .setFluidRenderer(64000, true, 16, 50)
//                .setOverlay(null, 0, 0);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }
}
