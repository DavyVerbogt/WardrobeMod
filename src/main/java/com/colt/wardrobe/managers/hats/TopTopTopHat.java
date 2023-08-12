package com.colt.wardrobe.managers.hats;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.managers.Hat;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class TopTopTopHat extends Hat {

    public static final ResourceLocation ID = new ResourceLocation(Wardrobe.MOD_ID, "toptoptophat");

    public TopTopTopHat() {
        super(ID, 6, true);
    }

    @Override
    public Supplier<Object> getModelSupplier() {
        return ModelInstences.get()::getTopTopTopHat;
    }
}
