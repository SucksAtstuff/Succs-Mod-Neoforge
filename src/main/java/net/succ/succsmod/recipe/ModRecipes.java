package net.succ.succsmod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, SuccsMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, SuccsMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GemPolishingRecipe>> GEM_POLISHING_SERIALIZER =
            SERIALIZERS.register("gem_polishing", GemPolishingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<GemPolishingRecipe>> GEM_POLISHING_TYPE =
            TYPES.register("gem_polishing", () -> new RecipeType<GemPolishingRecipe>() {
                @Override
                public String toString() {
                    return "gem_polishing";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
