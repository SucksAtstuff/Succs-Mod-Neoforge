package net.succ.succsmod.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.block.custom.GarlicCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SuccsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Registering gem blocks with their respective item models
        blockWithItem(ModBlocks.ATHERIUM_BLOCK);
        blockWithItem(ModBlocks.MALACHITE_BLOCK);
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.SUNSTONE_BLOCK);
        blockWithItem(ModBlocks.JASPILITE_BLOCK);

        // Registering gem ore blocks with their respective item models
        simpleBlockWithItem(ModBlocks.ATHERIUM_ORE.get(), cubeAll(ModBlocks.ATHERIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.NETHER_ATHERIUM_ORE.get(), cubeAll(ModBlocks.NETHER_ATHERIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.END_ATHERIUM_ORE.get(), cubeAll(ModBlocks.END_ATHERIUM_ORE.get()));
        simpleBlockWithItem(ModBlocks.RUBY_ORE.get(), cubeAll(ModBlocks.RUBY_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_RUBY_ORE.get()));
        simpleBlockWithItem(ModBlocks.NETHER_RUBY_ORE.get(), cubeAll(ModBlocks.NETHER_RUBY_ORE.get()));
        simpleBlockWithItem(ModBlocks.END_RUBY_ORE.get(), cubeAll(ModBlocks.END_RUBY_ORE.get()));
        simpleBlockWithItem(ModBlocks.SUNSTONE_ORE.get(), cubeAll(ModBlocks.SUNSTONE_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get()));
        simpleBlockWithItem(ModBlocks.NETHER_SUNSTONE_ORE.get(), cubeAll(ModBlocks.NETHER_SUNSTONE_ORE.get()));
        simpleBlockWithItem(ModBlocks.END_SUNSTONE_ORE.get(), cubeAll(ModBlocks.END_SUNSTONE_ORE.get()));
        simpleBlockWithItem(ModBlocks.MALACHITE_ORE.get(), cubeAll(ModBlocks.MALACHITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_MALACHITE_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_MALACHITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.NETHER_MALACHITE_ORE.get(), cubeAll(ModBlocks.NETHER_MALACHITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.END_MALACHITE_ORE.get(), cubeAll(ModBlocks.END_MALACHITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.SAPPHIRE_ORE.get(), cubeAll(ModBlocks.SAPPHIRE_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get()));
        simpleBlockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE.get(), cubeAll(ModBlocks.NETHER_SAPPHIRE_ORE.get()));
        simpleBlockWithItem(ModBlocks.END_SAPPHIRE_ORE.get(), cubeAll(ModBlocks.END_SAPPHIRE_ORE.get()));
        simpleBlockWithItem(ModBlocks.JASPILITE_ORE.get(), cubeAll(ModBlocks.JASPILITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_JASPILITE_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_JASPILITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.NETHER_JASPILITE_ORE.get(), cubeAll(ModBlocks.NETHER_JASPILITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.END_JASPILITE_ORE.get(), cubeAll(ModBlocks.END_JASPILITE_ORE.get()));

        // Registering wood blocks with their respective item models
        logBlock(((RotatedPillarBlock) ModBlocks.SHATTERBLOOM_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SHATTERBLOOM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.SHATTERBLOOM_WOOD.get()), blockTexture(ModBlocks.SHATTERBLOOM_LOG.get()), blockTexture(ModBlocks.SHATTERBLOOM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SHATTERBLOOM_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SHATTERBLOOM_LOG.get()), blockTexture(ModBlocks.STRIPPED_SHATTERBLOOM_LOG.get()));


        // Registering shatterbloom blocks with their respective item models
        blockItem(ModBlocks.SHATTERBLOOM_LOG);
        blockItem(ModBlocks.SHATTERBLOOM_WOOD);
        blockItem(ModBlocks.STRIPPED_SHATTERBLOOM_LOG);
        blockItem(ModBlocks.STRIPPED_SHATTERBLOOM_WOOD);


        blockWithItem(ModBlocks.SHATTERBLOOM_PLANKS);

        leavesBlock(ModBlocks.SHATTERBLOOM_LEAVES);
        saplingBlock(ModBlocks.SHATTERBLOOM_SAPLING);

        doorBlockWithRenderType(((DoorBlock) ModBlocks.SHATTERBLOOM_DOOR.get()), modLoc("block/shatterbloom_door_bottom"), modLoc("block/shatterbloom_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.SHATTERBLOOM_TRAPDOOR.get()), modLoc("block/shatterbloom_trapdoor"),true, "cutout");
        stairsBlock(((StairBlock) ModBlocks.SHATTERBLOOM_STAIRS.get()), blockTexture(ModBlocks.SHATTERBLOOM_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.SHATTERBLOOM_SLAB.get()), blockTexture(ModBlocks.SHATTERBLOOM_PLANKS.get()), blockTexture(ModBlocks.SHATTERBLOOM_PLANKS.get()));

        blockItem(ModBlocks.SHATTERBLOOM_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.SHATTERBLOOM_SLAB);
        blockItem(ModBlocks.SHATTERBLOOM_STAIRS);
        
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.SHATTERBLOOM_PRESSURE_PLATE.get()), blockTexture(ModBlocks.SHATTERBLOOM_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.SHATTERBLOOM_BUTTON.get()), blockTexture(ModBlocks.SHATTERBLOOM_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.SHATTERBLOOM_FENCE.get()), blockTexture(ModBlocks.SHATTERBLOOM_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.SHATTERBLOOM_FENCE_GATE.get()), blockTexture(ModBlocks.SHATTERBLOOM_PLANKS.get()));

        blockItem(ModBlocks.SHATTERBLOOM_PRESSURE_PLATE);
        blockItem(ModBlocks.SHATTERBLOOM_FENCE_GATE);

        logBlock(((RotatedPillarBlock)ModBlocks.MYCELIAL_SPOREWOOD_LOG.get()));
        logBlock(((RotatedPillarBlock)ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock)ModBlocks.MYCELIAL_SPOREWOOD_WOOD.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_LOG.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock)ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_WOOD.get()), blockTexture(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_LOG.get()), blockTexture(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_LOG.get()));

        // Registering mycelial sporewood blocks with their respective item models
        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_LOG);
        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_LOG);
        blockItem(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_WOOD);

        blockWithItem(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS);

        leavesBlock(ModBlocks.MYCELIAL_SPOREWOOD_LEAVES);
        saplingBlock(ModBlocks.MYCELIAL_SPOREWOOD_SAPLING);

        doorBlockWithRenderType(((DoorBlock) ModBlocks.MYCELIAL_SPOREWOOD_DOOR.get()), modLoc("block/mycelial_sporewood_door_bottom"), modLoc("block/mycelial_sporewood_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.MYCELIAL_SPOREWOOD_TRAPDOOR.get()), modLoc("block/mycelial_sporewood_trapdoor"), true, "cutout");
        stairsBlock(((StairBlock) ModBlocks.MYCELIAL_SPOREWOOD_STAIRS.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.MYCELIAL_SPOREWOOD_SLAB.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get()));

        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_SLAB);
        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_STAIRS);

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.MYCELIAL_SPOREWOOD_PRESSURE_PLATE.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.MYCELIAL_SPOREWOOD_BUTTON.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.MYCELIAL_SPOREWOOD_FENCE.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.MYCELIAL_SPOREWOOD_FENCE_GATE.get()), blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get()));

        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_PRESSURE_PLATE);
        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_FENCE_GATE);

        simpleBlock(ModBlocks.POISON_LILY.get(),
                models().cross(blockTexture(ModBlocks.POISON_LILY.get()).getPath(), blockTexture(ModBlocks.POISON_LILY.get())).renderType("cutout"));

        simpleBlock(ModBlocks.POTTED_POISON_LILY.get(), models().singleTexture("potted_poison_lily", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.POISON_LILY.get())).renderType("cutout"));

        simpleBlock(ModBlocks.POTTED_SHATTERBLOOM_SAPLING.get(), models().singleTexture("potted_shatterbloom_sapling", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.SHATTERBLOOM_SAPLING.get())).renderType("cutout"));

        simpleBlock(ModBlocks.POTTED_MYCELIAL_SPOREWOOD_SAPLING.get(), models().singleTexture("potted_mycelial_sporewood_sapling", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.MYCELIAL_SPOREWOOD_SAPLING.get())).renderType("cutout"));

        simpleBlock(ModBlocks.POTTED_CRYOHEART_SAPLING.get(), models().singleTexture("potted_cryoheart_sapling", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.CRYOHEART_SAPLING.get())).renderType("cutout"));

        // Registering cryoheart blocks with their respective item models
        logBlock(((RotatedPillarBlock)ModBlocks.CRYOHEART_LOG.get()));
        logBlock(((RotatedPillarBlock)ModBlocks.STRIPPED_CRYOHEART_LOG.get()));
        axisBlock(((RotatedPillarBlock)ModBlocks.CRYOHEART_WOOD.get()), blockTexture(ModBlocks.CRYOHEART_LOG.get()), blockTexture(ModBlocks.CRYOHEART_LOG.get()));
        axisBlock(((RotatedPillarBlock)ModBlocks.STRIPPED_CRYOHEART_WOOD.get()), blockTexture(ModBlocks.STRIPPED_CRYOHEART_LOG.get()), blockTexture(ModBlocks.STRIPPED_CRYOHEART_LOG.get()));

        // Registering cryoheart blocks with their respective item models
        blockItem(ModBlocks.CRYOHEART_LOG);
        blockItem(ModBlocks.CRYOHEART_WOOD);
        blockItem(ModBlocks.STRIPPED_CRYOHEART_LOG);
        blockItem(ModBlocks.STRIPPED_CRYOHEART_WOOD);

        blockWithItem(ModBlocks.CRYOHEART_PLANKS);
        leavesBlock(ModBlocks.CRYOHEART_LEAVES);
        saplingBlock(ModBlocks.CRYOHEART_SAPLING);
        doorBlockWithRenderType(((DoorBlock) ModBlocks.CRYOHEART_DOOR.get()), modLoc("block/cryoheart_door_bottom"), modLoc("block/cryoheart_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.CRYOHEART_TRAPDOOR.get()), modLoc("block/cryoheart_trapdoor"), true, "cutout");
        stairsBlock(((StairBlock) ModBlocks.CRYOHEART_STAIRS.get()), blockTexture(ModBlocks.CRYOHEART_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.CRYOHEART_SLAB.get()), blockTexture(ModBlocks.CRYOHEART_PLANKS.get()), blockTexture(ModBlocks.CRYOHEART_PLANKS.get()));
        blockItem(ModBlocks.CRYOHEART_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.CRYOHEART_SLAB);
        blockItem(ModBlocks.CRYOHEART_STAIRS);
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.CRYOHEART_PRESSURE_PLATE.get()), blockTexture(ModBlocks.CRYOHEART_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.CRYOHEART_BUTTON.get()), blockTexture(ModBlocks.CRYOHEART_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.CRYOHEART_FENCE.get()), blockTexture(ModBlocks.CRYOHEART_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.CRYOHEART_FENCE_GATE.get()), blockTexture(ModBlocks.CRYOHEART_PLANKS.get()));
        blockItem(ModBlocks.CRYOHEART_PRESSURE_PLATE);
        blockItem(ModBlocks.CRYOHEART_FENCE_GATE);

        // Registering Emberpine blocks with their respective item models
        logBlock(((RotatedPillarBlock)ModBlocks.EMBERPINE_LOG.get()));
        logBlock(((RotatedPillarBlock)ModBlocks.STRIPPED_EMBERPINE_LOG.get()));
        axisBlock(((RotatedPillarBlock)ModBlocks.EMBERPINE_WOOD.get()), blockTexture(ModBlocks.EMBERPINE_LOG.get()), blockTexture(ModBlocks.EMBERPINE_LOG.get()));
        axisBlock(((RotatedPillarBlock)ModBlocks.STRIPPED_EMBERPINE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_EMBERPINE_LOG.get()), blockTexture(ModBlocks.STRIPPED_EMBERPINE_LOG.get()));

        // Registering Emberpine blocks with their respective item models
        blockItem(ModBlocks.EMBERPINE_LOG);
        blockItem(ModBlocks.EMBERPINE_WOOD);
        blockItem(ModBlocks.STRIPPED_EMBERPINE_LOG);
        blockItem(ModBlocks.STRIPPED_EMBERPINE_WOOD);

        blockWithItem(ModBlocks.EMBERPINE_PLANKS);

        // EMBERPINE woodset
        doorBlockWithRenderType(((DoorBlock) ModBlocks.EMBERPINE_DOOR.get()),
                modLoc("block/emberpine_door_bottom"),
                modLoc("block/emberpine_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.EMBERPINE_TRAPDOOR.get()),
                modLoc("block/emberpine_trapdoor"),
                true,
                "cutout");

        stairsBlock(((StairBlock) ModBlocks.EMBERPINE_STAIRS.get()),
                blockTexture(ModBlocks.EMBERPINE_PLANKS.get()));

        slabBlock(((SlabBlock) ModBlocks.EMBERPINE_SLAB.get()),
                blockTexture(ModBlocks.EMBERPINE_PLANKS.get()),
                blockTexture(ModBlocks.EMBERPINE_PLANKS.get()));

        blockItem(ModBlocks.EMBERPINE_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.EMBERPINE_SLAB);
        blockItem(ModBlocks.EMBERPINE_STAIRS);

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.EMBERPINE_PRESSURE_PLATE.get()),
                blockTexture(ModBlocks.EMBERPINE_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.EMBERPINE_BUTTON.get()),
                blockTexture(ModBlocks.EMBERPINE_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.EMBERPINE_FENCE.get()),
                blockTexture(ModBlocks.EMBERPINE_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBlocks.EMBERPINE_FENCE_GATE.get()),
                blockTexture(ModBlocks.EMBERPINE_PLANKS.get()));

        blockItem(ModBlocks.EMBERPINE_PRESSURE_PLATE);
        blockItem(ModBlocks.EMBERPINE_FENCE_GATE);



        // Registering crop blocks with their respective item models
        makeCrop(((GarlicCropBlock) ModBlocks.GARLIC_CROP.get()), "garlic_stage", "garlic_stage");

        horizontalBlock(ModBlocks.GEM_POLISHING_TABLE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_table")));


        getMultipartBuilder(ModBlocks.MYCELIAL_SPOREWOOD_VINE.get())
                .part().modelFile(vineFaceModel("north")).rotationY(0).addModel().condition(BlockStateProperties.NORTH, true).end()
                .part().modelFile(vineFaceModel("south")).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, true).end()
                .part().modelFile(vineFaceModel("east")).rotationY(90).addModel().condition(BlockStateProperties.EAST, true).end()
                .part().modelFile(vineFaceModel("west")).rotationY(270).addModel().condition(BlockStateProperties.WEST, true).end()
                .part().modelFile(vineFaceModel("up")).rotationX(270).addModel().condition(BlockStateProperties.UP, true).end();
        blockItem(ModBlocks.MYCELIAL_SPOREWOOD_VINE);

        simpleBlockWithItem(ModBlocks.SCORCHED_SAND.get(), cubeAll(ModBlocks.SCORCHED_SAND.get()));


    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((GarlicCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "block/" + textureName + state.getValue(((GarlicCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("succsessentials:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("succsessentials:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private ModelFile vineFaceModel(String face) {
        return models()
                .withExistingParent("mycelial_sporewood_vine_" + face, mcLoc("block/vine"))
                .texture("vine", modLoc("block/mycelial_sporewood_vine"))
                .renderType("cutout");
    }
}
