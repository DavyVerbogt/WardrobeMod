package com.colt.wardrobe.managers;

import com.colt.wardrobe.managers.hats.Arrow;
import com.colt.wardrobe.managers.hats.BaseballCap;
import com.colt.wardrobe.managers.hats.TopHat;
import com.colt.wardrobe.managers.hats.TopTopTopHat;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HatManager {
    private static HatManager instance;

    public static HatManager instance() {
        if (instance == null) {
            instance = new HatManager();
        }
        return instance;
    }

    private final Map<ResourceLocation, Hat> registerdHats = new HashMap<>();


    private HatManager() {
        this.register(new TopHat());
        this.register(new TopTopTopHat());
        this.register(new Arrow());
        this.register(new BaseballCap());
    }

    public synchronized void register(Hat hat) {
        this.registerdHats.putIfAbsent(hat.GetId(), hat);
    }

    public List<Hat> getRegisteredHats() {
        return List.copyOf(this.registerdHats.values());
    }

    public Hat GetHat(ResourceLocation id) {
        return this.registerdHats.get(id);
    }

}
