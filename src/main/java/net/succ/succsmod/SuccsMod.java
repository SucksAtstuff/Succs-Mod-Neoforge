package net.succ.succsmod;

import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.block.entity.ModBlockEntities;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.entity.client.HedgehogRenderer;
import net.succ.succsmod.entity.client.PukekoRenderer;
import net.succ.succsmod.item.ModCreativeModeTabs;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.loot.ModLootModifiers;
import net.succ.succsmod.potion.ModPotions;
import net.succ.succsmod.recipe.ModRecipes;
import net.succ.succsmod.screen.ModMenuTypes;
import net.succ.succsmod.screen.custom.GemPolishingTableBlockScreen;
import net.succ.succsmod.sound.ModSounds;
import net.succ.succsmod.villager.ModVillagers;
import net.succ.succsmod.worldgen.biome.ModBiomes;
import net.succ.succsmod.worldgen.biome.ModSurfaceRules;
import net.succ.succsmod.worldgen.tree.ModTreeDecoratorTypes;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SuccsMod.MOD_ID)
public class SuccsMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "succsessentials";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public SuccsMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        ModEffects.register(modEventBus);

        ModPotions.register(modEventBus);
        ModVillagers.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModSounds.register(modEventBus);

        ModTreeDecoratorTypes.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModBiomes.registerBiomes();
        event.enqueueWork(()->{
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.POISON_LILY.getId(), ModBlocks.POTTED_POISON_LILY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SHATTERBLOOM_SAPLING.getId(), ModBlocks.POTTED_SHATTERBLOOM_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MYCELIAL_SPOREWOOD_SAPLING.getId(), ModBlocks.POTTED_MYCELIAL_SPOREWOOD_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CRYOHEART_SAPLING.getId(), ModBlocks.POTTED_CRYOHEART_SAPLING);
        });

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeShatterGroveRules());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeVenomousFenRules());
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.PUKEKO.get(), PukekoRenderer::new);

            EntityRenderers.register(ModEntities.HEDGEHOG.get(), HedgehogRenderer::new);

            EntityRenderers.register(ModEntities.TOXIC_SLIME.get(), ctx ->
                    new SlimeRenderer(ctx) {
                        @Override
                        public ResourceLocation getTextureLocation(Slime entity) {
                            return ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "textures/entity/toxic_slime/toxic_slime.png");
                        }
                    }
            );

        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.GEM_POLISHING_MENU.get(), GemPolishingTableBlockScreen::new);
        }
    }
}
