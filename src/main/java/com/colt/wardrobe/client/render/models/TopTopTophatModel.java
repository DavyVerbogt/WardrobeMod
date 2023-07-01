package com.colt.wardrobe.client.render.models;

import com.colt.wardrobe.Wardrobe;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;


public class TopTopTophatModel<T extends LivingEntity> extends AgeableListModel<T> {

	public ModelPart tophat;
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(Wardrobe.MOD_ID, "top_top_top_hat"), "main");


	public TopTopTophatModel(ModelPart root) {
		this.tophat = root.getChild("TopHat");
	}

	public static LayerDefinition createLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		CubeDeformation cubedeformation = new CubeDeformation(1.0F);
        
		partdefinition.addOrReplaceChild("TopHat", CubeListBuilder.create().texOffs(52, 56).addBox(-2.0F, -6.25F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(44, 56).addBox(-3.0F, -1.25F, -4.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition TopHat2 = partdefinition.addOrReplaceChild("TopHat2", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition Bottem_r1 = TopHat2.addOrReplaceChild("Bottem_r1", CubeListBuilder.create().texOffs(44, 56).addBox(-2.5052F, -1.3118F, -2.187F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.5F))
		.texOffs(52, 56).addBox(-1.5052F, -5.3118F, -1.187F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.5F, -0.1176F, -0.5681F, -0.2618F, 0.0F, 0.2618F));

		PartDefinition TopHat3 = partdefinition.addOrReplaceChild("TopHat3", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 0.0F));

		PartDefinition Bottem_r2 = TopHat3.addOrReplaceChild("Bottem_r2", CubeListBuilder.create().texOffs(44, 56).addBox(-2.7333F, -0.5269F, -3.6744F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.8F))
		.texOffs(52, 56).addBox(-1.7333F, -3.9269F, -2.6744F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(1.0F, -0.3176F, 2.7319F, -0.4363F, 0.0F, -0.4363F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		tophat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.tophat);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of();
	}

	@Override
	public void setupAnim(T entity, float p_102619_, float p_102620_, float p_102621_, float p_102622_,
			float p_102623_) {
		Vec3 vector3d = entity.getDeltaMovement();
		if (!entity.isOnGround() && !entity.isFallFlying() && vector3d.y < 0.0D) {
			tophat.y = (float) (-6.25f - Math.pow(-vector3d.y, 5D));
		} else {
			tophat.y = -6.25f;
		}
	}
}

