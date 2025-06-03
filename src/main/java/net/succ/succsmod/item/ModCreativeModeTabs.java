package net.succ.succsmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import java.awt.*;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    // Create a DeferredRegister for CreativeModeTabs, using the mod ID
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SuccsMod.MOD_ID);

    public static final Supplier<CreativeModeTab> SUCCS_ESSENTIALS_TAB_GEMS = CREATIVE_MODE_TAB.register("succs_essentials_tab_gems",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ATHERIUM.get()))
                    .title(Component.translatable("creativetab.succsessentials.gems.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ATHERIUM.get());
                        output.accept(ModItems.DIRTY_ATHERIUM.get());
                        output.accept(ModItems.RUBY.get());
                        output.accept(ModItems.DIRTY_RUBY.get());
                        output.accept(ModItems.SAPPHIRE.get());
                        output.accept(ModItems.DIRTY_SAPPHIRE.get());
                        output.accept(ModItems.MALACHITE.get());
                        output.accept(ModItems.DIRTY_MALACHITE.get());
                        output.accept(ModItems.SUNSTONE.get());
                        output.accept(ModItems.DIRTY_SUNSTONE.get());
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCS_ESSENTIALS_TAB_ITEMS = CREATIVE_MODE_TAB.register("succs_essentials_tab_items",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.GOLD_HANDLE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succs_essentials_tab_gems"))
                    .title(Component.translatable("creativetab.succsessentials.items.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GOLD_HANDLE.get());
                        output.accept(ModItems.GARLIC.get());
                        output.accept(ModItems.GARLIC_BREAD.get());
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCS_ESSENTIALS_TAB_BLOCKS = CREATIVE_MODE_TAB.register("succs_essentials_tab_blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.ATHERIUM_ORE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succs_essentials_tab_items"))
                    .title(Component.translatable("creativetab.succsessentials.blocks.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ATHERIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get());
                        output.accept(ModBlocks.NETHER_ATHERIUM_ORE.get());
                        output.accept(ModBlocks.END_ATHERIUM_ORE.get());
                        output.accept(ModBlocks.ATHERIUM_BLOCK.get());

                        output.accept(ModBlocks.RUBY_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_RUBY_ORE.get());
                        output.accept(ModBlocks.NETHER_RUBY_ORE.get());
                        output.accept(ModBlocks.END_RUBY_ORE.get());
                        output.accept(ModBlocks.RUBY_BLOCK.get());

                        output.accept(ModBlocks.SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.END_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.SAPPHIRE_BLOCK.get());

                        output.accept(ModBlocks.MALACHITE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_MALACHITE_ORE.get());
                        output.accept(ModBlocks.NETHER_MALACHITE_ORE.get());
                        output.accept(ModBlocks.END_MALACHITE_ORE.get());
                        output.accept(ModBlocks.MALACHITE_BLOCK.get());

                        output.accept(ModBlocks.SUNSTONE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get());
                        output.accept(ModBlocks.NETHER_SUNSTONE_ORE.get());
                        output.accept(ModBlocks.END_SUNSTONE_ORE.get());
                        output.accept(ModBlocks.SUNSTONE_BLOCK.get());
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCS_ESSENTIALS_TAB_TOOLS = CREATIVE_MODE_TAB.register("succs_essentials_tab_tools",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ATHERIUM_SWORD.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succs_essentials_tab_blocks"))
                    .title(Component.translatable("creativetab.succsessentials.tools.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ATHERIUM_SWORD.get());
                        output.accept(ModItems.ATHERIUM_PICKAXE.get());
                        output.accept(ModItems.ATHERIUM_AXE.get());
                        output.accept(ModItems.ATHERIUM_SHOVEL.get());
                        output.accept(ModItems.ATHERIUM_HOE.get());
                        output.accept(ModItems.ATHERIUM_HAMMER.get());


                        output.accept(ModItems.RUBY_SWORD.get());
                        output.accept(ModItems.RUBY_PICKAXE.get());
                        output.accept(ModItems.RUBY_AXE.get());
                        output.accept(ModItems.RUBY_SHOVEL.get());
                        output.accept(ModItems.RUBY_HOE.get());
                        output.accept(ModItems.RUBY_HAMMER.get());


                        output.accept(ModItems.SAPPHIRE_SWORD.get());
                        output.accept(ModItems.SAPPHIRE_PICKAXE.get());
                        output.accept(ModItems.SAPPHIRE_AXE.get());
                        output.accept(ModItems.SAPPHIRE_SHOVEL.get());
                        output.accept(ModItems.SAPPHIRE_HOE.get());
                        output.accept(ModItems.SAPPHIRE_HAMMER.get());


                        output.accept(ModItems.SUNSTONE_SWORD.get());
                        output.accept(ModItems.SUNSTONE_PICKAXE.get());
                        output.accept(ModItems.SUNSTONE_AXE.get());
                        output.accept(ModItems.SUNSTONE_SHOVEL.get());
                        output.accept(ModItems.SUNSTONE_HOE.get());
                        output.accept(ModItems.SUNSTONE_HAMMER.get());


                        output.accept(ModItems.MALACHITE_SWORD.get());
                        output.accept(ModItems.MALACHITE_PICKAXE.get());
                        output.accept(ModItems.MALACHITE_AXE.get());
                        output.accept(ModItems.MALACHITE_SHOVEL.get());
                        output.accept(ModItems.MALACHITE_HOE.get());
                        output.accept(ModItems.MALACHITE_HAMMER.get());
                    })
                    .build());

    public static final Supplier<CreativeModeTab> SUCCS_ESSENTIALS_TAB_ARMOR = CREATIVE_MODE_TAB.register("succs_essentials_tab_armor",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.ATHERIUM_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succs_essentials_tab_tools"))
                    .title(Component.translatable("creativetab.succsessentials.armor.tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                    })
                    .build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
