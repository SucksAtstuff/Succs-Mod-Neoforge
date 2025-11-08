package net.succ.succsmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.item.custom.*;
import net.succ.succsmod.sound.ModSounds;

import java.util.Collections;
import java.util.List;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SuccsMod.MOD_ID);

    /* ----------  RAW ORES / GEMS ---------- */

    public static final DeferredItem<Item> DIRTY_ATHERIUM = ITEMS.register("dirty_atherium",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ATHERIUM = ITEMS.register("atherium",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MALACHITE = ITEMS.register("malachite",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIRTY_MALACHITE = ITEMS.register("dirty_malachite",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_RUBY = ITEMS.register("dirty_ruby",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_SAPPHIRE = ITEMS.register("dirty_sapphire",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_SUNSTONE = ITEMS.register("dirty_sunstone",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SUNSTONE = ITEMS.register("sunstone",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> JASPILITE = ITEMS.register("jaspilite",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIRTY_JASPILITE = ITEMS.register("dirty_jaspilite",
            () -> new Item(new Item.Properties()));


    /* ----------   MISC   ---------- */
    public static final DeferredItem<Item> GOLD_HANDLE = ITEMS.register("gold_handle",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PUKEKO_SPAWN_EGG = ITEMS.register("pukeko_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.PUKEKO, 0xE6B8A2, 0xC4D6B0,
                    new Item.Properties()));

    public static final DeferredItem<Item> TOXIC_SLIME_SPAWN_EGG = ITEMS.register("toxic_slime_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.TOXIC_SLIME, 0xA8D8B9, 0x4C7A6D,
                    new Item.Properties()));


    public static final DeferredItem<Item> HEDGEHOG_SPAWN_EGG = ITEMS.register("hedgehog_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HEDGEHOG, 0xA0522D, 0x8B4513,
                    new Item.Properties()));

    public static final DeferredItem<Item> SCORCHED_HUSK_SPAWN_EGG = ITEMS.register("scorched_husk_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SCORCHED_HUSK, 0x1E1B1A, 0xFF6A00, new Item.Properties()));

    public static final DeferredItem<Item> ORB_OF_SUMMONING = ITEMS.register("orb_of_summoning",
            () -> new OrbOfSummoningItem(
                    new Item.Properties()
                            .stacksTo(1)
                            .fireResistant()
            ));




    public static final DeferredItem<Item> REINFORCEMENT_SMITHING_TEMPLATE =
            ITEMS.register("reinforcement_smithing_template", () ->
                    new SmithingTemplateItem(
                            Component.translatable("item.succsessentials.smithing_template.reinforcement.applies_to"),
                            Component.translatable("item.succsessentials.smithing_template.reinforcement.ingredients"),
                            Component.translatable("item.succsessentials.smithing_template.reinforcement.title"),
                            Component.translatable("item.succsessentials.smithing_template.reinforcement.base_slot_description"),
                            Component.translatable("item.succsessentials.smithing_template.reinforcement.additions_slot_description"),
                            Collections.emptyList(), // base slot overlays (optional)
                            Collections.emptyList()  // addition slot overlays (optional)
                    )
            );

    /* ----------  CROPS / FOOD / MUSIC DISCS  ---------- */

    public static final DeferredItem<Item> GARLIC = ITEMS.register("garlic",
            () -> new GarlicItem(ModBlocks.GARLIC_CROP.get(), new Item.Properties()));

    public static final DeferredItem<Item> GARLIC_BREAD = ITEMS.register("garlic_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GARLIC_BREAD)));

    public static final DeferredItem<Item> FROST_FRUIT = ITEMS.register("frost_fruit",
            () -> new FrostFruitItem());

    public static final DeferredItem<Item> ROCK = ITEMS.register("rock",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROCK)));
    public static final DeferredItem<Item> ROCK_CANDY = ITEMS.register("rock_candy",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROCK_CANDY)));

    public static final DeferredItem<Item> FUNKY_MUSIC_DISC = ITEMS.registerItem("funky_music_disc",
            p -> new Item(p.jukeboxPlayable(ModSounds.FUNKY_MUSIC_KEY)));
    public static final DeferredItem<Item> BASS_MUSIC_DISC = ITEMS.registerItem("bass_music_disc",
            p -> new Item(p.jukeboxPlayable(ModSounds.BASS_MUSIC_KEY)));
    public static final DeferredItem<Item> CLEAR_MUD_DISC = ITEMS.registerItem("clear_mud_disc",
            p -> new Item(p.jukeboxPlayable(ModSounds.CLEAR_MUD_KEY)));
    /* =====================================================================
     *                           TOOL & WEAPON TIERS
     * ===================================================================== */

    /* ----------  ✦  TIER-6  ATHERIUM  ✦  -------------------------------- */
    public static final DeferredItem<SwordItem> ATHERIUM_SWORD = ITEMS.register("atherium_sword",
            () -> new SwordItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.ATHERIUM,
                                    6,         //  Tier-6  ➜  highest sword ΔDMG
                                    -2.4F))));
    public static final DeferredItem<Item> ATHERIUM_PICKAXE = ITEMS.register("atherium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(ModToolTiers.ATHERIUM,
                                    5,         //  Tier-6  ➜  highest pick ΔDMG
                                    -2.8F))));
    public static final DeferredItem<Item> ATHERIUM_AXE = ITEMS.register("atherium_axe",
            () -> new AxeItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(ModToolTiers.ATHERIUM,
                                    9,         //  Tier-6  ➜  highest axe ΔDMG
                                    -3F))));
    public static final DeferredItem<Item> ATHERIUM_SHOVEL = ITEMS.register("atherium_shovel",
            () -> new ShovelItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(ModToolTiers.ATHERIUM,
                                    5,         //  Tier-6  ➜  highest shovel ΔDMG
                                    -3F))));
    public static final DeferredItem<Item> ATHERIUM_HOE = ITEMS.register("atherium_hoe",
            () -> new HoeItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(ModToolTiers.ATHERIUM,
                                    1,        //  Tier-6  ➜  only positive hoe ΔDMG
                                    1F))));
    public static final DeferredItem<Item> ATHERIUM_HAMMER = ITEMS.register("atherium_hammer",
            () -> new HammerItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.ATHERIUM,
                                    11,        //  Tier-6  ➜  highest hammer ΔDMG
                                    -3.5F))));
    public static final DeferredItem<Item> ATHERIUM_PAXEL = ITEMS.register("atherium_paxel",
            () -> new PaxelItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(PaxelItem.createAttributes(ModToolTiers.ATHERIUM,
                                    4,         //  Tier-6  ➜  highest paxel ΔDMG
                                    -3F))));
    public static final DeferredItem<Item> ATHERIUM_REINFORCED_HAMMER = ITEMS.register("atherium_reinforced_hammer",
            () -> new ReinforcedHammerItem(ModToolTiers.ATHERIUM,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.ATHERIUM,
                                    13,        // +2 damage over base
                                    -3.5F))
                            .durability(ModToolTiers.ATHERIUM.getUses() + 300)));

    /* ----------  ✦  TIER-5  MALACHITE  ✦  ------------------------------ */
    public static final DeferredItem<MobEffectSwordItem> MALACHITE_SWORD = ITEMS.register("malachite_sword",
            () -> new MobEffectSwordItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.MALACHITE,
                                    5,        //  Tier-5
                                    -2.4F)),
                    MobEffects.POISON, 100, 1));
    public static final DeferredItem<Item> MALACHITE_PICKAXE = ITEMS.register("malachite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(ModToolTiers.MALACHITE,
                                    4,        //  Tier-5
                                    -2.8F))));
    public static final DeferredItem<Item> MALACHITE_AXE = ITEMS.register("malachite_axe",
            () -> new AxeItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(ModToolTiers.MALACHITE,
                                    8,        //  Tier-5
                                    -3F))));
    public static final DeferredItem<Item> MALACHITE_SHOVEL = ITEMS.register("malachite_shovel",
            () -> new ShovelItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(ModToolTiers.MALACHITE,
                                    4,        //  Tier-5
                                    -3F))));
    public static final DeferredItem<Item> MALACHITE_HOE = ITEMS.register("malachite_hoe",
            () -> new HoeItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(ModToolTiers.MALACHITE,
                                    0,        //  Tier-5  (neutral ΔDMG)
                                    1F))));
    public static final DeferredItem<Item> MALACHITE_HAMMER = ITEMS.register("malachite_hammer",
            () -> new HammerItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.MALACHITE,
                                    10,       //  Tier-5
                                    -3.5F))));
    public static final DeferredItem<Item> MALACHITE_PAXEL = ITEMS.register("malachite_paxel",
            () -> new PaxelItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(PaxelItem.createAttributes(ModToolTiers.MALACHITE,
                                    3,        //  Tier-5
                                    -3F))));
    public static final DeferredItem<Item> MALACHITE_REINFORCED_HAMMER = ITEMS.register("malachite_reinforced_hammer",
            () -> new ReinforcedHammerItem(ModToolTiers.MALACHITE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.MALACHITE,
                                    12,        // +2 damage over base
                                    -3.5F))
                            .durability(ModToolTiers.MALACHITE.getUses() + 300)));

    /* ----------  ✦  TIER-4  RUBY  ✦  ---------------------------------- */
    public static final DeferredItem<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword",
            () -> new SwordItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.RUBY,
                                    4,        //  Tier-4
                                    -2.4F))));
    public static final DeferredItem<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(ModToolTiers.RUBY,
                                    3,        //  Tier-4
                                    -2.8F))));
    public static final DeferredItem<Item> RUBY_AXE = ITEMS.register("ruby_axe",
            () -> new AxeItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(ModToolTiers.RUBY,
                                    7,        //  Tier-4
                                    -3F))));
    public static final DeferredItem<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(ModToolTiers.RUBY,
                                    3,        //  Tier-4
                                    -3F))));
    public static final DeferredItem<Item> RUBY_HOE = ITEMS.register("ruby_hoe",
            () -> new HoeItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(ModToolTiers.RUBY,
                                    -1,       //  Tier-4
                                    1F))));
    public static final DeferredItem<Item> RUBY_HAMMER = ITEMS.register("ruby_hammer",
            () -> new HammerItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.RUBY,
                                    9,        //  Tier-4
                                    -3.5F))));
    public static final DeferredItem<Item> RUBY_PAXEL = ITEMS.register("ruby_paxel",
            () -> new PaxelItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(PaxelItem.createAttributes(ModToolTiers.RUBY,
                                    2,        //  Tier-4
                                    -3F))));
    public static final DeferredItem<Item> RUBY_REINFORCED_HAMMER = ITEMS.register("ruby_reinforced_hammer",
            () -> new ReinforcedHammerItem(ModToolTiers.RUBY,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.RUBY,
                                    11,        // +2 damage over base
                                    -3.5F))
                            .durability(ModToolTiers.RUBY.getUses() + 300)));


    /* ----------  ✦  TIER-3  SAPPHIRE  ✦  ------------------------------ */
    public static final DeferredItem<SwordItem> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new SwordItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    3,        //  Tier-3
                                    -2.4F))));
    public static final DeferredItem<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    2,        //  Tier-3
                                    -2.8F))));
    public static final DeferredItem<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    6,        //  Tier-3
                                    -3F))));
    public static final DeferredItem<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            () -> new ShovelItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    2,        //  Tier-3
                                    -3F))));
    public static final DeferredItem<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            () -> new HoeItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    -2,       //  Tier-3
                                    1F))));
    public static final DeferredItem<Item> SAPPHIRE_HAMMER = ITEMS.register("sapphire_hammer",
            () -> new HammerItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    8,        //  Tier-3
                                    -3.5F))));
    public static final DeferredItem<Item> SAPPHIRE_PAXEL = ITEMS.register("sapphire_paxel",
            () -> new PaxelItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(PaxelItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    1,        //  Tier-3
                                    -3F))));
    public static final DeferredItem<Item> SAPPHIRE_REINFORCED_HAMMER = ITEMS.register("sapphire_reinforced_hammer",
            () -> new ReinforcedHammerItem(ModToolTiers.SAPPHIRE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.SAPPHIRE,
                                    10,        // +2 damage over base
                                    -3.5F))
                            .durability(ModToolTiers.SAPPHIRE.getUses() + 300)));

    /* ----------  ✦  TIER-3  SUNSTONE  ✦ (identical numbers, extra fire effect) */
    public static final DeferredItem<MobEffectSwordItem> SUNSTONE_SWORD = ITEMS.register("sunstone_sword",
            () -> new MobEffectSwordItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.SUNSTONE,
                                    3,        //  Tier-3
                                    -2.4F)),
                    ModEffects.TRUE_FIRE_EFFECT, 25, 1));
    public static final DeferredItem<Item> SUNSTONE_PICKAXE = ITEMS.register("sunstone_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(ModToolTiers.SUNSTONE,
                                    2,        //  Tier-3
                                    -2.8F))));
    public static final DeferredItem<Item> SUNSTONE_AXE = ITEMS.register("sunstone_axe",
            () -> new AxeItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(ModToolTiers.SUNSTONE,
                                    6,        //  Tier-3
                                    -3F))));
    public static final DeferredItem<Item> SUNSTONE_SHOVEL = ITEMS.register("sunstone_shovel",
            () -> new ShovelItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(ModToolTiers.SUNSTONE,
                                    2,        //  Tier-3
                                    -3F))));
    public static final DeferredItem<Item> SUNSTONE_HOE = ITEMS.register("sunstone_hoe",
            () -> new HoeItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(ModToolTiers.SUNSTONE,
                                    -2,       //  Tier-3
                                    1F))));
    public static final DeferredItem<Item> SUNSTONE_HAMMER = ITEMS.register("sunstone_hammer",
            () -> new HammerItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.SUNSTONE,
                                    8,        //  Tier-3
                                    -3.5F))));
    public static final DeferredItem<Item> SUNSTONE_PAXEL = ITEMS.register("sunstone_paxel",
            () -> new PaxelItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(PaxelItem.createAttributes(ModToolTiers.SUNSTONE,
                                    1,        //  Tier-3
                                    -3F))));
    public static final DeferredItem<Item> SUNSTONE_REINFORCED_HAMMER = ITEMS.register("sunstone_reinforced_hammer",
            () -> new ReinforcedHammerItem(ModToolTiers.SUNSTONE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.SUNSTONE,
                                    10,        // +2 damage over base
                                    -3.5F))
                            .durability(ModToolTiers.SUNSTONE.getUses() + 300)));

    /* ----------  ✦  TIER-1  JASPILITE  ✦  ----------------------------- */
    public static final DeferredItem<SwordItem> JASPILITE_SWORD = ITEMS.register("jaspilite_sword",
            () -> new SwordItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(SwordItem.createAttributes(ModToolTiers.JASPILITE,
                                    2,        //  Tier-1  (baseline)
                                    -2.4F))));
    public static final DeferredItem<Item> JASPILITE_PICKAXE = ITEMS.register("jaspilite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(ModToolTiers.JASPILITE,
                                    1,        //  Tier-1
                                    -2.8F))));
    public static final DeferredItem<Item> JASPILITE_AXE = ITEMS.register("jaspilite_axe",
            () -> new AxeItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(ModToolTiers.JASPILITE,
                                    5,        //  Tier-1
                                    -3F))));
    public static final DeferredItem<Item> JASPILITE_SHOVEL = ITEMS.register("jaspilite_shovel",
            () -> new ShovelItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(ShovelItem.createAttributes(ModToolTiers.JASPILITE,
                                    1,        //  Tier-1
                                    -3F))));
    public static final DeferredItem<Item> JASPILITE_HOE = ITEMS.register("jaspilite_hoe",
            () -> new HoeItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(HoeItem.createAttributes(ModToolTiers.JASPILITE,
                                    -3,       //  Tier-1
                                    1F))));
    public static final DeferredItem<Item> JASPILITE_HAMMER = ITEMS.register("jaspilite_hammer",
            () -> new HammerItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.JASPILITE,
                                    7,        //  Tier-1
                                    -3.5F))));
    public static final DeferredItem<Item> JASPILITE_PAXEL = ITEMS.register("jaspilite_paxel",
            () -> new PaxelItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(PaxelItem.createAttributes(ModToolTiers.JASPILITE,
                                    0,        //  Tier-1
                                    -3F))));
    public static final DeferredItem<Item> JASPILITE_REINFORCED_HAMMER = ITEMS.register("jaspilite_reinforced_hammer",
            () -> new ReinforcedHammerItem(ModToolTiers.JASPILITE,
                    new Item.Properties()
                            .attributes(HammerItem.createAttributes(ModToolTiers.JASPILITE,
                                    9,        // +2 damage over base
                                    -3.5F))
                            .durability(ModToolTiers.JASPILITE.getUses() + 300)));

    /* =====================================================================
     *                      ARMOUR ITEM REGISTRATION
     * ===================================================================== */

    // ★ TIER-6  ATHERIUM (full-set = Health-Boost)
    public static final DeferredItem<ArmorItem> ATHERIUM_HELMET = ITEMS.register("atherium_helmet",
            () -> new ArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(42))));
    public static final DeferredItem<ArmorItem> ATHERIUM_CHESTPLATE = ITEMS.register("atherium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(42))));
    public static final DeferredItem<ArmorItem> ATHERIUM_LEGGINGS = ITEMS.register("atherium_leggings",
            () -> new ArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(42))));
    public static final DeferredItem<ArmorItem> ATHERIUM_BOOTS = ITEMS.register("atherium_boots",
            () -> new ArmorItem(ModArmorMaterials.ATHERIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(42))));

    // ★ TIER-5  MALACHITE
    public static final DeferredItem<ArmorItem> MALACHITE_HELMET = ITEMS.register("malachite_helmet",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(41))));
    public static final DeferredItem<ArmorItem> MALACHITE_CHESTPLATE = ITEMS.register("malachite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(41))));
    public static final DeferredItem<ArmorItem> MALACHITE_LEGGINGS = ITEMS.register("malachite_leggings",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(41))));
    public static final DeferredItem<ArmorItem> MALACHITE_BOOTS = ITEMS.register("malachite_boots",
            () -> new ArmorItem(ModArmorMaterials.MALACHITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(41))));

    // ★ TIER-4  RUBY
    public static final DeferredItem<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40))));
    public static final DeferredItem<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40))));
    public static final DeferredItem<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40))));
    public static final DeferredItem<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots",
            () -> new ArmorItem(ModArmorMaterials.RUBY_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40))));

    // ★ TIER-3  SAPPHIRE
    public static final DeferredItem<ArmorItem> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(38))));
    public static final DeferredItem<ArmorItem> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(38))));
    public static final DeferredItem<ArmorItem> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(38))));
    public static final DeferredItem<ArmorItem> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(38))));

    // ★ TIER-3  SUNSTONE  (full-set = Fire-Resistance)
    public static final DeferredItem<ArmorItem> SUNSTONE_HELMET = ITEMS.register("sunstone_helmet",
            () -> new ArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(38))));
    public static final DeferredItem<ArmorItem> SUNSTONE_CHESTPLATE = ITEMS.register("sunstone_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(38))));
    public static final DeferredItem<ArmorItem> SUNSTONE_LEGGINGS = ITEMS.register("sunstone_leggings",
            () -> new ArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(38))));
    public static final DeferredItem<ArmorItem> SUNSTONE_BOOTS = ITEMS.register("sunstone_boots",
            () -> new ArmorItem(ModArmorMaterials.SUNSTONE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(38))));

    // ★ TIER-1  JASPILITE
    public static final DeferredItem<ArmorItem> JASPILITE_HELMET = ITEMS.register("jaspilite_helmet",
            () -> new ArmorItem(ModArmorMaterials.JASPILITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(32))));
    public static final DeferredItem<ArmorItem> JASPILITE_CHESTPLATE = ITEMS.register("jaspilite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.JASPILITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(32))));
    public static final DeferredItem<ArmorItem> JASPILITE_LEGGINGS = ITEMS.register("jaspilite_leggings",
            () -> new ArmorItem(ModArmorMaterials.JASPILITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(32))));
    public static final DeferredItem<ArmorItem> JASPILITE_BOOTS = ITEMS.register("jaspilite_boots",
            () -> new ArmorItem(ModArmorMaterials.JASPILITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(32))));

    // Cooling armour
    public static final DeferredItem<ArmorItem> SWIM_TRUNKS = ITEMS.register("swim_trunks",
            () -> new ArmorItem(ModArmorMaterials.SWIMWEAR_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(5))));
    public static final DeferredItem<ArmorItem> BIKINI_TOP = ITEMS.register("bikini_top",
            () -> new ArmorItem(ModArmorMaterials.SWIMWEAR_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(5))));


    /* =====================================================================
        *                      CURIOS ITEM REGISTRATION
        * ===================================================================== */

    // Register the Ring of Atherium item with attack speed effect for the ring slot
    public static final DeferredItem<Item> RING_OF_ATHERIUM = ITEMS.register("ring_of_atherium",
            () -> new CustomCurioMobEffectItem(new MobEffectInstance(ModEffects.ATTACK_SPEED_EFFECT, Integer.MAX_VALUE, 0, true, false, false), "ring"));

    // Register the Ring of Ruby item with health effect for the ring slot
    public static final DeferredItem<Item> RING_OF_RUBY = ITEMS.register("ring_of_ruby",
            () -> new CustomCurioMobEffectItem(new MobEffectInstance(MobEffects.HEALTH_BOOST, Integer.MAX_VALUE, 4, true, false, false), "ring"));

    // Register the Ring of Sapphire item with luck effect for the ring slot
    public static final DeferredItem<Item> RING_OF_SAPPHIRE = ITEMS.register("ring_of_sapphire",
            () -> new CustomCurioMobEffectItem(new MobEffectInstance(MobEffects.LUCK, Integer.MAX_VALUE, 2, true, false, false), "ring"));

    public static final DeferredItem<Item> BRACELET_OF_MALACHITE = ITEMS.register("bracelet_of_malachite",
            () -> new CustomCurioMobEffectItem(new MobEffectInstance(MobEffects.POISON, Integer.MAX_VALUE, 0, true, false, false), "bracelet"));

    public static final DeferredItem<Item> RING_OF_SUNSTONE = ITEMS.register("ring_of_sunstone",
            () -> new CustomCurioMobEffectItem(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, true, false, false), "ring"));

    // Register the Necklace of Amethyst item with haste effect for the necklace slot
    public static final DeferredItem<Item> NECKLACE_OF_JASPILITE = ITEMS.register("necklace_of_jaspilite",
            () -> new CustomCurioMobEffectItem(new MobEffectInstance(MobEffects.DIG_SPEED, Integer.MAX_VALUE, 1, true, false, false), "necklace"));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
