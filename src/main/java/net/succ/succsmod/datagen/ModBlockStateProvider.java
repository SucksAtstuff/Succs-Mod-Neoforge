package net.succ.succsmod.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
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

        blockItem(ModBlocks.SHATTERBLOOM_LOG);
        blockItem(ModBlocks.SHATTERBLOOM_WOOD);
        blockItem(ModBlocks.STRIPPED_SHATTERBLOOM_LOG);
        blockItem(ModBlocks.STRIPPED_SHATTERBLOOM_WOOD);

        blockWithItem(ModBlocks.SHATTERBLOOM_PLANKS);

        leavesBlock(ModBlocks.SHATTERBLOOM_LEAVES);
        saplingBlock(ModBlocks.SHATTERBLOOM_SAPLING);


        // Registering crop blocks with their respective item models
        makeCrop(((GarlicCropBlock) ModBlocks.GARLIC_CROP.get()), "garlic_stage", "garlic_stage");

        horizontalBlock(ModBlocks.GEM_POLISHING_TABLE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_table")));

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
}
