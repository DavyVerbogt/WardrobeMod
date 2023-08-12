package com.colt.wardrobe.client.render.layers;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.client.render.models.hats.BaseHatModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class WardrobeLayers<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {

    private static final Map<String, Supplier<BaseHatModel>> VARIANTS = new HashMap<>();
    private static final Supplier<BaseHatModel> DEFAULT_SUPPLIER = () -> ModelInstences.get().getTopTopTopHat();

    public static boolean IsMenu = false;
    public static String ModelName;
    public static int Layers;
    public static boolean IsColorible;
    public static int LayerColor = Color.BLACK.hashCode();

    public WardrobeLayers(RenderLayerParent<T, M> p_117346_)
    {
        super(p_117346_);

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

    }

    public void LayerRenderer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity,
                              float partialTick,  String ModelName, int Layers, boolean IsColorible, int LayerCol, boolean IsMenu) {

        this.ModelName = ModelName;
        this.Layers = Layers;
        this.IsColorible = IsColorible;
        this.LayerColor = LayerCol;
        this.IsMenu = IsMenu;

        BaseHatModel model = VARIANTS.getOrDefault(ModelName, DEFAULT_SUPPLIER).get();
        if (model == null)
            return;

if (!IsMenu)
{
    
    getParentModel().getHead().translateAndRotate(poseStack);
    model.setupAnimation((Player) entity, entity.tickCount, partialTick);
}

        poseStack.scale(1.01f, 1.01f, 1.01f);

        // Setup Animation

        for (int i = 0; i < Layers; i++) {
            int ColorBackup = LayerColor;
            if (!IsColorible) {
                LayerColor = Color.WHITE.hashCode();
            } else {
                LayerColor = ColorBackup;
            }
            float r = (float) (LayerColor >> 16 & 255) / 255.0F;
            float g = (float) (LayerColor >> 8 & 255) / 255.0F;
            float b = (float) (LayerColor & 255) / 255.0F;

            float alpha = 1F;
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
