package com.colt.wardrobe.client.render.layers;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.client.render.models.hats.BaseHatModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HatLayer<T extends Player, M extends PlayerModel<T>> extends RenderLayer<T, M> {
    private static final Map<String, Supplier<BaseHatModel>> VARIANTS = new HashMap<>();
    private static final Supplier<BaseHatModel> DEFAULT_SUPPLIER = () -> ModelInstences.get().getTopTopTopHat();

    public static boolean TurnTophatOn = false;
    public static String ModelName;
    public static int Layers;
    public static boolean IsColorible;
    public static int Layer0Color = Color.BLACK.hashCode();
    public static int Layer1Color = Color.WHITE.hashCode();

    public HatLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing,
                       float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (TurnTophatOn) {
            HatRenderer(poseStack, buffer, packedLight, entity, partialTick);
        }
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

    public void HatRenderer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity,
                                       float partialTick) {

        BaseHatModel model = VARIANTS.getOrDefault(ModelName, DEFAULT_SUPPLIER).get();
        if (model == null)
            return;


        getParentModel().getHead().translateAndRotate(poseStack);
        poseStack.scale(1.01f, 1.01f, 1.01f);

        // Setup Animation
        model.setupAnimation(entity, entity.tickCount, partialTick);

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
            Wardrobe.LOGGER.info("Wardrobe: "+ ModelName);
            Wardrobe.LOGGER.info("Wardrobe: " + Layers);
            Wardrobe.LOGGER.info("Wardrobe: "+ IsColorible);

            renderModelPerLayer(poseStack, buffer, packedLight, false, model, r, g, b, alpha, (i < 1 ? "_layer_" + i : ""));
        }
    }

    private void renderModelPerLayer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint,
                                     BaseHatModel model, float r, float g, float b, Float aplha,
                                     @Nullable String textureLayer) {
        renderModel(poseStack, buffer, packedLight, glint, model, r, g, b, aplha,
                this.getTextureLocation(textureLayer));
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint,
                             BaseHatModel model, float r, float g, float b, float alpha,
                             ResourceLocation textureLocation) {
        VertexConsumer vertexconsumer;

        vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(textureLocation), false,
                glint);

        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, alpha);
    }

    public synchronized static void registerModel(ResourceLocation id, Supplier<BaseHatModel> model) {
        VARIANTS.putIfAbsent(id.toString(), model);
    }

    public static Supplier<BaseHatModel> getModel(String id) {
        return VARIANTS.getOrDefault(id, DEFAULT_SUPPLIER);
    }
}