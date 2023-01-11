package com.colt.wardrobe.client.render.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.sql.Time;
import java.util.Timer;

public class TopHatModel extends HumanoidModel {
	public ModelPart tophat;

	public TopHatModel(ModelPart root) {
		super(root);
		this.tophat = root.getChild("head").getChild("TopHat");
	}

	public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		PartDefinition TopHat = head.addOrReplaceChild("TopHat", CubeListBuilder.create().texOffs(32, 50).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 54).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public TopHatModel withAnimations(LivingEntity entity){
		Vec3 vector3d = entity.getDeltaMovement();
		if (!entity.isOnGround() && !entity.isFallFlying() &&  vector3d.y < 0.0D) {
			tophat.y = (float) (-6.5f -Math.pow(-vector3d.y, 5D));
		}
		else
		{
			tophat.y = -6.5f;
		}
		return  this;
	}
}