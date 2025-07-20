package net.succ.succsmod.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.succ.succsmod.SuccsMod;

public class ModModelLayers {
    public static final ModelLayerLocation PUKEKO = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "pukeko"), "main");

    public static final ModelLayerLocation HEDGEHOG = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "hedgehog"), "main");
}
