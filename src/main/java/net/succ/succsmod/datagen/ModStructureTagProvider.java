// ModStructureTagProvider.java
package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModStructureTagProvider extends TagsProvider<Structure> {
    public ModStructureTagProvider(PackOutput output,
                                   CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   @Nullable ExistingFileHelper fileHelper) {
        super(output, Registries.STRUCTURE, lookupProvider, SuccsMod.MOD_ID, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Venomous Fen rock formations
        this.tag(ModTags.Structures.VENOMOUS_FEN_ROCK_FORMATIONS)
                .addOptional(id("gem_rock_4"))
                .addOptional(id("gem_rock_5"))
                .addOptional(id("gem_rock_6"));

        // Shattergrove rock formations
        this.tag(ModTags.Structures.SHATTERGROVE_ROCK_FORMATIONS)
                .addOptional(id("gem_rock_1"))
                .addOptional(id("gem_rock_2"))
                .addOptional(id("gem_rock_3"));

        // Crystalfrost vale rock formations
        this.tag(ModTags.Structures.CRYSTALFROST_VALE_ROCK_FORMATIONS)
                .addOptional(id("gem_rock_7"))
                .addOptional(id("gem_rock_8"))
                .addOptional(id("gem_rock_9"));
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, path);
    }


    @Override
    public String getName() {
        return "Structure Tags: " + SuccsMod.MOD_ID;
    }
}
