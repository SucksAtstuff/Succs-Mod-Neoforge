package net.succ.succsmod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

public record GemPolishingRecipe(Ingredient inputItem, ItemStack output) implements Recipe<GemPolishingRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(GemPolishingRecipeInput pInput, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(GemPolishingRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.GEM_POLISHING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.GEM_POLISHING_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<GemPolishingRecipe> {
        public static final MapCodec<GemPolishingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(GemPolishingRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(GemPolishingRecipe::output)
        ).apply(inst, GemPolishingRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, GemPolishingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, GemPolishingRecipe::inputItem,
                        ItemStack.STREAM_CODEC, GemPolishingRecipe::output,
                        GemPolishingRecipe::new);

        @Override
        public MapCodec<GemPolishingRecipe> codec() {
            return CODEC;
        }



        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GemPolishingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
