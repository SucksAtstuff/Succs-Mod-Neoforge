package net.succ.succsmod.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.screen.custom.GemPolishingTableBlockMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, SuccsMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GemPolishingTableBlockMenu>> GEM_POLISHING_MENU =
            registerMenuType("gem_polishing_menu", GemPolishingTableBlockMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register (IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
