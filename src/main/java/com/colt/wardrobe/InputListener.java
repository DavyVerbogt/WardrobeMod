package com.colt.wardrobe;

import com.colt.wardrobe.gui.WardrobeGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputListener {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onKeyInput(InputEvent.Key inputEvent) {
        if (Minecraft.getInstance().player != null) {

            if (WardrobeKeyMapping.KeyWardrobe.consumeClick()) {
                WardrobeGui gui = WardrobeGui.getInstance();
                Minecraft.getInstance().setScreen(gui);
                Wardrobe.LOGGER.info(Wardrobe.MOD_ID + " Wardrobe Key has been pressed");
            }
            if (WardrobeKeyMapping.Keyflip.consumeClick()) {
                WardrobeGui gui = WardrobeGui.getInstance();
                //gui.flipChar();
                Wardrobe.LOGGER.info(Wardrobe.MOD_ID + " Wardrobe Key has been pressed");
            }
        }
    }

}
