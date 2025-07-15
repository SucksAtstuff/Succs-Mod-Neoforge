package net.succ.succsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.entity.custom.PukekoEntity;

public class PukekoRenderer extends MobRenderer<PukekoEntity, PukekoModel> {
    public PukekoRenderer(EntityRendererProvider.Context context) {
        super(context, new PukekoModel(context.bakeLayer(ModModelLayers.PUKEKO)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(PukekoEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "textures/entity/pukeko/pukeko.png");
    }

    @Override
    public void render(PukekoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
