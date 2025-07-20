package net.succ.succsmod.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.entity.ModBlockEntities;
import net.succ.succsmod.block.entity.custom.GemPolishingTableBlockEntity;
import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.entity.client.HedgehogModel;
import net.succ.succsmod.entity.client.ModModelLayers;
import net.succ.succsmod.entity.client.PukekoModel;
import net.succ.succsmod.entity.custom.HedgehogEntity;
import net.succ.succsmod.entity.custom.PukekoEntity;
import net.succ.succsmod.entity.custom.ToxicSlimeEntity;

@EventBusSubscriber(modid = SuccsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GEM_POLISHING_TABLE_BE.get(), GemPolishingTableBlockEntity::getItemHandler);

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.GEM_POLISHING_TABLE_BE.get(), GemPolishingTableBlockEntity::getFluidTank);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.PUKEKO, PukekoModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.HEDGEHOG, HedgehogModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.PUKEKO.get(), PukekoEntity.createAttributes().build());



        event.put(ModEntities.HEDGEHOG.get(), HedgehogEntity.createAttributes().build());

        event.put(ModEntities.TOXIC_SLIME.get(), ToxicSlimeEntity.createAttributes().build());

    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.PUKEKO.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);



        event.register(ModEntities.HEDGEHOG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(ModEntities.TOXIC_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING,
                ToxicSlimeEntity::checkToxicSlimeSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);

    }

}
