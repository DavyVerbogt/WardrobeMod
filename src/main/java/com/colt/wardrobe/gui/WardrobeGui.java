package com.colt.wardrobe.gui;

import com.colt.wardrobe.gui.Wardrobe.HeaderGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import se.mickelus.mutil.gui.GuiElement;

@OnlyIn(Dist.CLIENT)
public class WardrobeGui extends Screen{

    private final GuiElement defaultGui;
    private final HeaderGui header;
    private static WardrobeGui instance = null;
    
    protected WardrobeGui() {
        super(Component.translatable("wardrobe:wardrobegui"));

        width = 320;
        height = 240;

        defaultGui = new GuiElement(0, 0, width, height);
        
        header = new HeaderGui(0, 0, width, height);
        defaultGui.addChild(header);
    }

    public static WardrobeGui getInstance() {
        if (instance == null) {
            instance = new WardrobeGui();
        }

        return instance;
    }

    @OnlyIn(Dist.CLIENT)
    public static void showGUI()
    {
        WardrobeGui gui = WardrobeGui.getInstance();

        Minecraft.getInstance().setScreen(gui);
    }
}
