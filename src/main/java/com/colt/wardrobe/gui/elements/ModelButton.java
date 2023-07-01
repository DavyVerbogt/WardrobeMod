package com.colt.wardrobe.gui.elements;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.colt.wardrobe.gui.Wardrobe.EntityRender;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraftforge.common.SoundAction;
import se.mickelus.mutil.gui.GuiAlignment;
import se.mickelus.mutil.gui.GuiAttachment;
import se.mickelus.mutil.gui.GuiClickable;
import se.mickelus.mutil.gui.GuiElement;
import se.mickelus.mutil.gui.GuiRect;
import se.mickelus.mutil.gui.GuiTexture;

public class ModelButton extends GuiClickable {

    public final GuiTexture Background;
    private boolean Enabled = false;
    private int rotate = 20;

    public ModelButton(int x, int y, int width, int height, Runnable onClickHandler) {
        super(x, y, width, height, onClickHandler);

        Background = (GuiTexture) new GuiTexture(0, 0, width, height, GuiTextures.ModelButtonBackground).setOpacity(1f);

        Background.setTextureCoordinates(0, 0);

        addChild(Background);
    }


    @Override
    public boolean onMouseClick(int x, int y, int button) {
        if (hasFocus)
        {
            Enabled=true;
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            updateColor();
        }
        return Enabled && super.onMouseClick(x, y, button);

    }

    @Override
    public void onMouseRelease(int x, int y, int button) {
        if (hasFocus)
        {
            Enabled=false;
            updateColor();
        }
        super.onMouseRelease(x, y, button);
    }


    private void updateColor() {
        if (!Enabled && !hasFocus) {
            Background.setTextureCoordinates(0, 0);
        } else if (hasFocus() && Enabled) {
            Background.setTextureCoordinates(160, 0);
        } else {
            Background.setTextureCoordinates(80, 0);
        }
    }

    @Override
    protected void onFocus() {
        updateColor();
    }

    @Override
    protected void onBlur() {
        updateColor();
    }

    @Override
    public void draw(PoseStack matrixStack, int refX, int refY, int screenWidth, int screenHeight, int mouseX,
            int mouseY, float opacity) {

        ArmorStand INVStand = new ArmorStand(EntityType.ARMOR_STAND, Minecraft.getInstance().level);

        if (rotate == 360) {
            rotate = 0;
        }
        if (hasFocus) {
            rotate++;
        } else {
            rotate = 20;
        }

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        EntityRender.scissor(refX+x, refY+y-1, width, height);
        EntityRender.renderEntityInInventory((refX+40)+x, (refY)+y*3, 50, rotate,
                0,
                0,
                INVStand);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        super.draw(matrixStack, refX, refY, screenWidth, screenHeight, mouseX, mouseY, opacity);
    }

}
