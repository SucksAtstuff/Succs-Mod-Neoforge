package net.succ.succsmod.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.entity.ModBlockEntities;
import net.succ.succsmod.block.entity.custom.GemPolishingTableBlockEntity;

@EventBusSubscriber(modid = SuccsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GEM_POLISHING_TABLE_BE.get(), GemPolishingTableBlockEntity::getItemHandler);


        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.GEM_POLISHING_TABLE_BE.get(), GemPolishingTableBlockEntity::getFluidTank);
    }
}
