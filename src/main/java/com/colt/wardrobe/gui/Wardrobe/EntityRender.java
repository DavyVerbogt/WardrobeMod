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
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class EntityRender {

    public static void renderEntityInInventory(int entityPosLeft, int entityPosTop, int size, int rotatePlayer,
                                               float MouseLookX, float MouseLookY, LivingEntity Player) {
        float f = (float) Math.atan(MouseLookX / 40.0F);
        float f1 = (float) Math.atan(MouseLookY / 40.0F);
        renderEntityInInventoryRaw(entityPosLeft, entityPosTop, size, rotatePlayer, f, f1, Player);
    }

    public static void renderEntityInInventoryRaw(int entityPosLeft, int entityPosTop, int size, int rotatePlayer,
                                                  float angleXComponent, float angleYComponent, LivingEntity Player) {
        float f = angleXComponent;
        float f1 = angleYComponent;
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate(entityPosLeft, entityPosTop, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float) size, (float) size, (float) size);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        float f2 = Player.yBodyRot;
        float f3 = Player.getYRot();
        float f4 = Player.getXRot();
        float f5 = Player.yHeadRotO;
        float f6 = Player.yHeadRot;

        Player.yBodyRot = rotatePlayer + 180 + f * 20.0F;
        Player.setYRot(rotatePlayer + 180 + f * 40.0F);

        Player.setXRot(-f1 * 20.0F);
        Player.yHeadRot = Player.getYRot();
        Player.yHeadRotO = Player.getYRot();
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers()
                .bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(Player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1,
                    multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        Player.yBodyRot = f2;
        Player.setYRot(f3);
        Player.setXRot(f4);
        Player.yHeadRotO = f5;
        Player.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

    public static void scissor(int x, int y, int width, int height) {
        Minecraft mc = Minecraft.getInstance();
        int scale = (int) mc.getWindow().getGuiScale();
        GL11.glScissor(x * scale, mc.getWindow().getScreenHeight() - y * scale - height * scale,
                Math.max(0, width * scale), Math.max(0, height * scale));
    }

}
