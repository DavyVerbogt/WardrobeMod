package com.colt.wardrobe.client;

import com.colt.wardrobe.InputListener;
import com.colt.wardrobe.WardrobeKeyMapping;
import com.colt.wardrobe.client.render.layers.Data;
import com.colt.wardrobe.client.render.layers.HatLayer;
import com.colt.wardrobe.client.render.layers.LayersOptions;
import com.colt.wardrobe.client.render.layers.MenuHatLayer;
import com.colt.wardrobe.client.render.models.ModelInstences;
import com.colt.wardrobe.client.render.models.hats.*;
import com.colt.wardrobe.managers.HatManager;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
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
        HatManager.instance().getRegisteredHats().forEach(hat -> {
            HatLayer.registerModel(hat.GetId(), () -> (BaseHatModel) hat.GetModelSupplier().get());
        });
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
        event.registerLayerDefinition(TopTopTophatModel.LAYER_LOCATION, () -> TopTopTophatModel.createLayer());
        event.registerLayerDefinition(ArrowModel.LAYER_LOCATION, () -> ArrowModel.createLayer());
        event.registerLayerDefinition(BaseballCapModel.LAYER_LOCATION, () -> BaseballCapModel.createLayer());
    }

    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        addHatLayer(event.getSkin("default"));
        addHatLayer(event.getSkin("slim"));

        EntityRenderer<?> renderer = event.getRenderer(EntityType.ARMOR_STAND);
        if (renderer instanceof ArmorStandRenderer armorStandRenderer) {
            armorStandRenderer.addLayer(new MenuHatLayer(armorStandRenderer));
        }

        ModelInstences.get().LoadModels(event.getEntityModels());
    }

    private static void addHatLayer(LivingEntityRenderer<?, ?> renderer) {
        if (renderer instanceof PlayerRenderer playerRenderer) {
            playerRenderer.addLayer(new HatLayer(playerRenderer));
        }
    }

    @SubscribeEvent
    public static void onRegisterKeymappings(RegisterKeyMappingsEvent registerKeyMappingsEvent) {
        registerKeyMappingsEvent.register(WardrobeKeyMapping.KeyWardrobe);
    }
}
