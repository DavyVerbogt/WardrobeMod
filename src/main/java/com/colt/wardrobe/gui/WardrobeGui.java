package com.colt.wardrobe.gui;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.gui.Wardrobe.EntityRender;
import com.colt.wardrobe.gui.Wardrobe.HeaderGui;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import se.mickelus.mutil.gui.GuiButton;
import se.mickelus.mutil.gui.GuiElement;
import se.mickelus.mutil.gui.GuiTexture;
import se.mickelus.mutil.gui.animation.Applier;
import se.mickelus.mutil.gui.animation.KeyframeAnimation;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WardrobeGui extends Screen {

    private final GuiElement defaultGui;
    private final HeaderGui header;
    private static WardrobeGui instance = null;

    private final GuiButton flipBtn;
    private boolean flipChar = false;

    protected WardrobeGui() {
        super(Component.translatable("wardrobe:wardrobegui"));

        width = 320;
        height = 240;

        defaultGui = new GuiElement(0, 0, width, height);// RotatePlayer()
        flipBtn = new GuiButton(width, height, "Flip Character", () -> flipChar());
        defaultGui.addChild(flipBtn);

        header = new HeaderGui(0, 0, width, height);
        defaultGui.addChild(header);

    }

    @Override
    public void render(PoseStack matrixStack, final int mouseX, final int mouseY, final float partialTicks) {
        renderBackground(matrixStack, 0);

        defaultGui.updateFocusState((width - defaultGui.getWidth()) / 2, (height - defaultGui.getHeight()) / 2, mouseX,
                mouseY);
        defaultGui.draw(matrixStack, (width - defaultGui.getWidth()) / 2, (height - defaultGui.getHeight()) / 2,
                width, height, mouseX, mouseY, 1);

        int entityPosLeft = (width / 4) * 3;
        int entityPosTop = (height / 3) * 2;

        EntityRender.renderEntityInInventory(entityPosLeft, entityPosTop, 100, (float) entityPosLeft - mouseX,
                (float) ((entityPosTop - 120) - mouseY) / 6,
                this.minecraft.player, flipChar);
    }

    public void flipChar() {
        Wardrobe.LOGGER.info(Wardrobe.MOD_ID + " " + this.flipChar);
        this.flipChar = !this.flipChar;
    }

    public static WardrobeGui getInstance() {
        if (instance == null) {
            instance = new WardrobeGui();
        }

        return instance;
    }

}