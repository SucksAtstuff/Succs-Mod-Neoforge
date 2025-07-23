package net.succ.succsmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    // Define the FoodProperties for ROCK_CANDY
    public static final FoodProperties ROCK_CANDY = new FoodProperties.Builder()
            .nutrition(1)
            .fast()
            .alwaysEdible()
            .saturationModifier(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 5), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 200, 2), 0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 1), 0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.HARM, 20, 10), 0.01f)
            .build();

    // Define the FoodProperties for ROCK
    public static final FoodProperties ROCK = new FoodProperties.Builder()
            // Set the nutrition value (hunger restored) to 4
            .nutrition(4)
            // Make the food consume quickly
            .fast()
            // Set the saturation modifier to 0.5F (how much saturation is restored)
            .saturationModifier(0.5F)
            // Add an effect that grants a speed boost for 200 ticks (10 seconds) at level 1, with a 100% chance
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1), 1.0f)
            // Build the FoodProperties instance
            .build();

    // Define the FoodProperties for Garlic
    public static final FoodProperties GARLIC = new FoodProperties.Builder()
            // Set the nutrition value (hunger restored) to 2
            .nutrition(2)
            // Set the saturation modifier to 0.5F (how much saturation is restored)
            .saturationModifier(0.5F)
            // Build the FoodProperties instance
            .build();

    // Define the FoodProperties for Garlic Bread
    public static final FoodProperties GARLIC_BREAD = new FoodProperties.Builder()
            // Set the nutrition value (hunger restored) to 6
            .nutrition(6)
            // Set the saturation modifier to 3F (how much saturation is restored)
            .saturationModifier(3F)
            // Build the FoodProperties instance
            .build();

    // Define the FoodProperties for Frost Fruit
    public static final FoodProperties FROST_FRUIT = new FoodProperties.Builder()
            // Set the nutrition value (hunger restored) to 4
            .nutrition(2)
            // Set the saturation modifier to 0.3F (how much saturation is restored)
            .saturationModifier(0.3F)
            // Build the FoodProperties instance
            .build();

}


