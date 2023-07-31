package com.colt.wardrobe.gui;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.layers.HatLayer;
import com.colt.wardrobe.gui.Wardrobe.EntityRender;
import com.colt.wardrobe.gui.Wardrobe.HeaderGui;
import com.colt.wardrobe.gui.elements.GuiColorChooser;
import com.colt.wardrobe.gui.elements.GuiSlider;
import com.colt.wardrobe.gui.elements.ModelButton;
import com.colt.wardrobe.managers.HatManager;
import com.mojang.blaze3d.vertex.PoseStack;
import lain.mods.cos.impl.ModObjects;
import lain.mods.cos.impl.client.PlayerRenderHandler;
import lain.mods.cos.impl.inventory.InventoryCosArmor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import se.mickelus.mutil.gui.GuiButton;
import se.mickelus.mutil.gui.GuiElement;

import java.awt.*;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class WardrobeGui extends Screen {

    private final GuiElement defaultGui;
    private final HeaderGui header;
    private static WardrobeGui instance = null;
    //private final GuiElement RotatePlayerScrollArea;
    private final GuiButton ResetPlayerRotation;
    private final GuiButton PageSwitcher;

    private final GuiButton ToggleCAR;
    private final GuiButton ToggleHeadArmor;
    private final GuiButton ToggleChestArmor;
    private final GuiButton ToggleLegArmor;
    private final GuiButton ToggleBootArmor;
    private final GuiSlider RotateSlider;
    private int RotatePlayer = 0;
    private int ModelButtonoffset = 0;

    protected WardrobeGui() {
        super(Component.translatable("wardrobe:wardrobegui"));

        width = 460;
        height = 380;

        defaultGui = new GuiElement(0, 0, width, height);

        //RotatePlayerScrollArea = new GuiElement(430, -000, 120, 300);
        ResetPlayerRotation = new GuiButton(430, 260, "Reset Rotation", () -> ResetRotation());
        //defaultGui.addChild(RotatePlayerScrollArea);
        defaultGui.addChild(ResetPlayerRotation);

        header = new HeaderGui(0, 0, height, width);
        defaultGui.addChild(header);

        PageSwitcher = new GuiButton(0, 0, "Next " + "Page", null);

        defaultGui.addChild(PageSwitcher);

        ToggleCAR = new GuiButton(0, 10, "Toggle Cosmetic Armor", () -> ToggleCosmeticArmor());
        ToggleHeadArmor = new GuiButton(0, 20, "Toggle Helmet", () -> ToggleArmor(3));
        ToggleChestArmor = new GuiButton(0, 30, "Toggle Chestplate", () -> ToggleArmor(2));
        ToggleLegArmor = new GuiButton(0, 40, "Toggle Leggings", () -> ToggleArmor(1));
        ToggleBootArmor = new GuiButton(0, 50, "Toggle Boots", () -> ToggleArmor(0));

        defaultGui.addChild(ToggleCAR);
        defaultGui.addChild(ToggleHeadArmor);
        defaultGui.addChild(ToggleChestArmor);
        defaultGui.addChild(ToggleLegArmor);
        defaultGui.addChild(ToggleBootArmor);


        HatManager.instance().getRegisteredHats().forEach(hat -> {

            defaultGui.addChild(new ModelButton(ModelButtonoffset, 70, 80, 80, hat.getId().toString(), hat.GetAmmountOfLayers(), hat.getColorible(),
                    () -> ToggleCustomArmor(hat.getId().toString(), hat.GetAmmountOfLayers(), hat.getColorible())));
Wardrobe.LOGGER.info("Wardrobe Model Offset: " + ModelButtonoffset);
            ModelButtonoffset+=110;
        });

        defaultGui.addChild(new GuiColorChooser(-300, -50, 255, Color.BLACK.hashCode(),
                val -> HatLayer.Layer0Color = val));

        RotateSlider = new GuiSlider(430, 280, 100, 360, true, val -> RotatePlayer = -180 + val);
        defaultGui.addChild(RotateSlider);
    }


    @Override
    public void render(PoseStack matrixStack, final int mouseX, final int mouseY, final float partialTicks) {
        renderBackground(matrixStack, 0);

        defaultGui.updateFocusState((width - defaultGui.getWidth()) / 2,
                (int) ((height - defaultGui.getHeight()) / 1.5), mouseX,
                mouseY);
        defaultGui.draw(matrixStack, (width - defaultGui.getWidth()) / 2,
                (int) ((height - defaultGui.getHeight()) / 1.5),
                width, height, mouseX, mouseY, 1);

        int entityPosLeft = (width / 4) * 3;
        int entityPosTop = (height / 3) * 2;

        EntityRender.renderEntityInInventory(entityPosLeft, entityPosTop, 110, RotatePlayer,
                (float) ((entityPosLeft) - mouseX) / 2,
                (float) ((entityPosTop - 120) - mouseY) / 6,
                this.minecraft.player);
    }

    private void ResetRotation() {
        RotateSlider.currentIndicator.setX(RotateSlider.getWidth() / 2);
        RotatePlayer = 0;
    }

    private void ToggleCosmeticArmor() {
        PlayerRenderHandler.Disabled = !PlayerRenderHandler.Disabled;
    }

    private void ToggleArmor(int slot) {
        InventoryCosArmor inv = ModObjects.invMan.getCosArmorInventoryClient(this.minecraft.player.getUUID());
        inv.setSkinArmor(slot, !inv.isSkinArmor(slot));
    }

    private void ToggleCustomArmor(String id, int LayerAmount, boolean IsColorible) {
        if (Objects.equals(id, HatLayer.ModelName)) {
            HatLayer.TurnTophatOn = !HatLayer.TurnTophatOn;
        }
        HatLayer.ModelName = id;
        HatLayer.Layers = LayerAmount;
        HatLayer.IsColorible = IsColorible;

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
        /*
         * if (RotatePlayerScrollArea.hasFocus()) {
         * if (distance > 0)
         * RotatePlayer += 5;
         * if (distance < 0)
         * RotatePlayer -= 5;
         *
         * return true;
         * }
         */
        return super.mouseScrolled(mouseX, mouseY, distance);
    }

    @Override
    public boolean mouseReleased(double x, double y, int button) {
        defaultGui.onMouseRelease((int) x, (int) y, button);
        return super.mouseReleased(x, y, button);
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