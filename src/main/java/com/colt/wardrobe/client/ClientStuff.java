package com.colt.wardrobe.client;

import com.colt.wardrobe.InputListener;
import com.colt.wardrobe.WardrobeKeyMapping;
import com.colt.wardrobe.client.render.layers.Data;
import com.colt.wardrobe.client.render.layers.LayersOptions;
import com.colt.wardrobe.client.render.layers.MenuHatLayer;
import com.colt.wardrobe.client.render.layers.TwoLayerColoribleHatLayer;
import com.colt.wardrobe.client.render.models.TopHatModel;

import net.minecraft.client.model.ArmorStandModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import javax.annotation.Nullable;
import java.util.HashMap;

public class ClientStuff {
    private static final HashMap<Player, Data> dataHashMap = new HashMap<>();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent SetupEvent) {
        MinecraftForge.EVENT_BUS.register(new InputListener());

    }

    @Nullable
    public static LayersOptions LayerChoice(AbstractClientPlayer player) {
        return dataHashMap.get(player) != null && dataHashMap.get(player).choice() != null
                ? dataHashMap.get(player).choice()
                : null;
    }

    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TopHatModel.LAYER_LOCATION, () -> TopHatModel.createLayer());
    }

    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        EntityModelSet models = event.getEntityModels();

        for (String skin : event.getSkins()) {
            LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> player = event.getSkin(skin);

            if (player != null) {
                player.addLayer(new TwoLayerColoribleHatLayer<>(player, models));
            }
        }

        LivingEntityRenderer<ArmorStand, ArmorStandModel> armorStand = event.getRenderer(EntityType.ARMOR_STAND);

        armorStand.addLayer(new MenuHatLayer(armorStand, models));
    }

    @SubscribeEvent
    public static void onRegisterKeymappings(RegisterKeyMappingsEvent registerKeyMappingsEvent) {
        registerKeyMappingsEvent.register(WardrobeKeyMapping.KeyWardrobe);
    }
}
