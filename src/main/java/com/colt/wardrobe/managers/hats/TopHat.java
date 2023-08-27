package com.colt.wardrobe.managers.hats;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.managers.Hat;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Supplier;

public class TopHat extends Hat {

    public static final ResourceLocation ID = new ResourceLocation(Wardrobe.MOD_ID, "tophat");

    public TopHat() {
        super(ID, 2, true);
    }

    @Override
    public Supplier<Object> GetModelSupplier() {
        return ModelInstences.get()::getTopHat;
    }
@Override
    public int GetColorForLayer(int Layer){
    return Layer == 1 ?  Color.WHITE.hashCode() :  Color.BLACK.hashCode();
    }
    @Override
    public  ResourceLocation GetTexture(String Layer){
        return new ResourceLocation(Wardrobe.MOD_ID,
                "textures/player/hats/tophat/top_hat" + Layer + ".png");
    }
}
