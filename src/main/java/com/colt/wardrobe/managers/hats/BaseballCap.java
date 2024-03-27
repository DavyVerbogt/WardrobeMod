package com.colt.wardrobe.managers.hats;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.managers.Hat;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Supplier;

public class BaseballCap extends Hat {

    public static final ResourceLocation ID = new ResourceLocation(Wardrobe.MOD_ID, "baseballcap");

    public BaseballCap() {
        super(ID, 2, true,true, "iron_ingot");
    }

    @Override
    public Supplier<Object> GetModelSupplier() {
        return ModelInstences.get()::getBaseballCap;
    }
    @Override
    public int GetColorForLayer(int Layer){
        return Layer == 1 ?  Color.red.hashCode() :  Color.WHITE.hashCode();
    }
    @Override
    public  ResourceLocation GetTexture(String Layer){
        return new ResourceLocation(Wardrobe.MOD_ID,
                "textures/player/hats/baseballcap/baseball_cap.png");
    }
}
