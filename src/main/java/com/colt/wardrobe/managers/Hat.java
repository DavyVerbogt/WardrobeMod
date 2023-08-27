package com.colt.wardrobe.managers;

import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Supplier;

public abstract class Hat {

    private final ResourceLocation id;
    private final int LayerAmount;
    private final boolean IsColorible;

    public Hat(ResourceLocation id, int AmountOfLayers, boolean IsColorible) {
        this.id = id;
        this.LayerAmount = AmountOfLayers;
        this.IsColorible = IsColorible;
    }

    public ResourceLocation GetId() {
        return this.id;
    }

    public int GetAmmountOfLayers() {
        return this.LayerAmount;
    }

    public boolean GetColorible() {
        return this.IsColorible;
    }

    public abstract Supplier<Object> GetModelSupplier();

    public  int GetColorForLayer(int Layer){return Color.WHITE.hashCode();}

    public  ResourceLocation GetTexture(String Layer){return null;}

}
