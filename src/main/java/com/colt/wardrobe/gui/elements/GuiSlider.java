package com.colt.wardrobe.gui.elements;

import com.mojang.blaze3d.vertex.PoseStack;
import se.mickelus.mutil.gui.*;

import java.util.function.Consumer;

public class GuiSlider extends GuiElement {

    private final int valueSteps;
    private int indicatorMax;
    private final Consumer<Integer> onChange;
    public final GuiElement currentIndicator;
    private final GuiElement hoverIndicator;
    private boolean isDragging = false;
    private int value = 0;
    private boolean CanGoNegative = false;

    public GuiSlider(int x, int y, int width, int valueSteps, Consumer<Integer> onChange) {
        super(x, y, width, 6);

        addChild(new GuiRect(0, 1, width, 4, 0xffffff).setOpacity(0.3f));

        hoverIndicator = new GuiRect(0, 1, 4, 4, 0xffff33).setOpacity(0.3f);
        hoverIndicator.setVisible(false);
        addChild(hoverIndicator);

        currentIndicator = new GuiRect(0, 1, 4, 4, 0xffffff);
        addChild(currentIndicator);

        this.valueSteps = valueSteps;
        this.indicatorMax = width - 4;

        this.onChange = onChange;
    }

    public GuiSlider(int x, int y, int width, int valueSteps, boolean CanGoNegative, Consumer<Integer> onChange) {
        super(x, y, width, 6);

        addChild(new GuiRect(0, 1, width, 4, 0xffffff).setOpacity(0.3f));

        hoverIndicator = new GuiRect(0, 1, 4, 4, 0xffff33).setOpacity(0.3f);
        hoverIndicator.setVisible(false);
        addChild(hoverIndicator);

        currentIndicator = new GuiRect(0, 1, 4, 4, 0xffffff);
        addChild(currentIndicator);

        currentIndicator.setX(width / 2);

        this.valueSteps = valueSteps;
        this.indicatorMax = width - 4;
        this.CanGoNegative = CanGoNegative;

        this.onChange = onChange;
    }

    public void setValue(int value) {
        this.value = value;
        currentIndicator.setX(value * indicatorMax / (valueSteps - 1));
    }

    @Override
    public boolean onMouseClick(int x, int y, int button) {
        if (this.hasFocus()) {
            isDragging = true;
            return true;
        }
        return false;
    }

    @Override
    public void onMouseRelease(int x, int y, int button) {
        isDragging = false;
    }

    protected int calculateSegment(int refX, int mouseX) {
        if (CanGoNegative) {
            return Math
                    .round((valueSteps - 1) * Math.min(Math.max((mouseX - refX - x - 1) / (1f * indicatorMax), 0), 1));
        }
        return Math.round((valueSteps - 1) * Math.min(Math.max((mouseX - refX - x - 1) / (1f * indicatorMax), 0), 1));
    }

    @Override
    protected void onFocus() {
        hoverIndicator.setVisible(true);
    }

    @Override
    protected void onBlur() {
        hoverIndicator.setVisible(false);
    }

    @Override
    public void draw(PoseStack matrixStack, int refX, int refY, int screenWidth, int screenHeight, int mouseX,
            int mouseY, float opacity) {
        if (isDragging) {
            int newSegment = calculateSegment(refX, mouseX);
            if (newSegment != value) {
                value = newSegment;
                onChange.accept(value);
                currentIndicator.setX(value * indicatorMax / (valueSteps - 1));
                hoverIndicator.setX(value * indicatorMax / (valueSteps - 1));
            }
        } else if (hoverIndicator.isVisible()) {
            hoverIndicator.setX(calculateSegment(refX, mouseX) * indicatorMax / (valueSteps - 1));
        }

        super.draw(matrixStack, refX, refY, screenWidth, screenHeight, mouseX, mouseY, opacity);
    }
}
