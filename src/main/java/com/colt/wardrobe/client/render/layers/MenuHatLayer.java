package com.colt.wardrobe.client.render.layers;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.hats.BaseHatModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;
import java.awt.*;

public class MenuHatLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public static String ModelName;
    public static int Layers;
    public static boolean IsColorible;
    public static boolean RenderYessPlease = false;

    public static int Layer0Color = Color.BLACK.hashCode();

    public MenuHatLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }


    protected ResourceLocation getTextureLocation(String layer) {
        return switch (ModelName) {
            case "coltwardrobe:tophat" -> new ResourceLocation(Wardrobe.MOD_ID,
                    "textures/player/hats/tophat/top_hat" + layer + ".png");

            case "coltwardrobe:toptoptophat" -> new ResourceLocation(Wardrobe.MOD_ID,
                    "textures/player/hats/toptoptophat/top_hat" + layer + ".png");

            case "coltwardrobe:arrow" -> new ResourceLocation(Wardrobe.MOD_ID,
                    "textures/player/hats/arrow/arrow.png");
            default -> null;
        };
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing,
                       float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (RenderYessPlease) {
            TwoLayercolorRenederer(poseStack, buffer, packedLight, entity, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
            RenderYessPlease = false;
        }
    }

    public void TwoLayercolorRenederer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity,
                               float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw,
                               float headPitch) {

        BaseHatModel model = HatLayer.getModel(ModelName).get();


        for (int i = 0; i < Layers; i++) {
            int ColorBackup = Layer0Color;
            if (!IsColorible) {
                Layer0Color =  Color.WHITE.hashCode();
            }
            else {
                Layer0Color = ColorBackup;
            }

            float r = (float) (Layer0Color >> 16 & 255) / 255.0F;
            float g = (float) (Layer0Color >> 8 & 255) / 255.0F;
            float b = (float) (Layer0Color & 255) / 255.0F;


            float alpha = 1F;

            renderModelPerLayer(poseStack, buffer, packedLight, false, model, r, g, b, alpha, i < 1 ? "_layer_" + i : "");
        }
    }

    private void renderModelPerLayer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint,
                                     net.minecraft.client.model.Model model, float r, float g, float b, Float aplha,
                                     @Nullable String textureLayer) {
        renderModel(poseStack, buffer, packedLight, glint, model, r, g, b, aplha,
                this.getTextureLocation(textureLayer));
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint,
                             net.minecraft.client.model.Model model, float r, float g, float b, float alpha,
                             ResourceLocation textureLocation) {
        VertexConsumer vertexconsumer;

        vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(textureLocation), false,
                glint);

        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, alpha);
    }
}