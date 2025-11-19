package net.succ.succsmod.advancement;

import com.google.common.collect.Sets;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.resources.ResourceLocation;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * AllSuccAdvancements
 *
 * Central registry for all advancements for Succ's Essentials.
 * Structured similar to Create's AllAdvancements.
 */
public class AllSuccAdvancements implements DataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger("SuccAdvancements");

    /**
     * Every SuccAdvancement instance registers itself here in its constructor.
     */
    public static final List<SuccAdvancement> ENTRIES = new ArrayList<>();

    // ========================================================================
    // ROOT
    // ========================================================================

    /**
     * Root advancement for the Succ's Essentials tab.
     * Uses a custom background texture defined in SuccAdvancement.
     */
    public static final SuccAdvancement ROOT = SuccAdvancement.create("root", b -> b
            .icon(ModBlocks.ATHERIUM_ORE.get())
            .title("The Gemstone Journey")
            .description("Begin discovering the elemental gemstones.")
            .awardedForFree()
    );

    // ========================================================================
    // GEM PROGRESSION (TOOLS)
    // ========================================================================

    //
    // Sapphire
    //
    public static final SuccAdvancement SAPPHIRE = SuccAdvancement.create("sapphire", b -> b
            .icon(ModItems.SAPPHIRE.get())
            .title("Sapphire")
            .description("Obtain a Sapphire.")
            .whenIconCollected()
            .after(ROOT)
    );

    //
    // Sapphire Pickaxe → used as gate into Ruby branch
    //
    public static final SuccAdvancement SAPPHIRE_PICKAXE = SuccAdvancement.create("sapphire_pickaxe", b -> b
            .icon(ModItems.SAPPHIRE_PICKAXE.get())
            .title("Feeling fortunate?")
            .description("Craft a Sapphire Pickaxe.")
            .whenIconCollected()
            .after(SAPPHIRE)
    );

    //
    // Sunstone
    //
    public static final SuccAdvancement SUNSTONE = SuccAdvancement.create("sunstone", b -> b
            .icon(ModItems.SUNSTONE.get())
            .title("Sunstone")
            .description("Obtain a Sunstone.")
            .whenIconCollected()
            .after(ROOT)
    );

    //
    // Sunstone Pickaxe → also leads to Ruby
    //
    public static final SuccAdvancement SUNSTONE_PICKAXE = SuccAdvancement.create("sunstone_pickaxe", b -> b
            .icon(ModItems.SUNSTONE_PICKAXE.get())
            .title("Smeltery on the go.")
            .description("Craft a Sunstone Pickaxe.")
            .whenIconCollected()
            .after(SUNSTONE)
    );

    //
    // Ruby — unlocked if player reaches either Sapphire or Sunstone pickaxe
    //
    public static final SuccAdvancement RUBY = SuccAdvancement.create("ruby", b -> b
            .icon(ModItems.RUBY.get())
            .title("Ruby")
            .description("Acquire a Ruby, forged from heat and pressure.")
            .whenIconCollected()
            .after(SAPPHIRE_PICKAXE)
            .after(SUNSTONE_PICKAXE)
    );

    public static final SuccAdvancement RUBY_PICKAXE = SuccAdvancement.create("ruby_pickaxe", b -> b
            .icon(ModItems.RUBY_PICKAXE.get())
            .title("Ruby Tools")
            .description("Craft a Ruby Pickaxe.")
            .whenIconCollected()
            .after(RUBY)
    );

    //
    // Malachite
    //
    public static final SuccAdvancement MALACHITE = SuccAdvancement.create("malachite", b -> b
            .icon(ModItems.MALACHITE.get())
            .title("Malachite")
            .description("Obtain Malachite.")
            .whenIconCollected()
            .after(RUBY_PICKAXE)
    );

    public static final SuccAdvancement MALACHITE_PICKAXE = SuccAdvancement.create("malachite_pickaxe", b -> b
            .icon(ModItems.MALACHITE_PICKAXE.get())
            .title("Malachite Tools")
            .description("Craft a Malachite Pickaxe.")
            .whenIconCollected()
            .after(MALACHITE)
    );

    //
    // Atherium — final tier
    //
    public static final SuccAdvancement ATHERIUM = SuccAdvancement.create("atherium", b -> b
            .icon(ModItems.ATHERIUM.get())
            .title("Atherium")
            .description("Obtain the legendary Atherium crystal.")
            .whenIconCollected()
            .after(MALACHITE_PICKAXE)
    );

    public static final SuccAdvancement ATHERIUM_PICKAXE = SuccAdvancement.create("atherium_pickaxe", b -> b
            .icon(ModItems.ATHERIUM_PICKAXE.get())
            .title("Atherium Tools")
            .description("Craft a Atherium Pickaxe.")
            .whenIconCollected()
            .after(ATHERIUM)
    );

    // ========================================================================
    // JASPILITE BRANCH (SEPARATE)
    // ========================================================================

    public static final SuccAdvancement JASPILITE = SuccAdvancement.create("jaspilite", b -> b
            .icon(ModItems.JASPILITE.get())
            .title("Jaspilite")
            .description("Discover Jaspilite, the lone outsider gem.")
            .whenIconCollected()
            .after(ROOT)
    );

    public static final SuccAdvancement JASPILITE_PICKAXE = SuccAdvancement.create("jaspilite_pickaxe", b -> b
            .icon(ModItems.JASPILITE_PICKAXE.get())
            .title("Slightly better than diamond.")
            .description("Craft a Jaspilite Pickaxe.")
            .whenIconCollected()
            .after(JASPILITE)
    );

    // ========================================================================
    // ARMOR SET ADVANCEMENTS (1/4 style)
    // ========================================================================
    //
    // Each of these advancements:
    //  - Uses 4 criteria, one per armor piece
    //  - Shows a progress bar like “1/4”, “2/4”, etc.
    //  - Uses InventoryChange triggers via whenItemCollected(...)
    //

    // Sapphire Armor Set
    public static final SuccAdvancement SAPPHIRE_ARMOR = SuccAdvancement.create("sapphire_armor", b -> b
            .icon(ModItems.SAPPHIRE_CHESTPLATE.get())
            .title("Sapphire Armor Set")
            .description("Obtain the full Sapphire armor set.")
            .whenItemCollected(ModItems.SAPPHIRE_HELMET.get())
            .whenItemCollected(ModItems.SAPPHIRE_CHESTPLATE.get())
            .whenItemCollected(ModItems.SAPPHIRE_LEGGINGS.get())
            .whenItemCollected(ModItems.SAPPHIRE_BOOTS.get())
            .after(SAPPHIRE)
    );

    // Sunstone Armor Set
    public static final SuccAdvancement SUNSTONE_ARMOR = SuccAdvancement.create("sunstone_armor", b -> b
            .icon(ModItems.SUNSTONE_CHESTPLATE.get())
            .title("Sunstone Armor Set")
            .description("Obtain the full Sunstone armor set.")
            .whenItemCollected(ModItems.SUNSTONE_HELMET.get())
            .whenItemCollected(ModItems.SUNSTONE_CHESTPLATE.get())
            .whenItemCollected(ModItems.SUNSTONE_LEGGINGS.get())
            .whenItemCollected(ModItems.SUNSTONE_BOOTS.get())
            .after(SUNSTONE)
    );

    // Ruby Armor Set
    public static final SuccAdvancement RUBY_ARMOR = SuccAdvancement.create("ruby_armor", b -> b
            .icon(ModItems.RUBY_CHESTPLATE.get())
            .title("Ruby Armor Set")
            .description("Obtain the full Ruby armor set.")
            .whenItemCollected(ModItems.RUBY_HELMET.get())
            .whenItemCollected(ModItems.RUBY_CHESTPLATE.get())
            .whenItemCollected(ModItems.RUBY_LEGGINGS.get())
            .whenItemCollected(ModItems.RUBY_BOOTS.get())
            .after(RUBY)
    );

    // Malachite Armor Set
    public static final SuccAdvancement MALACHITE_ARMOR = SuccAdvancement.create("malachite_armor", b -> b
            .icon(ModItems.MALACHITE_CHESTPLATE.get())
            .title("Malachite Armor Set")
            .description("Obtain the full Malachite armor set.")
            .whenItemCollected(ModItems.MALACHITE_HELMET.get())
            .whenItemCollected(ModItems.MALACHITE_CHESTPLATE.get())
            .whenItemCollected(ModItems.MALACHITE_LEGGINGS.get())
            .whenItemCollected(ModItems.MALACHITE_BOOTS.get())
            .after(MALACHITE)
    );

    // Atherium Armor Set
    public static final SuccAdvancement ATHERIUM_ARMOR = SuccAdvancement.create("atherium_armor", b -> b
            .icon(ModItems.ATHERIUM_CHESTPLATE.get())
            .title("Atherium Armor Set")
            .description("Obtain the full Atherium armor set.")
            .whenItemCollected(ModItems.ATHERIUM_HELMET.get())
            .whenItemCollected(ModItems.ATHERIUM_CHESTPLATE.get())
            .whenItemCollected(ModItems.ATHERIUM_LEGGINGS.get())
            .whenItemCollected(ModItems.ATHERIUM_BOOTS.get())
            .after(ATHERIUM)
            .special(SuccAdvancement.TaskType.EXPERT) // final tier set – make it feel special
    );

    // Jaspilite Armor Set
    public static final SuccAdvancement JASPILITE_ARMOR = SuccAdvancement.create("jaspilite_armor", b -> b
            .icon(ModItems.JASPILITE_CHESTPLATE.get())
            .title("Jaspilite Armor Set")
            .description("Obtain the full Jaspilite armor set.")
            .whenItemCollected(ModItems.JASPILITE_HELMET.get())
            .whenItemCollected(ModItems.JASPILITE_CHESTPLATE.get())
            .whenItemCollected(ModItems.JASPILITE_LEGGINGS.get())
            .whenItemCollected(ModItems.JASPILITE_BOOTS.get())
            .after(JASPILITE)
    );

    // ========================================================================
    // CURIOS — RINGS / BRACELETS / NECKLACE (also 1/4 style)
    // ========================================================================
    //
    // These use the same “multiple criteria in one advancement” style as armor
    // sets, so the UI will show partial progress (1/4, 2/4, etc.).
    //
    // NOTE: These use InventoryChange triggers; they fire when the item is
    // obtained (including equipping/unequipping via Curios, since that changes
    // the inventory contents / locations).
    //

    // Gemstone Rings (4 items)
    public static final SuccAdvancement GEMSTONE_RINGS = SuccAdvancement.create("rings", b -> b
            .icon(ModItems.RING_OF_ATHERIUM.get())
            .title("Gemstone Rings")
            .description("Equip every gemstone ring.")
            .whenItemCollected(ModItems.RING_OF_ATHERIUM.get())
            .whenItemCollected(ModItems.RING_OF_RUBY.get())
            .whenItemCollected(ModItems.RING_OF_SAPPHIRE.get())
            .whenItemCollected(ModItems.RING_OF_SUNSTONE.get())
            .after(ROOT)
    );

    // Enchanted Bracelets (3 items)
    public static final SuccAdvancement ENCHANTED_BRACELETS = SuccAdvancement.create("bracelets", b -> b
            .icon(ModItems.BRACELET_OF_MALACHITE.get())
            .title("Enchanted Bracelets")
            .description("Equip each mystical bracelet.")
            .whenItemCollected(ModItems.BRACELET_OF_MALACHITE.get())
            .whenItemCollected(ModItems.BRACELET_OF_DISPLACEMENT.get())
            .whenItemCollected(ModItems.BRACELET_OF_REPLACEMENT.get())
            .after(GEMSTONE_RINGS)
    );

    // Jaspilite Necklace (single item)
    public static final SuccAdvancement MYSTERIOUS_NECKLACES = SuccAdvancement.create("necklace_jaspilite", b -> b
            .icon(ModItems.NECKLACE_OF_JASPILITE.get())
            .title("Mysterious Necklaces")
            .description("Equip each mysterious necklace.")
            .whenItemCollected(ModItems.NECKLACE_OF_JASPILITE.get())
            .after(ENCHANTED_BRACELETS)
    );

    // ========================================================================
    // MISC ITEMS (GARLIC, ROCK CANDY, etc.)
    // ========================================================================

    public static final SuccAdvancement GARLIC = SuccAdvancement.create("garlic", b -> b
            .icon(ModItems.GARLIC.get())
            .title("Garlic")
            .description("Obtain some garlic.")
            .whenItemCollected(ModItems.GARLIC.get())
            .after(ROOT)
    );

    public static final SuccAdvancement GARLIC_BREAD = SuccAdvancement.create("garlic_bread", b -> b
            .icon(ModItems.GARLIC_BREAD.get())
            .title("mmm yummy")
            .description("I LOVE GARLIC BREAD!!!")
            .whenItemCollected(ModItems.GARLIC_BREAD.get())
            .after(GARLIC)
    );

    public static final SuccAdvancement ROCK_CANDY = SuccAdvancement.create("rock_candy", b -> b
            .icon(ModItems.ROCK_CANDY.get())
            .title("'Rock Candy?'")
            .description("This doesn't look like candy....")
            .whenItemCollected(ModItems.ROCK_CANDY.get())
            .after(ROOT)
            .special(SuccAdvancement.TaskType.SECRET)
    );

    // ========================================================================
    // DATAGEN IMPLEMENTATION
    // ========================================================================

    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public AllSuccAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.output = output;
        this.registries = registries;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return registries.thenCompose(provider -> {

            PackOutput.PathProvider pathProvider =
                    output.createPathProvider(Target.DATA_PACK, "advancement");

            List<CompletableFuture<?>> futures = new ArrayList<>();
            Set<ResourceLocation> usedIds = Sets.newHashSet();

            Consumer<AdvancementHolder> saver = holder -> {
                ResourceLocation id = holder.id();

                if (!usedIds.add(id)) {
                    throw new IllegalStateException("Duplicate advancement: " + id);
                }

                Path path = pathProvider.json(id);
                LOGGER.info("Saving advancement {}", id);

                futures.add(DataProvider.saveStable(
                        cache, provider, Advancement.CODEC, holder.value(), path
                ));
            };

            // Let each SuccAdvancement generate its AdvancementHolder
            for (SuccAdvancement adv : ENTRIES) {
                adv.save(saver, provider);
            }

            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return "Succ's Essentials Advancements";
    }

    /**
     * Called from your lang provider to auto-generate advancement titles/descriptions.
     */
    public static void provideLang(BiConsumer<String, String> langOut) {
        for (SuccAdvancement adv : ENTRIES) {
            adv.provideLang(langOut);
        }
    }

    public static void register() {
        // Parity with Create – nothing needed here at runtime.
    }
}
