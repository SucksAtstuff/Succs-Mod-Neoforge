package net.succ.succsmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
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
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.SUNSTONE_BLOCK);
        blockWithItem(ModBlocks.MALACHITE_BLOCK);

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

        // Registering crop blocks with their respective item models
        makeCrop(((GarlicCropBlock) ModBlocks.GARLIC_CROP.get()), "garlic_stage", "garlic_stage");

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

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
