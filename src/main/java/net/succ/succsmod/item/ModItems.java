package net.succ.succsmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.item.custom.GarlicItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SuccsMod.MOD_ID);

    public static final DeferredItem<Item> DIRTY_ATHERIUM = ITEMS.register("dirty_atherium",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ATHERIUM = ITEMS.register("atherium",
            () -> new Item(new Item.Properties()));

    // Register the Sunstone items
    public static final DeferredItem<Item> DIRTY_SUNSTONE = ITEMS.register("dirty_sunstone",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SUNSTONE = ITEMS.register("sunstone",
            () -> new Item(new Item.Properties()));

    // Register the Ruby items
    public static final DeferredItem<Item> DIRTY_RUBY = ITEMS.register("dirty_ruby",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    // Register the Sapphire items
    public static final DeferredItem<Item> DIRTY_SAPPHIRE = ITEMS.register("dirty_sapphire",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    // Register the Malachite items
    public static final DeferredItem<Item> MALACHITE = ITEMS.register("malachite",
            () ->new Item(new Item.Properties()));

    public static final DeferredItem<Item> DIRTY_MALACHITE = ITEMS.register("dirty_malachite",
            () -> new Item(new Item.Properties()));

    // Register the Gold Handle item
    public static final DeferredItem<Item> GOLD_HANDLE = ITEMS.register("gold_handle",
            () -> new Item(new Item.Properties()));

    // Register the Garlic item
    public static final DeferredItem<Item> GARLIC = ITEMS.register("garlic",
            () -> new GarlicItem(ModBlocks.GARLIC_CROP.get(), new Item.Properties()));

    // Register the Garlic Bread item
    public static final DeferredItem<Item> GARLIC_BREAD = ITEMS.register("garlic_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GARLIC_BREAD)));

    // Register the Rock item
    public static final DeferredItem<Item> ROCK = ITEMS.register("rock",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROCK)));

    // Register the Rock Candy item
    public static final DeferredItem<Item> ROCK_CANDY = ITEMS.register("rock_candy",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROCK_CANDY)));



    // Method to register the DeferredRegister to the event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
