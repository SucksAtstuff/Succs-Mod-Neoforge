package net.succ.succsmod.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.item.custom.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SuccsMod.MOD_ID);

    public static final DeferredItem<Item> DIRTY_ATHERIUM = ITEMS.register("dirty_atherium",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ATHERIUM = ITEMS.register("atherium",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_SUNSTONE = ITEMS.register("dirty_sunstone",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SUNSTONE = ITEMS.register("sunstone",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_RUBY = ITEMS.register("dirty_ruby",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_SAPPHIRE = ITEMS.register("dirty_sapphire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MALACHITE = ITEMS.register("malachite",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_MALACHITE = ITEMS.register("dirty_malachite",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GOLD_HANDLE = ITEMS.register("gold_handle",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GARLIC = ITEMS.register("garlic",
            () -> new GarlicItem(ModBlocks.GARLIC_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> GARLIC_BREAD = ITEMS.register("garlic_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GARLIC_BREAD)));

    public static final DeferredItem<Item> ROCK = ITEMS.register("rock",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROCK)));

    public static final DeferredItem<Item> ROCK_CANDY = ITEMS.register("rock_candy",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROCK_CANDY)));

    // Atherium Tools
    public static final DeferredItem<SwordItem> ATHERIUM_SWORD = ITEMS.register("atherium_sword",
            () -> new SwordItem(ModToolTiers.ATHERIUM, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.ATHERIUM, 4, -2.4F))));

    public static final DeferredItem<Item> ATHERIUM_PICKAXE = ITEMS.register("atherium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ATHERIUM, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.ATHERIUM, 2, -2.8F))));

    public static final DeferredItem<Item> ATHERIUM_AXE = ITEMS.register("atherium_axe",
            () -> new AxeItem(ModToolTiers.ATHERIUM, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.ATHERIUM, 7, -3F))));

    public static final DeferredItem<Item> ATHERIUM_SHOVEL = ITEMS.register("atherium_shovel",
            () -> new ShovelItem(ModToolTiers.ATHERIUM, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.ATHERIUM, 2, -3F))));

    public static final DeferredItem<Item> ATHERIUM_HOE = ITEMS.register("atherium_hoe",
            () -> new HoeItem(ModToolTiers.ATHERIUM, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.ATHERIUM, -4, 1F))));

    public static final DeferredItem<Item> ATHERIUM_HAMMER = ITEMS.register("atherium_hammer",
            () -> new HammerItem(ModToolTiers.ATHERIUM, new Item.Properties()
                    .attributes(HammerItem.createAttributes(ModToolTiers.ATHERIUM, 10, -3.5F))));

    public static final DeferredItem<Item> ATHERIUM_PAXEL = ITEMS.register("atherium_paxel",
            () -> new PaxelItem(ModToolTiers.ATHERIUM, new Item.Properties()
                    .attributes(PaxelItem.createAttributes(ModToolTiers.ATHERIUM, 4, -3F))));

    // Malachite Tools
    public static final DeferredItem<MobEffectSwordItem> MALACHITE_SWORD = ITEMS.register("malachite_sword",
            () -> new MobEffectSwordItem(ModToolTiers.MALACHITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.MALACHITE, 3, -2.4F)), MobEffects.POISON, 100, 1)); // 100 ticks = 5 seconds, amplifier 0 = Poison I

    public static final DeferredItem<Item> MALACHITE_PICKAXE = ITEMS.register("malachite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.MALACHITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.MALACHITE, 2, -2.8F))));

    public static final DeferredItem<Item> MALACHITE_AXE = ITEMS.register("malachite_axe",
            () -> new AxeItem(ModToolTiers.MALACHITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.MALACHITE, 6, -3F))));

    public static final DeferredItem<Item> MALACHITE_SHOVEL = ITEMS.register("malachite_shovel",
            () -> new ShovelItem(ModToolTiers.MALACHITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.MALACHITE, 2, -3F))));

    public static final DeferredItem<Item> MALACHITE_HOE = ITEMS.register("malachite_hoe",
            () -> new HoeItem(ModToolTiers.MALACHITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.MALACHITE, -5, 1F))));

    public static final DeferredItem<Item> MALACHITE_HAMMER = ITEMS.register("malachite_hammer",
            () -> new HammerItem(ModToolTiers.MALACHITE, new Item.Properties()
                    .attributes(HammerItem.createAttributes(ModToolTiers.MALACHITE, 8, -3.5F))));

    public static final DeferredItem<Item> MALACHITE_PAXEL = ITEMS.register("malachite_paxel",
            () -> new PaxelItem(ModToolTiers.MALACHITE, new Item.Properties()
                    .attributes(PaxelItem.createAttributes(ModToolTiers.MALACHITE, 3, -3F))));

    // Ruby Tools
    public static final DeferredItem<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new SwordItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.RUBY, 3, -2.4F))));

    public static final DeferredItem<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.RUBY, 2, -2.8F))));

    public static final DeferredItem<Item> RUBY_AXE = ITEMS.register("ruby_axe",
            () -> new AxeItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.RUBY, 6, -3F))));

    public static final DeferredItem<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.RUBY, 2, -3F))));

    public static final DeferredItem<Item> RUBY_HOE = ITEMS.register("ruby_hoe",
            () -> new HoeItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.RUBY, -4, 1F))));

    public static final DeferredItem<Item> RUBY_HAMMER = ITEMS.register("ruby_hammer",
            () -> new HammerItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(HammerItem.createAttributes(ModToolTiers.RUBY, 9, -3.5F))));

    public static final DeferredItem<Item> RUBY_PAXEL = ITEMS.register("ruby_paxel",
            () -> new PaxelItem(ModToolTiers.RUBY, new Item.Properties()
                    .attributes(PaxelItem.createAttributes(ModToolTiers.RUBY, 2, -3F))));

    // Sapphire Tools
    public static final DeferredItem<SwordItem> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new SwordItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SAPPHIRE, 3, -2.4F))));

    public static final DeferredItem<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.SAPPHIRE, 2, -2.8F))));

    public static final DeferredItem<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.SAPPHIRE, 6, -3F))));

    public static final DeferredItem<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            () -> new ShovelItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.SAPPHIRE, 2, -3F))));

    public static final DeferredItem<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            () -> new HoeItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.SAPPHIRE, -3, 1F))));

    public static final DeferredItem<Item> SAPPHIRE_HAMMER = ITEMS.register("sapphire_hammer",
            () -> new HammerItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(HammerItem.createAttributes(ModToolTiers.SAPPHIRE, 8, -3.5F))));

    public static final DeferredItem<Item> SAPPHIRE_PAXEL = ITEMS.register("sapphire_paxel",
            () -> new PaxelItem(ModToolTiers.SAPPHIRE, new Item.Properties()
                    .attributes(PaxelItem.createAttributes(ModToolTiers.SAPPHIRE, 1, -3F))));

    // Sunstone Tools
    public static final DeferredItem<MobEffectSwordItem> SUNSTONE_SWORD = ITEMS.register("sunstone_sword",
            () -> new MobEffectSwordItem(ModToolTiers.SUNSTONE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.SUNSTONE, 3, -2.4F)), ModEffects.TRUE_FIRE_EFFECT, 25, 1)); // 25 ticks = 1.25 seconds, amplifier 0 = Fire I

    public static final DeferredItem<Item> SUNSTONE_PICKAXE = ITEMS.register("sunstone_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SUNSTONE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.SUNSTONE, 2, -2.8F))));

    public static final DeferredItem<Item> SUNSTONE_AXE = ITEMS.register("sunstone_axe",
            () -> new AxeItem(ModToolTiers.SUNSTONE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.SUNSTONE, 6, -3F))));

    public static final DeferredItem<Item> SUNSTONE_SHOVEL = ITEMS.register("sunstone_shovel",
            () -> new ShovelItem(ModToolTiers.SUNSTONE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.SUNSTONE, 2, -3F))));

    public static final DeferredItem<Item> SUNSTONE_HOE = ITEMS.register("sunstone_hoe",
            () -> new HoeItem(ModToolTiers.SUNSTONE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.SUNSTONE, -3, 1F))));

    public static final DeferredItem<Item> SUNSTONE_HAMMER = ITEMS.register("sunstone_hammer",
            () -> new HammerItem(ModToolTiers.SUNSTONE, new Item.Properties()
                    .attributes(HammerItem.createAttributes(ModToolTiers.SUNSTONE, 8, -3.5F))));

    public static final DeferredItem<Item> SUNSTONE_PAXEL = ITEMS.register("sunstone_paxel",
            () -> new PaxelItem(ModToolTiers.SUNSTONE, new Item.Properties()
                    .attributes(PaxelItem.createAttributes(ModToolTiers.SUNSTONE, 1, -3F))));

    // Atherium Armour (durability factor 42) (Full set grants Health Boost)
    public static final DeferredItem<ArmorItem> ATHERIUM_HELMET = ITEMS.register("atherium_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(42))));
    public static final DeferredItem<ArmorItem> ATHERIUM_CHESTPLATE = ITEMS.register("atherium_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(42))));
    public static final DeferredItem<ArmorItem> ATHERIUM_LEGGINGS = ITEMS.register("atherium_leggings",
            () -> new ModArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(42))));
    public static final DeferredItem<ArmorItem> ATHERIUM_BOOTS = ITEMS.register("atherium_boots",
            () -> new ModArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(42))));

    // Malachite Armour (durability factor 41)
    public static final DeferredItem<ArmorItem> MALACHITE_HELMET = ITEMS.register("malachite_helmet",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(41))));
    public static final DeferredItem<ArmorItem> MALACHITE_CHESTPLATE = ITEMS.register("malachite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(41))));
    public static final DeferredItem<ArmorItem> MALACHITE_LEGGINGS = ITEMS.register("malachite_leggings",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(41))));
    public static final DeferredItem<ArmorItem> MALACHITE_BOOTS = ITEMS.register("malachite_boots",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(41))));

    // Ruby Armour (durability factor 40)
    public static final DeferredItem<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40))));
    public static final DeferredItem<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40))));
    public static final DeferredItem<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
    public static final DeferredItem<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40))));

    // Sapphire Armour (durability factor 38)
    public static final DeferredItem<ArmorItem> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(38))));
    public static final DeferredItem<ArmorItem> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(38))));
    public static final DeferredItem<ArmorItem> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(38))));
    public static final DeferredItem<ArmorItem> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(38))));

    // Sunstone Armour (durability factor 38) (Full set grants Fire Resistance)
    public static final DeferredItem<ArmorItem> SUNSTONE_HELMET = ITEMS.register("sunstone_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(38))));
    public static final DeferredItem<ArmorItem> SUNSTONE_CHESTPLATE = ITEMS.register("sunstone_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(38))));
    public static final DeferredItem<ArmorItem> SUNSTONE_LEGGINGS = ITEMS.register("sunstone_leggings",
            () -> new ModArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(38))));
    public static final DeferredItem<ArmorItem> SUNSTONE_BOOTS = ITEMS.register("sunstone_boots",
            () -> new ModArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(38))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
