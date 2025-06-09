package net.succ.succsmod.block.entity;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.block.entity.custom.GemPolishingTableBlockEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SuccsMod.MOD_ID);

    public static final Supplier<BlockEntityType<GemPolishingTableBlockEntity>> GEM_POLISHING_TABLE_BE =
            BLOCK_ENTITIES.register("gem_polishing_table_be", () -> BlockEntityType.Builder.of(
                            GemPolishingTableBlockEntity::new, ModBlocks.GEM_POLISHING_TABLE.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
