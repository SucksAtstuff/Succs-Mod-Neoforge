package net.succ.succsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.succ.succsmod.entity.custom.PukekoEntity;

public class PukekoModel extends HierarchicalModel<PukekoEntity> {
    private final ModelPart Body;
    private final ModelPart head;
    private final ModelPart LEG;

    public PukekoModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.head = this.Body.getChild("head");
        this.LEG = this.Body.getChild("LEG");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

        PartDefinition head = Body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -3.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(3, 9).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 4.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.5672F, 0.0F, 0.0F));

        PartDefinition LEG = Body.addOrReplaceChild("LEG", CubeListBuilder.create().texOffs(10, 9).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition cube_r3 = LEG.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(6, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, -1.0F, -1.5708F, -0.7854F, 0.0F));

        PartDefinition cube_r4 = LEG.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r5 = LEG.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 14).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.0F, -1.0F, -1.5708F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(PukekoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(PukekoAnimations.he_walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, PukekoAnimations.he_sit, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch){
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return Body;
    }
}
