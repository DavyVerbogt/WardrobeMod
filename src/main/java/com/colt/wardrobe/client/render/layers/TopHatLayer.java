package com.colt.wardrobe.client.render.layers;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.ClientStuff;
import com.colt.wardrobe.client.render.models.TopHatModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

import java.util.Objects;

public class TopHatLayer <T extends LivingEntity> extends RenderLayer<T, PlayerModel<T>>{

    private static final ResourceLocation TOP_HAT_TEXTURE = new ResourceLocation(Wardrobe.MOD_ID + "texture/entity/hats/top_hat.png");
    public TopHatModel topHatModel;

    public TopHatLayer(RenderLayerParent<T, PlayerModel<T>> renderer, EntityModelSet modelSet)
    {
        super(renderer);
        this.topHatModel = new TopHatModel(modelSet.bakeLayer(TopHatModel.LAYER_LOCATION));
    }

    @Override
    protected ResourceLocation getTextureLocation(T entity) {
        return switch (LayersConfig.LAYER_CHOICE.get())
                {
                    case TOP_HAT -> TOP_HAT_TEXTURE;
                };
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        poseStack.pushPose();
        poseStack.translate(0.0D, 0.0D, 0.125D);
            this.getParentModel().copyPropertiesTo(this.topHatModel);
            this.topHatModel.withAnimations(entity);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(TOP_HAT_TEXTURE), false, false);
            this.topHatModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();

    }
    }



