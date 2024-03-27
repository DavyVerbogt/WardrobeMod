package com.colt.wardrobe.client.render.models.hats;

import com.colt.wardrobe.Wardrobe;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class BaseballCapModel extends BaseHatModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Wardrobe.MOD_ID, "baseballcap"), "main");

    public BaseballCapModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(1.0F);

        PartDefinition hat = partdefinition.addOrReplaceChild("Hat",
                CubeListBuilder.create().texOffs(0, 51).addBox(-4.6F, -2.25F, -4.5F, 9.15F, 3.725F, 8.75F, new CubeDeformation(0.0F))
                        .texOffs(-13, -13).addBox(-8.1F, -8.5F, -10.825F, 16.0F, 16.0F, 12.7F, new CubeDeformation(-6.3F))
                        .texOffs(36, 58).addBox(-4.6F, 1.25F, -9.3F, 9.1F, 0.25F, 4.8F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnimation(@Nullable Player player, int animationTick, float partialTick) {
        Vec3 vector3d = player.getDeltaMovement();
        if (!player.isOnGround() && !player.isFallFlying() && vector3d.y < 0.0D) {
            Hat.y = (float) (-6.5f - Math.pow(-vector3d.y, 5D));
        } else {
            Hat.y = -6.5f;
        }
    }
}