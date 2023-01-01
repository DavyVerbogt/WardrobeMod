package com.colt.wardrobe.gui.Wardrobe;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

import net.minecraft.world.entity.LivingEntity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EntityRender {

    public static void renderEntityInInventory(int p_98851_, int p_98852_, int p_98853_, float p_98854_, float p_98855_,
            LivingEntity p_98856_, boolean flipChar) {
        float f = (float) Math.atan((double) (p_98854_ / 40.0F));
        float f1 = (float) Math.atan((double) (p_98855_ / 40.0F));
        renderEntityInInventoryRaw(p_98851_, p_98852_, p_98853_, f, f1, p_98856_, flipChar);
    }

    public static void renderEntityInInventoryRaw(int p_98851_, int p_98852_, int p_98853_, float angleXComponent,
            float angleYComponent, LivingEntity p_98856_, boolean flipChar) {
        float f = angleXComponent;
        float f1 = angleYComponent;
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate((double) p_98851_, (double) p_98852_, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float) p_98853_, (float) p_98853_, (float) p_98853_);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        float f2 = p_98856_.yBodyRot;
        float f3 = p_98856_.getYRot();
        float f4 = p_98856_.getXRot();
        float f5 = p_98856_.yHeadRotO;
        float f6 = p_98856_.yHeadRot;
        if (flipChar) {
            p_98856_.yBodyRot = f * 20.0F;
            p_98856_.setYRot(f * 40.0F);

        } else {
        p_98856_.yBodyRot = 180.0F + f * 20.0F;
        p_98856_.setYRot(180.0F + f * 40.0F);

        }
        p_98856_.setXRot(-f1 * 20.0F);
        p_98856_.yHeadRot = p_98856_.getYRot();
        p_98856_.yHeadRotO = p_98856_.getYRot();
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers()
                .bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(p_98856_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1,
                    multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        p_98856_.yBodyRot = f2;
        p_98856_.setYRot(f3);
        p_98856_.setXRot(f4);
        p_98856_.yHeadRotO = f5;
        p_98856_.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

}
