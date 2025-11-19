package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;   // <-- ADDED
import net.minecraft.world.item.Item;        // <-- ADDED
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.succ.succsmod.SuccsMod;

import net.succ.succsmod.advancement.AllSuccAdvancements;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.enchant.ModEnchants;
import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.item.ModCreativeModeTabs;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.potion.ModPotions;
import net.succ.succsmod.worldgen.biome.ModBiomes;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends LanguageProvider {

    private final CompletableFuture<HolderLookup.Provider> lookup;

    public ModLangProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, SuccsMod.MOD_ID, "en_us");
        this.lookup = lookup;
    }

    @Override
    protected void addTranslations() {

        // ======================================================================
        // 1. AUTOMATIC ITEMS
        //    - Skip BlockItems (their lang is handled by the BLOCKS section)
        //    - Skip any items that have a manual override later in this file
        // ======================================================================
        ModItems.ITEMS.getEntries().forEach(entry -> {
            Item item = entry.get();                 // The actual Item instance
            String path = entry.getId().getPath();   // Registry path, e.g. "atherium_sword"

            // --- 1) Skip block items (BlockItem uses the block.* lang key) ---
            if (item instanceof BlockItem) {
                return; // Blocks are handled in the BLOCKS section below
            }

            // --- 2) Skip items with manual overrides in section 9 ---
            if (path.equals("atherium_helmet")       // custom: "Atherium Crown"
                    || path.equals("funky_music_disc")
                    || path.equals("clear_mud_disc")
                    || path.equals("bass_music_disc")
                    || path.equals("rock_candy")) {  // custom: "'Rock Candy'"
                return;
            }

            // Everything else: generate "nice" name from registry path
            add(item.getDescriptionId(), format(path));
        });

        // ======================================================================
        // 2. AUTOMATIC BLOCKS
        //    - All blocks, including ores and woodsets
        //    - Their BlockItems use the same lang key, but we skipped those above
        // ======================================================================
        ModBlocks.BLOCKS.getEntries().forEach(entry ->
                add(entry.get().getDescriptionId(), format(entry.getId().getPath()))
        );

        // ======================================================================
        // 3. AUTOMATIC ENTITIES
        //    - Skip TJ because we give him a special name manually later
        // ======================================================================
        ModEntities.ENTRIES.forEach(supplier -> {
            EntityType<?> type = supplier.get();

            // Manual override exists for this one: "TJ the Stinker"
            if (type == ModEntities.TJ.get()) {
                return;
            }

            add(type.getDescriptionId(), format(type.toShortString()));
        });

        // ======================================================================
        // 4. AUTOMATIC BIOMES
        // ======================================================================
        List<ResourceKey<Biome>> BIOME_KEYS = List.of(
                ModBiomes.SHATTERGROVE,
                ModBiomes.VENOMOUS_FEN,
                ModBiomes.CRYSTALFROST_VALE,
                ModBiomes.SOLARBLIGHT_EXPANSE
        );

        BIOME_KEYS.forEach(key ->
                add("biome." + SuccsMod.MOD_ID + "." + key.location().getPath(),
                        format(key.location().getPath()))
        );

        // ======================================================================
        // 5. AUTOMATIC ENCHANTMENTS
        // ======================================================================
        List<ResourceKey<Enchantment>> ENCHANT_KEYS = List.of(
                ModEnchants.WIDENING,
                ModEnchants.DEPTH
        );

        ENCHANT_KEYS.forEach(key ->
                add("enchantment." + SuccsMod.MOD_ID + "." + key.location().getPath(),
                        format(key.location().getPath()))
        );

        // ======================================================================
        // 6. AUTOMATIC EFFECTS
        // ======================================================================
        ModEffects.MOB_EFFECTS.getEntries().forEach(entry ->
                add(entry.get().getDescriptionId(),
                        format(entry.getId().getPath()))
        );

        // ======================================================================
        // 7. AUTOMATIC POTIONS
        // ======================================================================
        ModPotions.POTIONS.getEntries().forEach(entry -> {
            String id = entry.getId().getPath();
            String name = format(id);

            add("item.minecraft.potion.effect." + id, name + " Potion");
            add("item.minecraft.splash_potion.effect." + id, name + " Splash Potion");
            add("item.minecraft.lingering_potion.effect." + id, name + " Lingering Potion");
            add("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + name);
        });

        // ======================================================================
        // 8. ADVANCEMENTS
        // ======================================================================
        AllSuccAdvancements.provideLang(this::add);

        // ======================================================================
        // 9. MANUAL OVERRIDES
        //    (These have matching skips in the automatic sections above!)
        // ======================================================================

        // --- Items needing special names ---
        add("item.succsessentials.atherium_helmet", "Atherium Crown");

        // --- Music discs ---
        add("item.succsessentials.funky_music_disc", "Music Disc");
        add("item.succsessentials.funky_music.desc", "IsuckAtEverything - Funky Music Disc");

        add("item.succsessentials.clear_mud_disc", "Music Disc");
        add("item.succsessentials.clear_mud.desc", "Cloud8ine - Clear Mud");

        add("item.succsessentials.bass_music_disc", "Music Disc");
        add("item.succsessentials.bass_music.desc", "Cloud8ine - Bass Music Disc");

        // --- Tooltips ---
        add("succsessentials.tooltip.liquid.amount.with.capacity", "%s / %s mB");
        add("succsessentials.tooltip.liquid.amount", "%s mB");

        add("tooltip.succsessentials.area", "Area: %sx%sx%s");

        add("tooltip.succsessentials.fortune_pickaxe.tooltip",
                "Press §eShift§r for more information");
        add("tooltip.succsessentials.fortune_pickaxe.tooltip.shift",
                "Has a chance to drop more items when mining ores");

        add("tooltip.succsessentials.looting_sword.tooltip",
                "Press §eShift§r for more information");
        add("tooltip.succsessentials.looting_sword.tooltip.shift",
                "Mobs have a chance to drop more items when killed");

        add("tooltip.succsessentials.mob_effect_sword.tooltip",
                "On hit: Applies %1$s for %2$d seconds (Level %3$d)");
        add("tooltip.succsessentials.mob_effect_sword.tooltip.shift",
                "Press §eShift§r for more information");

        add("tooltip.succsessentials.auto_smelt", "Auto-smelts blocks");

        // --- Keybinds ---
        add("key.categories.succsessentials", "Succ's Essentials");
        add("key.succsessentials.bracelet_replacement", "Bracelet of Replacement");
        add("key.succsessentials.bracelet_displacement", "Bracelet of Displacement");

        // --- Smithing Template ---
        add("item.succsessentials.smithing_template.reinforcement.applies_to",
                "Applies to: All Hammers");
        add("item.succsessentials.smithing_template.reinforcement.ingredients",
                "Ingredients: Various reinforcement materials");
        add("item.succsessentials.smithing_template.reinforcement.title",
                "Reinforcement Upgrade");
        add("item.succsessentials.smithing_template.reinforcement.base_slot_description",
                "Place any hammer here");
        add("item.succsessentials.smithing_template.reinforcement.additions_slot_description",
                "Place the reinforcing material here");

        // --- Paintings (manual because not auto-generated) ---
        add("painting.succsessentials.kurgerbing.title", "Kurger Bing");
        add("painting.succsessentials.kurgerbing.author", "IsuckAtEverything");
        add("painting.succsessentials.farquaad.title", "Farquaad");
        add("painting.succsessentials.farquaad.author", "IsuckAtEverything");

        // --- Special Cases ---
        add("item.succsessentials.rock_candy", "'Rock Candy'");
        add("entity.succsessentials.tj", "TJ the Stinker");

        // ======================================================================
        // 10. CREATIVE MODE TABS
        // ======================================================================
        ModCreativeModeTabs.CREATIVE_MODE_TAB.getEntries().forEach(entry -> {
            String path = entry.getId().getPath();  // e.g. "succs_essentials_tab_gems"

            // Your creative tabs use:
            //   creativetab.succsessentials.<category>.tab
            //
            // Example:
            //   creativetab.succsessentials.gems.tab
            //
            // So we must convert "succs_essentials_tab_gems" → "gems"

            String clean = path.replace("succs_essentials_tab_", ""); // "gems"

            // Build proper lang key
            String key = "creativetab." + SuccsMod.MOD_ID + "." + clean + ".tab";

            // Convert "gems" → "Gems"
            String name = format(clean);

            add(key, "Succ's Essentials " + name);
        });

    }



    // Converts "ruby_pickaxe" → "Ruby Pickaxe"
    private static String format(String id) {
        String[] parts = id.split("_");
        StringBuilder formatted = new StringBuilder();
        for (String part : parts) {
            formatted.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1))
                    .append(" ");
        }
        return formatted.toString().trim();
    }
}
