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

public class TopHatModel extends BaseHatModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Wardrobe.MOD_ID, "tophat"), "main");

    public TopHatModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(1.0F);

        PartDefinition hat = partdefinition.addOrReplaceChild("Hat",
                CubeListBuilder.create().texOffs(32, 50)
                        .addBox(-4.0F, -7.5F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 54).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(1.0F)),
                PartPose.offsetAndRotation(0.0F, -6.5F, 0.0F, -0.1309F, 0.0F, 0.0F));

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