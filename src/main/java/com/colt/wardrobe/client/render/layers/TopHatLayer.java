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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Objects;

public class TopHatLayer <T extends LivingEntity> extends RenderLayer<T, PlayerModel<T>>{

    private static ResourceLocation TopHatTexture;
    public TopHatModel topHatModel;
    public static boolean TurnTophatOn = false;
public static int Layer1Color = Color.BLACK.hashCode();
    public static int RedLayer1 = 255;
    public static int GreenLayer1 = 255;
    public static int BlueLayer1 = 255;

    public TopHatLayer(RenderLayerParent<T, PlayerModel<T>> renderer, EntityModelSet modelSet)
    {
        super(renderer);
        this.topHatModel = new TopHatModel(modelSet.bakeLayer(TopHatModel.LAYER_LOCATION));
    }

    protected ResourceLocation getTextureLocation(T entity, String layer) {
        return switch (LayersConfig.LAYER_CHOICE.get())
                {
                    case TOP_HAT ->  TopHatTexture = new ResourceLocation(Wardrobe.MOD_ID, "textures/player/hats/tophat/top_hat" + layer +".png");
                };
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (TurnTophatOn)
        {
            colorRenederer(poseStack,buffer,packedLight,entity,limbSwing,limbSwingAmount,partialTick,ageInTicks,netHeadYaw,headPitch);
        }
    }
    public void colorRenederer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.getParentModel().copyPropertiesTo(this.topHatModel);

        // Color Int
        int colorLayer1 = (RedLayer1 & 0xff) << 16 | (GreenLayer1 & 0xff) << 8 | (BlueLayer1 & 0xff);

        //RGB Value layer 1
        float r = (float)(Layer1Color >> 16 & 255) / 255.0F;
        float g = (float)(Layer1Color >> 8 & 255) / 255.0F;
        float b = (float)(Layer1Color & 255) / 255.0F;

        //RGB Value layer 2
        float r1 = (float)(colorLayer1 >> 16 & 255) / 255.0F;
        float g1 = (float)(colorLayer1 >> 8 & 255) / 255.0F;
        float b1 = (float)(colorLayer1 & 255) / 255.0F;

        //Alpha Layer 1
        float alpha = 1F;
        //Alpha layer 2
        float alpha1 = 1F;

        getParentModel().getHead().translateAndRotate(poseStack);
        poseStack.scale(1.01f,1.01f,1.01f);

        //Setup Animation
        this.topHatModel.setupAnim(entity, limbSwing, limbSwingAmount, partialTick, netHeadYaw, headPitch);

        //Render Layer 1
        this.renderModelPerLayer(poseStack, buffer, packedLight, false, topHatModel,entity, r, g, b,alpha, "" );
        //Render Layer 2
        this.renderModelPerLayer(poseStack, buffer, packedLight, false, topHatModel,entity, r1, g1, b1, alpha1,"_layer_1");
    }

    private void renderModelPerLayer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint, net.minecraft.client.model.Model  model, Entity entity, float r, float g, float b, Float aplha,@Nullable String textureLayer) {
        renderModel(poseStack, buffer, packedLight, glint, model, r, g, b, aplha,this.getTextureLocation((T) entity, textureLayer));
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean glint, net.minecraft.client.model.Model model, float r, float g, float b,float alpha, ResourceLocation textureLocation) {
        VertexConsumer vertexconsumer;

            vertexconsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(textureLocation), false, glint);

        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, alpha);
    }
}