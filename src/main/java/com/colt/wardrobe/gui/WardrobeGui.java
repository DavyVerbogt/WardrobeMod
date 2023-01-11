package com.colt.wardrobe.gui;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.gui.Wardrobe.EntityRender;
import com.colt.wardrobe.gui.Wardrobe.HeaderGui;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent.MouseScrolled;
import se.mickelus.mutil.gui.GuiButton;
import se.mickelus.mutil.gui.GuiElement;

@OnlyIn(Dist.CLIENT)
public class WardrobeGui extends Screen {

    private final GuiElement defaultGui;
    private final HeaderGui header;
    private static WardrobeGui instance = null;

    private final GuiElement RotatePlayerScrollArea;
    private final GuiButton ResetPlayerRotation;
    private int RotatePlayer = 0;

    protected WardrobeGui() {
        super(Component.translatable("wardrobe:wardrobegui"));

        width = 320;
        height = 240;

        defaultGui = new GuiElement(0, 0, width, height);// RotatePlayer()
        RotatePlayerScrollArea = new GuiElement(430, -000, 120, 300);
        ResetPlayerRotation = new GuiButton(440, 260, "Reset Rotation", () -> ResetRotation());
        defaultGui.addChild(RotatePlayerScrollArea);
        defaultGui.addChild(ResetPlayerRotation);

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

        EntityRender.renderEntityInInventory(entityPosLeft, entityPosTop, 100, RotatePlayer,
                (float) entityPosLeft - mouseX,
                (float) ((entityPosTop - 120) - mouseY) / 6,
                this.minecraft.player);
    }

    private void ResetRotation() {
        RotatePlayer = 0;
    }

    @Override
    public boolean mouseClicked(double x, double y, int button) {

        if (defaultGui.onMouseClick((int) x, (int) y, button)) {
            return true;
        }
        return super.mouseClicked(x, y, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double distance) {
        if (RotatePlayerScrollArea.hasFocus()) {
            if (distance > 0)
                RotatePlayer += 5;
            if (distance < 0)
                RotatePlayer -= 5;

            return true;
        }

        return super.mouseScrolled(mouseX, mouseY, distance);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static WardrobeGui getInstance() {
        if (instance == null) {
            instance = new WardrobeGui();
        }

        return instance;
    }
}