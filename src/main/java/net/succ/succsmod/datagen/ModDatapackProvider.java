package net.succ.succsmod.datagen;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.succ.succsmod.SuccsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.succ.succsmod.worldgen.ModBiomeModifiers;
import net.succ.succsmod.worldgen.ModConfiguredFeatures;
import net.succ.succsmod.worldgen.ModPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();



    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(SuccsMod.MOD_ID));
    }
}