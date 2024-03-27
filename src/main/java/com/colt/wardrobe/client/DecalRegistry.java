package com.colt.wardrobe.client;

import com.colt.wardrobe.Wardrobe;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class DecalRegistry {

    public static String[] Decals_16x16 = {"cat_black","cat_blackblue","cat_blackwhite","cat_blonde","cat_brown","cat_ginger","cat_gizmo","cat_gray","cat_rags","cat_stripes","cat_white","ocelot",
            "cow","dog","pig","wolf"
            ,"sheep_black","sheep_blue","sheep_brown","sheep_cyan","sheep_gray","sheep_green","sheep_light_blue","sheep_light_gray","sheep_lime","sheep_magenta","sheep_orange","sheep_pink","sheep_purple","sheep_rainbow","sheep_red","sheep_white","sheep_yellow"
            ,"enderman","creeper","spider","zombie","skeleton","skeleton_wither",
            "coal","diamond","emerald","laps_lazuli","redstone","copper_ingot","gold_ingot","iron_ingot","netherrite_ingot"
};

    private static DecalRegistry instance;

    public static DecalRegistry instance() {
        if (instance == null) {
            instance = new DecalRegistry();
        }
        return instance;
    }

    public ResourceLocation GetSingle16x16Decal(String type){
        return new ResourceLocation(Wardrobe.MOD_ID,
                "textures/player/decals/16x16/" + type + ".png");
    }

    public List<String> GetAll16x16Decal() {
        return List.copyOf(Arrays.stream(Decals_16x16).toList());
    }
}