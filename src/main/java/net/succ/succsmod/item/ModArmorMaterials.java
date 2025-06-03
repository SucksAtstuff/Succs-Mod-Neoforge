package net.succ.succsmod.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.succ.succsmod.SuccsMod;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static Holder<ArmorMaterial> ATHERIUM_ARMOR_MATERIAL = register("atherium",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 5);        // +1 from Malachite
                attribute.put(ArmorItem.Type.LEGGINGS, 8);     // +1
                attribute.put(ArmorItem.Type.CHESTPLATE, 10);  // +1
                attribute.put(ArmorItem.Type.HELMET, 5);       // +1
                attribute.put(ArmorItem.Type.BODY, 6);
            }), 18, 5.0f, 0.3f, () -> ModItems.ATHERIUM.get());

    public static Holder<ArmorMaterial> MALACHITE_ARMOR_MATERIAL = register("malachite",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 4);        // +1 from Ruby
                attribute.put(ArmorItem.Type.LEGGINGS, 7);     // +1
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);   // +1
                attribute.put(ArmorItem.Type.HELMET, 4);       // +1
                attribute.put(ArmorItem.Type.BODY, 5);
            }), 16, 4.0f, 0.2f, () -> ModItems.MALACHITE.get());

    public static Holder<ArmorMaterial> RUBY_ARMOR_MATERIAL = register("ruby",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 4);        // +1 from Sapphire/Sunstone
                attribute.put(ArmorItem.Type.LEGGINGS, 7);     // +1
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);   // +1
                attribute.put(ArmorItem.Type.HELMET, 4);       // +1
                attribute.put(ArmorItem.Type.BODY, 4);
            }), 14, 3.5f, 0.1f, () -> ModItems.RUBY.get());

    public static Holder<ArmorMaterial> SAPPHIRE_ARMOR_MATERIAL = register("sapphire",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 3);        // Matches Netherite
                attribute.put(ArmorItem.Type.LEGGINGS, 6);
                attribute.put(ArmorItem.Type.CHESTPLATE, 8);
                attribute.put(ArmorItem.Type.HELMET, 3);
                attribute.put(ArmorItem.Type.BODY, 3);
            }), 12, 3.0f, 0.1f, () -> ModItems.SAPPHIRE.get());

    public static Holder<ArmorMaterial> SUNSTONE_ARMOR_MATERIAL = register("sunstone",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 3);        // Matches Netherite
                attribute.put(ArmorItem.Type.LEGGINGS, 6);
                attribute.put(ArmorItem.Type.CHESTPLATE, 8);
                attribute.put(ArmorItem.Type.HELMET, 3);
                attribute.put(ArmorItem.Type.BODY, 3);
            }), 12, 3.0f, 0.1f, () -> ModItems.SUNSTONE.get());

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
