package net.succ.succsmod.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.worldgen.tree.custom.ModTreeDecorator;

public class ModTreeDecoratorTypes {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, SuccsMod.MOD_ID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<ModTreeDecorator>> MYCELIAL_VINE =
            TREE_DECORATOR_TYPES.register("mycelial_vine",
                    () -> new TreeDecoratorType<>(ModTreeDecorator.CODEC));

    public static void register(IEventBus eventBus) {
        TREE_DECORATOR_TYPES.register(eventBus);
    }
}
