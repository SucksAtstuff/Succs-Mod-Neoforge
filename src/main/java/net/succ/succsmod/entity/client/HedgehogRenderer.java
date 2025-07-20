package net.succ.succsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.entity.custom.HedgehogEntity;

public class HedgehogRenderer extends MobRenderer<HedgehogEntity, HedgehogModel> {
    public HedgehogRenderer(EntityRendererProvider.Context context) {
        super(context, new HedgehogModel(context.bakeLayer(ModModelLayers.HEDGEHOG)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(HedgehogEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "textures/entity/hedgehog/hedgehog.png");
    }

    @Override
    public void render(HedgehogEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.3f, 0.3f, 0.3f); // Smaller baby
        } else {
            poseStack.scale(0.5f, 0.5f, 0.5f); // Smaller adult
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
