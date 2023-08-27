package com.colt.wardrobe.managers.hats;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.managers.Hat;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class Arrow extends Hat {

    public static final ResourceLocation ID = new ResourceLocation(Wardrobe.MOD_ID, "arrow");

    public Arrow() {
        super(ID, 1, false);
    }

    @Override
    public Supplier<Object> GetModelSupplier() {
        return ModelInstences.get()::getArrow;
    }

    @Override
    public  ResourceLocation GetTexture(String Layer){
        return new ResourceLocation(Wardrobe.MOD_ID,
                "textures/player/hats/arrow/arrow.png");
    }
}
