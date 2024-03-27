package com.colt.wardrobe.managers;

import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.function.Supplier;

public abstract class Hat {

    private final ResourceLocation id;
    private final int LayerAmount;
    private final boolean IsColorible;
    private final boolean SelectibleDecal;
    private final String DecalName;

    public Hat(ResourceLocation id, int AmountOfLayers, boolean IsColorible, boolean SelectibleDecal, String DecalName) {
        this.id = id;
        this.LayerAmount = AmountOfLayers;
        this.IsColorible = IsColorible;
        this.SelectibleDecal = SelectibleDecal;
        this.DecalName = DecalName;
    }

    public ResourceLocation GetId() {
        return this.id;
    }

    public int GetAmmountOfLayers() {
        return this.LayerAmount;
    }

    public boolean GetDecalOption() {return this.SelectibleDecal;}

    public boolean GetColorible() {
        return this.IsColorible;
    }

    public abstract Supplier<Object> GetModelSupplier();

    public  int GetColorForLayer(int Layer){return Color.WHITE.hashCode();}

    public  ResourceLocation GetTexture(String Layer){return null;}

    public String GetDecalName(){return this.DecalName;}
}
