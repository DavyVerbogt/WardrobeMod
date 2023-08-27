package com.colt.wardrobe.managers.hats;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.managers.Hat;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Supplier;

public class TopTopTopHat extends Hat {

    public static final ResourceLocation ID = new ResourceLocation(Wardrobe.MOD_ID, "toptoptophat");

    public TopTopTopHat() {
        super(ID, 6, true);
    }

    @Override
    public Supplier<Object> GetModelSupplier() {
        return ModelInstences.get()::getTopTopTopHat;
    }

    @Override
    public int GetColorForLayer(int Layer){
        return switch (Layer){
            case 1 -> Color.WHITE.hashCode();
            case 2 -> Color.GREEN.hashCode();
            case 3 -> Color.WHITE.hashCode();
            case 4 -> Color.RED.hashCode();
            case 5 -> Color.WHITE.hashCode();
            default -> Color.BLUE.hashCode();
        };
    }
@Override
    public  ResourceLocation GetTexture(String Layer){
        return new ResourceLocation(Wardrobe.MOD_ID,
            "textures/player/hats/toptoptophat/top_hat" + Layer + ".png");
}
}
