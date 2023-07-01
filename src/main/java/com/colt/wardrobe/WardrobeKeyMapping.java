package com.colt.wardrobe;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;


public class WardrobeKeyMapping{

    public static final KeyMapping KeyWardrobe = new KeyMapping(
            "key.coltwardrobe.open_wardrobe",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_HOME,
            "Wardrobe");

    private WardrobeKeyMapping() {
    }
}
