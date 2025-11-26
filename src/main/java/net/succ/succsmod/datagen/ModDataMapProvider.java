package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider
{
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.GARLIC.getId(), new Compostable(0.45f), false);

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModBlocks.GLOWCAP_WART_BLOCK.getId(), new Compostable(0.85f), false);

    }
}

