package net.succ.succsmod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = SuccsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Tool toggles
    public static final ModConfigSpec.BooleanValue ENABLE_HAMMER = BUILDER
            .comment("Enable all Hammer tools")
            .define("tools.enableHammer", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PAXEL = BUILDER
            .comment("Enable all Paxel tools")
            .define("tools.enablePaxel", true);

    // Recipe type toggle
    public static final ModConfigSpec.BooleanValue USE_POLISHING_TABLE = BUILDER
            .comment("Use Polishing Table to clean gems instead of furnace smelting")
            .define("recipes.usePolishingTable", true);

    // Food toggles
    public static final ModConfigSpec.BooleanValue ENABLE_GARLIC = BUILDER
            .define("food.enableGarlic", true);
    public static final ModConfigSpec.BooleanValue ENABLE_GARLIC_BREAD = BUILDER
            .define("food.enableGarlicBread", true);
    public static final ModConfigSpec.BooleanValue ENABLE_ROCK_CANDY = BUILDER
            .define("food.enableRockCandy", true);


    // ORE SETTINGS — Atherium
    public static final ModConfigSpec.BooleanValue GENERATE_ATHERIUM = BUILDER.define("ores.atherium.generate", true);
    public static final ModConfigSpec.IntValue ATHERIUM_VEINS = BUILDER.defineInRange("ores.atherium.veinsPerChunk", 1, 0, 64);
    public static final ModConfigSpec.IntValue ATHERIUM_VEIN_MIN = BUILDER.defineInRange("ores.atherium.veinSizeMin", 3, 1, 64);
    public static final ModConfigSpec.IntValue ATHERIUM_VEIN_MAX = BUILDER.defineInRange("ores.atherium.veinSizeMax", 5, 1, 64);
    public static final ModConfigSpec.IntValue ATHERIUM_MIN_Y = BUILDER.defineInRange("ores.atherium.minY", -32, -64, 320);
    public static final ModConfigSpec.IntValue ATHERIUM_MAX_Y = BUILDER.defineInRange("ores.atherium.maxY", 16, -64, 320);

    // Ruby
    public static final ModConfigSpec.BooleanValue GENERATE_RUBY = BUILDER.define("ores.ruby.generate", true);
    public static final ModConfigSpec.IntValue RUBY_VEINS = BUILDER.defineInRange("ores.ruby.veinsPerChunk", 3, 0, 64);
    public static final ModConfigSpec.IntValue RUBY_VEIN_MIN = BUILDER.defineInRange("ores.ruby.veinSizeMin", 3, 1, 64);
    public static final ModConfigSpec.IntValue RUBY_VEIN_MAX = BUILDER.defineInRange("ores.ruby.veinSizeMax", 8, 1, 64);
    public static final ModConfigSpec.IntValue RUBY_MIN_Y = BUILDER.defineInRange("ores.ruby.minY", 20, -64, 320);
    public static final ModConfigSpec.IntValue RUBY_MAX_Y = BUILDER.defineInRange("ores.ruby.maxY", 80, -64, 320);

    // Sapphire
    public static final ModConfigSpec.BooleanValue GENERATE_SAPPHIRE = BUILDER.define("ores.sapphire.generate", true);
    public static final ModConfigSpec.IntValue SAPPHIRE_VEINS = BUILDER.defineInRange("ores.sapphire.veinsPerChunk", 2, 0, 64);
    public static final ModConfigSpec.IntValue SAPPHIRE_VEIN_MIN = BUILDER.defineInRange("ores.sapphire.veinSizeMin", 4, 1, 64);
    public static final ModConfigSpec.IntValue SAPPHIRE_VEIN_MAX = BUILDER.defineInRange("ores.sapphire.veinSizeMax", 10, 1, 64);
    public static final ModConfigSpec.IntValue SAPPHIRE_MIN_Y = BUILDER.defineInRange("ores.sapphire.minY", 8, -64, 320);
    public static final ModConfigSpec.IntValue SAPPHIRE_MAX_Y = BUILDER.defineInRange("ores.sapphire.maxY", 40, -64, 320);

    // Sunstone
    public static final ModConfigSpec.BooleanValue GENERATE_SUNSTONE = BUILDER.define("ores.sunstone.generate", true);
    public static final ModConfigSpec.IntValue SUNSTONE_VEINS = BUILDER.defineInRange("ores.sunstone.veinsPerChunk", 2, 0, 64);
    public static final ModConfigSpec.IntValue SUNSTONE_VEIN_MIN = BUILDER.defineInRange("ores.sunstone.veinSizeMin", 4, 1, 64);
    public static final ModConfigSpec.IntValue SUNSTONE_VEIN_MAX = BUILDER.defineInRange("ores.sunstone.veinSizeMax", 10, 1, 64);
    public static final ModConfigSpec.IntValue SUNSTONE_MIN_Y = BUILDER.defineInRange("ores.sunstone.minY", 8, -64, 320);
    public static final ModConfigSpec.IntValue SUNSTONE_MAX_Y = BUILDER.defineInRange("ores.sunstone.maxY", 40, -64, 320);

    // Malachite
    public static final ModConfigSpec.BooleanValue GENERATE_MALACHITE = BUILDER.define("ores.malachite.generate", true);
    public static final ModConfigSpec.IntValue MALACHITE_VEINS = BUILDER.defineInRange("ores.malachite.veinsPerChunk", 2, 0, 64);
    public static final ModConfigSpec.IntValue MALACHITE_VEIN_MIN = BUILDER.defineInRange("ores.malachite.veinSizeMin", 2, 1, 64);
    public static final ModConfigSpec.IntValue MALACHITE_VEIN_MAX = BUILDER.defineInRange("ores.malachite.veinSizeMax", 10, 1, 64);
    public static final ModConfigSpec.IntValue MALACHITE_MIN_Y = BUILDER.defineInRange("ores.malachite.minY", 0, -64, 320);
    public static final ModConfigSpec.IntValue MALACHITE_MAX_Y = BUILDER.defineInRange("ores.malachite.maxY", 48, -64, 320);

    // Disable any item by registry name
    public static final ModConfigSpec.ConfigValue<List<? extends String>> DISABLED_ITEMS = BUILDER
            .comment("List of item IDs to disable (e.g., succsmod:ruby_sword)")
            .defineListAllowEmpty("items.disabled", List.of(), Config::validateItemName);

    public static final ModConfigSpec SPEC = BUILDER.build();

    // Runtime values
    public static boolean enableHammer;
    public static boolean enablePaxel;
    public static boolean usePolishingTable;
    public static boolean enableGarlic;
    public static boolean enableGarlicBread;
    public static boolean enableRockCandy;
    public static Set<Item> disabledItems;

    private static boolean validateItemName(final Object obj) {
        if (!(obj instanceof String s)) return false;
        ResourceLocation rl = ResourceLocation.tryParse(s);
        return rl != null && BuiltInRegistries.ITEM.containsKey(rl);
    }


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        enableHammer = ENABLE_HAMMER.get();
        enablePaxel = ENABLE_PAXEL.get();
        usePolishingTable = USE_POLISHING_TABLE.get();
        enableGarlic = ENABLE_GARLIC.get();
        enableGarlicBread = ENABLE_GARLIC_BREAD.get();
        enableRockCandy = ENABLE_ROCK_CANDY.get();


        disabledItems = DISABLED_ITEMS.get().stream()
                .map(s -> ResourceLocation.tryParse((String) s))
                .filter(loc -> loc != null)
                .map(loc -> BuiltInRegistries.ITEM.get(loc))
                .filter(item -> item != null)
                .collect(Collectors.toSet());

    }

    public static boolean isItemDisabled(Item item) {
        return disabledItems.contains(item);
    }
}
