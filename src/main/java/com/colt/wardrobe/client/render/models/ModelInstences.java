package com.colt.wardrobe.client.render.models;

import com.colt.wardrobe.client.render.models.hats.ArrowModel;
import com.colt.wardrobe.client.render.models.hats.BaseHatModel;
import com.colt.wardrobe.client.render.models.hats.TopHatModel;
import com.colt.wardrobe.client.render.models.hats.TopTopTophatModel;
import net.minecraft.client.model.geom.EntityModelSet;

public class ModelInstences {

    private static ModelInstences instance;

    public static ModelInstences get() {
        if (instance == null) {
            instance = new ModelInstences();
        }
        return instance;
    }

    //Hats
    private BaseHatModel TopHat;
    private BaseHatModel TopTopTopHat;
    private BaseHatModel Arrow;

    private ModelInstences() {
    }

    public void LoadModels(EntityModelSet modelSet) {
        this.TopHat = new TopHatModel(modelSet.bakeLayer(TopHatModel.LAYER_LOCATION));
        this.TopTopTopHat = new TopTopTophatModel(modelSet.bakeLayer(TopTopTophatModel.LAYER_LOCATION));
        this.Arrow = new ArrowModel(modelSet.bakeLayer(ArrowModel.LAYER_LOCATION));
    }


    public BaseHatModel getTopHat() {
        return this.TopHat;
    }

    public BaseHatModel getTopTopTopHat() {
        return this.TopTopTopHat;
    }

    public BaseHatModel getArrow() {
        return this.Arrow;
    }

}
