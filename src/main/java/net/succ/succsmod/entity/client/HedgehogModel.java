package net.succ.succsmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.succ.succsmod.entity.custom.HedgehogEntity;

public class HedgehogModel extends HierarchicalModel<HedgehogEntity> {
    private final ModelPart hedgehog;
    private final ModelPart body;
    private final ModelPart legs;
    private final ModelPart back_legs;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart front_legs;
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart head;
    private final ModelPart ears;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart face;

    public HedgehogModel(ModelPart root) {
        this.hedgehog = root.getChild("hedgehog");
        this.body = this.hedgehog.getChild("body");
        this.legs = this.hedgehog.getChild("legs");
        this.back_legs = this.legs.getChild("back_legs");
        this.back_left_leg = this.back_legs.getChild("back_left_leg");
        this.back_right_leg = this.back_legs.getChild("back_right_leg");
        this.front_legs = this.legs.getChild("front_legs");
        this.front_right_leg = this.front_legs.getChild("front_right_leg");
        this.front_left_leg = this.front_legs.getChild("front_left_leg");
        this.head = this.hedgehog.getChild("head");
        this.ears = this.head.getChild("ears");
        this.left_ear = this.ears.getChild("left_ear");
        this.right_ear = this.ears.getChild("right_ear");
        this.face = this.head.getChild("face");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hedgehog = partdefinition.addOrReplaceChild("hedgehog", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition body = hedgehog.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legs = hedgehog.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back_legs = legs.addOrReplaceChild("back_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 6.0F));

        PartDefinition back_left_leg = back_legs.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

        PartDefinition back_right_leg = back_legs.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition front_legs = legs.addOrReplaceChild("front_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -6.0F));

        PartDefinition front_right_leg = front_legs.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

        PartDefinition front_left_leg = front_legs.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition head = hedgehog.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_ear = ears.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(13, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -9.0F, -4.0F));

        PartDefinition right_ear = ears.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(13, 25).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -7.0F, -4.0F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(1, 25).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 31).addBox(-2.0F, 2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -7.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(HedgehogEntity hedgehogEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(HedgehogAnimations.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(hedgehogEntity.idleAnimationState, HedgehogAnimations.idle, ageInTicks, 1f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        hedgehog.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return hedgehog;
    }


}
