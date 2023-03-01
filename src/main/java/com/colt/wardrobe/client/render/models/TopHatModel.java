package com.colt.wardrobe.client.render.models;

import com.colt.wardrobe.Wardrobe;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
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

public class TopHatModel<T extends LivingEntity> extends AgeableListModel<T> {
	public ModelPart tophat;
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Wardrobe.MOD_ID, "top_hat"), "main");

	public TopHatModel(ModelPart root) {
		this.tophat = root.getChild("TopHat");
	}

	public static LayerDefinition createLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		CubeDeformation cubedeformation = new CubeDeformation(1.0F);

		partdefinition.addOrReplaceChild("TopHat", CubeListBuilder.create().texOffs(32, 50).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 54).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tophat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.tophat);
	}
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of();
	}


	@Override
	public void setupAnim(T entity, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		Vec3 vector3d = entity.getDeltaMovement();
		if (!entity.isOnGround() && !entity.isFallFlying() &&  vector3d.y < 0.0D) {
			tophat.y = (float) (-6.5f -Math.pow(-vector3d.y, 5D));
		}
		else
		{
			tophat.y = -6.5f;
		}
	}
}