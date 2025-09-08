package net.succ.succsmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ReinforcedHammerItem extends HammerItem {
    public ReinforcedHammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override public boolean minesVolume(ItemStack stack, Player player) { return true; }

    @Override public int getBaseWidthRange(ItemStack stack, Player player) { return 1; } // 3x3x3
    @Override public int getBaseDepthRange(ItemStack stack, Player player) { return 1; }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Mines a 3x3x3"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}

