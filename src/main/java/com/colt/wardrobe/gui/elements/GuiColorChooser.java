package com.colt.wardrobe.gui.elements;

import com.colt.wardrobe.Wardrobe;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import se.mickelus.mutil.gui.GuiElement;
import se.mickelus.mutil.gui.GuiRect;
import se.mickelus.mutil.gui.GuiTexture;

import java.awt.*;
import java.util.function.Consumer;

public class GuiColorChooser extends GuiElement {
    public static final ResourceLocation ColorPickBaground = new ResourceLocation(Wardrobe.MOD_ID,
            "textures/gui/picker.png");
    public static final ResourceLocation LumSliderBackground = new ResourceLocation(Wardrobe.MOD_ID,
            "textures/gui/slider.png");
    private static final int H = 0, S = 1, B = 2;
    private final float[] hsb;
    private int rgb;
    private final int valueSteps;
    private final int indicatorMax;
    private final Consumer<Integer> onChange;
    public final GuiElement Picker;
    public GuiElement LumSlider;
    public final GuiElement currentIndicatorPicker;
    public final GuiTexture PickerBackground;
    public final GuiTexture SliderBackground;
    public final GuiElement currentIndicatorLum;
    public final GuiRect CurrentColor;
    private final GuiElement hoverIndicatorPicker;
    private final GuiElement hoverIndicatorLum;
    private boolean isDraggingPicker = false;
    private boolean isDraggingLum = false;
    private int valueY = 0;
    private int valueX = 0;
    private int luminance = 0;

    public GuiColorChooser(int x, int y, int WithAndHieght, int initialColor, Consumer<Integer> onChange) {
        super(x, y, WithAndHieght, WithAndHieght);

        Color color = new Color(initialColor);
        this.hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        PickerBackground = new GuiTexture(0, 1, WithAndHieght, WithAndHieght, ColorPickBaground);
        PickerBackground.setColor(Color.BLACK.hashCode());
        Picker = new GuiRect(0, 1, WithAndHieght, WithAndHieght, 0xffffff).setOpacity(0.0f);

        SliderBackground = new GuiTexture(WithAndHieght + 10, 0, 8, WithAndHieght, LumSliderBackground);
        LumSlider = new GuiRect(WithAndHieght + 10, 0, 8, WithAndHieght, rgb).setOpacity(0.0f);

        CurrentColor = (GuiRect) new GuiRect(Picker.getWidth() + 20, Picker.getHeight() - 20, 20, 20, initialColor)
                .setOpacity(1f);

        addChild(PickerBackground);
        addChild(Picker);

        addChild(SliderBackground);
        addChild(LumSlider);
        addChild(CurrentColor);

        hoverIndicatorPicker = new GuiRect(0, 1, 4, 4, 0xffff33).setOpacity(0.3f);
        hoverIndicatorPicker.setVisible(false);

        hoverIndicatorLum = new GuiRect(WithAndHieght + 10, 1, 8, 4, 0xffff33).setOpacity(0.3f);
        hoverIndicatorLum.setVisible(false);

        addChild(hoverIndicatorPicker);
        addChild(hoverIndicatorLum);

        currentIndicatorPicker = new GuiRect(0, 1, 4, 4, 0xffffff);
        currentIndicatorLum = new GuiRect(WithAndHieght + 10, 1, 8, 4, 0xffffff);

        addChild(currentIndicatorPicker);
        addChild(currentIndicatorLum);

        this.valueSteps = 255;
        this.indicatorMax = WithAndHieght - 4;

        this.updateColor();
        this.onChange = onChange;
    }

    protected void updateColor() {
        this.rgb = (0xFFFFFF & Color.HSBtoRGB(this.hsb[H], this.hsb[S], this.hsb[B]));
        Wardrobe.LOGGER.info("Wardrobe color picker color: " + rgb);
        // this.txtRed.setText(String.valueOf((this.rgb >> 16) & 0xFF));
        // this.txtGreen.setText(String.valueOf((this.rgb >> 8) & 0xFF));
        // this.txtBlue.setText(String.valueOf(this.rgb & 0xFF));
    }

    public void setValue(int valueX, int valueY, int luminance) {
        this.valueY = valueY;
        this.valueX = valueX;
        this.luminance = luminance;

        currentIndicatorPicker.setX(valueX * indicatorMax / (valueSteps));
        currentIndicatorPicker.setY(valueY * indicatorMax / (valueSteps));
    }

