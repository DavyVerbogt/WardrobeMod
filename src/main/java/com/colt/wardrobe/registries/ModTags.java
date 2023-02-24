package com.colt.wardrobe.registries;

import com.colt.wardrobe.Wardrobe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    private static TagKey<Item> ItemTag(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Wardrobe.MOD_ID, name));
    }

    private static TagKey<Block> BlockTag(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Wardrobe.MOD_ID, name));
    }

    public static class Items {
        public static final TagKey<Item> HAT = ItemTag("hat");
        public static final TagKey<Item> HAIR = ItemTag("hair");
        public static final TagKey<Item> COAT = ItemTag("coat");
        public static final TagKey<Item> SHIRT = ItemTag("shirt");
        public static final TagKey<Item> PANTS = ItemTag("pants");
        public static final TagKey<Item> SHOES = ItemTag("shoes");
        public static final TagKey<Item> ACCESSORIES = ItemTag("accessories");

        public static class Blocks {

        }

    }
}
