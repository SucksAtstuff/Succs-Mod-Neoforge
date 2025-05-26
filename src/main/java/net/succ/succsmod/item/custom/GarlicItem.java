package net.succ.succsmod.item.custom;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.succ.succsmod.item.ModFoodProperties;

public class GarlicItem extends ItemNameBlockItem {

    public GarlicItem(Block cropBlock, Properties properties) {
        super(cropBlock, properties.food(ModFoodProperties.GARLIC));
    }
}
