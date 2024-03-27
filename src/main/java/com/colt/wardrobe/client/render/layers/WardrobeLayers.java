package com.colt.wardrobe.client.render.layers;

import com.colt.wardrobe.client.DecalRegistry;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.client.render.models.hats.BaseHatModel;
import com.colt.wardrobe.managers.HatManager;
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
import java.util.Arrays;
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
    public static boolean SelectibleDecal;


    public WardrobeLayers(RenderLayerParent<T, M> p_117346_) {
        super(p_117346_);

    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing,
                       float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    public void LayerRenderer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity,
                              float partialTick, String ModelName, int Layers, boolean IsColorible, boolean SelectibleDecal, int LayerCol,String DecalName, boolean IsMenu) {
        WardrobeLayers.ModelName = ModelName;
        WardrobeLayers.Layers = Layers;
        WardrobeLayers.IsColorible = IsColorible;
        WardrobeLayers.IsMenu = IsMenu;
        WardrobeLayers.SelectibleDecal = SelectibleDecal;

        BaseHatModel model = VARIANTS.getOrDefault(ModelName, DEFAULT_SUPPLIER).get();
        if (model == null)
            return;

        //See if it is for the menu or the player
        if (!IsMenu) {
            getParentModel().getHead().translateAndRotate(poseStack);
            // Setup Animation
            model.setupAnimation((Player) entity, entity.tickCount, partialTick);
        }
        poseStack.scale(1.01f, 1.01f, 1.01f);
        LayerColor = LayerCol;
        //Loops Per Layer through The render function and the Color
        for (int i = 0; i < Layers; i++) {
            LayerColor = GetLayerColor(ModelName, i);

            float r = (float) (LayerColor >> 16 & 255) / 255.0F;
            float g = (float) (LayerColor >> 8 & 255) / 255.0F;
            float b = (float) (LayerColor & 255) / 255.0F;

            float alpha = 1F;
            if (!SelectibleDecal || i == Layers-1)
            {
                renderModelPerLayer(poseStack, buffer, packedLight, false, model, r, g, b, alpha, (i == 0 ? "" : "_layer_" + i), ModelName);
            }
            else {
                renderModelPerLayer(poseStack, buffer, packedLight, false, model, r, g, b, alpha, "", DecalName);
            }
        }
    }

    private void renderModelPerLayer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint,
                                     BaseHatModel model, float r, float g, float b, Float aplha,
                                     @Nullable String textureLayer, String ModelName) {
        renderModel(poseStack, buffer, packedLight, glint, model, r, g, b, aplha,
                (Arrays.stream(DecalRegistry.Decals_16x16).toList().contains(ModelName) ? DecalRegistry.instance().GetSingle16x16Decal(ModelName) : getTextureLocation(ModelName,textureLayer)));
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint,
                             BaseHatModel model, float r, float g, float b, float alpha,
                             ResourceLocation textureLocation) {
        VertexConsumer vertexconsumer;

        vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(textureLocation), false,
                glint);

        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, alpha);
    }
    public static int GetLayerColor(String Model, int Layer) {
        return HatManager.instance().GetHat(ResourceLocation.tryParse(Model)).GetColorForLayer(Layer);
    }

    public static ResourceLocation getTextureLocation(String Model, String Layer) {
        return HatManager.instance().GetHat(ResourceLocation.tryParse(Model)).GetTexture(Layer);
    }
    public synchronized static void registerModel(ResourceLocation id, Supplier<BaseHatModel> model) {
        VARIANTS.putIfAbsent(id.toString(), model);
    }

    public static Supplier<BaseHatModel> getModel(String id) {
        return VARIANTS.getOrDefault(id, DEFAULT_SUPPLIER);
    }
}
