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


public class TopTopTophatModel extends BaseHatModel {


    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Wardrobe.MOD_ID, "toptoptophat"), "main");

    public TopTopTophatModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createLayer() {

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();


        PartDefinition TophatAll = partdefinition.addOrReplaceChild("Hat", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -31.75F, 4.0F, -0.2618F, 0.0F, 0.2618F));

        PartDefinition TopHat = TophatAll.addOrReplaceChild("TopHat", CubeListBuilder.create().texOffs(52, 56).addBox(-2.0F, -6.25F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(44, 56).addBox(-3.0F, -1.25F, -4.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition TopHat2 = TophatAll.addOrReplaceChild("TopHat2", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition Bottem_r1 = TopHat2.addOrReplaceChild("Bottem_r1", CubeListBuilder.create().texOffs(24, 56).addBox(-2.5052F, -1.3118F, -2.187F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.5F))
                .texOffs(32, 56).addBox(-1.5052F, -5.3118F, -1.187F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.5F, -0.1176F, -0.5681F, -0.2618F, 0.0F, 0.2618F));

        PartDefinition TopHat3 = TophatAll.addOrReplaceChild("TopHat3", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition Bottem_r2 = TopHat3.addOrReplaceChild("Bottem_r2", CubeListBuilder.create().texOffs(4, 56).addBox(-2.7333F, -0.5269F, -3.6744F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.8F))
                .texOffs(12, 56).addBox(-1.7333F, -3.9269F, -2.6744F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(1.0F, -0.3176F, 2.7319F, -0.4363F, 0.0F, -0.4363F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnimation(@Nullable Player player, int animationTick, float partialTick) {
        Vec3 vector3d = player.getDeltaMovement();
        if (!player.isOnGround() && !player.isFallFlying() && vector3d.y < 0.0D) {
            Hat.y = (float) (-6.25f - Math.pow(-vector3d.y, 5D));
        } else {
            Hat.y = -6.25f;
        }
    }
}