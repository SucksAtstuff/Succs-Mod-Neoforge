package net.succ.succsmod.item.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ReinforcedHammerItem extends HammerItem {
    public ReinforcedHammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override public boolean minesVolume(ItemStack stack, Player player) { return true; }

    @Override public int getBaseWidthRange(ItemStack stack, Player player) { return 1; } // 3x3x3
    @Override public int getBaseDepthRange(ItemStack stack, Player player) { return 0; }
}