    @Override
    public boolean onMouseClick(int x, int y, int button) {
        if (Picker.hasFocus()) {
            isDraggingPicker = true;
            return true;
        }
        if (LumSlider.hasFocus()) {
            isDraggingLum = true;
            return true;
        }
        return false;
    }

    @Override
    public void onMouseRelease(int x, int y, int button) {
        isDraggingPicker = false;
        isDraggingLum = false;
    }

    protected int calculateSegmentYLuminosity(int refY, int mouseY) {
        return Math.round((valueSteps - 1) * Math.min(Math.max((mouseY - refY - y - 1) / (1f * indicatorMax), 0), 1));
    }

    protected int calculateSegmentY(int refY, int mouseY) {
        return Math.round((valueSteps - 1) * Math.min(Math.max((mouseY - refY - y - 1) / (1f * indicatorMax), 0), 1));
    }

    protected int calculateSegmentX(int refX, int mouseX) {
        return Math.round((valueSteps - 1) * Math.min(Math.max((mouseX - refX - x - 1) / (1f * indicatorMax), 0), 1));
    }

    @Override
    protected void onFocus() {
        hoverIndicatorPicker.setVisible(true);
    }

    @Override
    protected void onBlur() {
        hoverIndicatorPicker.setVisible(false);
    }

    @Override
    public void draw(PoseStack matrixStack, int refX, int refY, int screenWidth, int screenHeight, int mouseX,
                     int mouseY, float opacity) {
        int BackgroundColorOnly = Color.HSBtoRGB(hsb[H], hsb[S], 1.0f);
        int BackgroundBrightness = Color.HSBtoRGB(0.0f, 0.0f, hsb[B]);

        CurrentColor.setColor(rgb);
        SliderBackground.setColor(BackgroundColorOnly);

        if (isDraggingPicker) {
            int newSegmentY = calculateSegmentY(refY, mouseY);
            int newSegmentX = calculateSegmentX(refX, mouseX);

            if (newSegmentY != valueY) {
                if (newSegmentX != valueX) {

                    valueY = newSegmentY;
                    valueX = newSegmentX;

                    currentIndicatorPicker.setY(valueY * indicatorMax / (valueSteps - 1));
                    currentIndicatorPicker.setX(valueX * indicatorMax / (valueSteps - 1));

                    hoverIndicatorPicker.setY(valueY * indicatorMax / (valueSteps - 1));
                    hoverIndicatorPicker.setX(valueX * indicatorMax / (valueSteps - 1));

                    this.hsb[H] = Math.min(Math.max((mouseY - refY - y - 1) / (1f * indicatorMax), 0), 1);
                    this.hsb[S] = Math.min(Math.max((mouseX - refX - x - 1) / (1f * indicatorMax), 0), 1);
                    this.updateColor();
                    onChange.accept(rgb);
                }

            }
        } else if (hoverIndicatorPicker.isVisible()) {
            hoverIndicatorPicker.setY(calculateSegmentY(refY, mouseY) * indicatorMax / (valueSteps - 1));
            hoverIndicatorPicker.setX(calculateSegmentX(refX, mouseX) * indicatorMax / (valueSteps - 1));
        }

        if (isDraggingLum) {
            int newSegmentLuminance = calculateSegmentYLuminosity(refY, mouseY);
            if (newSegmentLuminance != luminance) {

                luminance = newSegmentLuminance;
                hoverIndicatorLum.setY(luminance * indicatorMax / (valueSteps - 1));
                currentIndicatorLum.setY(luminance * indicatorMax / (valueSteps - 1));
                this.hsb[B] = Math.min(Math.max((mouseY - refY - y - 1) / (1f * indicatorMax), 0), 1);

                PickerBackground.setColor(BackgroundBrightness);
                this.updateColor();
                onChange.accept(rgb);
            }
        } else if (hoverIndicatorLum.isVisible()) {
            hoverIndicatorLum.setY(calculateSegmentYLuminosity(refY, mouseY) * indicatorMax / (valueSteps - 1));
        }

        super.draw(matrixStack, refX, refY, screenWidth, screenHeight, mouseX, mouseY, opacity);
    }
}
