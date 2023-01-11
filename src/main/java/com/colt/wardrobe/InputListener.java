package com.colt.wardrobe;

import com.colt.wardrobe.gui.WardrobeGui;
import com.google.common.graph.Network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkHooks;

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
