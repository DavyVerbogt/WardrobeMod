package com.colt.wardrobe.gui.elements;

import com.colt.wardrobe.Wardrobe;
import net.minecraft.client.Minecraft;
import se.mickelus.mutil.gui.GuiElement;
import se.mickelus.mutil.gui.GuiStringOutline;
import net.minecraft.network.chat.Component;
import se.mickelus.mutil.gui.impl.GuiColors;


import java.util.function.Consumer;
import java.util.Collections;
import java.util.List;

public class GuiSlider extends GuiElement implements Consumer<Integer> {
    private final GuiStringOutline textElement;
    Consumer<Integer> slider;
    private boolean enabled = true;
    private Component disabledTooltip;

    public GuiSlider(int x, int y, int width, int height,String text, Consumer<Integer> Slider) {
        super(x, y, width, height);
        textElement = new GuiStringOutline(0, (height - 8) / 2, text);
        addChild(textElement);
    }

    public GuiSlider(int x, int y, String text, Consumer<Integer> Slider) {
        this(x, y, Minecraft.getInstance().font.width(text), 10, text, Slider);
    }

    public GuiSlider(int x, int y, int width, int height, String text, Consumer<Integer> Slider, Component disabledTooltip) {
        this(x, y, width, height, text, Slider);

        this.disabledTooltip = disabledTooltip;
    }

    public void setSlider(Consumer<Integer> slider) {
        this.slider = slider;
    }

    public Consumer<Integer> getSlider() {
        return slider;
    }

    @Override
    public void accept(Integer integer) {
        Wardrobe.LOGGER.info("Wardrobe Slider Int is: " + integer.toString());
    }

    public boolean onMouseDown(int x, int y, int slider)
    {
return false;
    }

    private void updateColor() {
        if (!enabled) {
            textElement.setColor(GuiColors.muted);
        } else if (hasFocus()) {
            textElement.setColor(GuiColors.hover);
        } else {
            textElement.setColor(GuiColors.normal);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        updateColor();
    }

    public void setText(String text) {
        textElement.setString(text);
        setWidth(Minecraft.getInstance().font.width(text));
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
    public List<Component> getTooltipLines() {
        if (!enabled && disabledTooltip != null && hasFocus()) {
            return Collections.singletonList(disabledTooltip);
        }
        return null;
    }
}
