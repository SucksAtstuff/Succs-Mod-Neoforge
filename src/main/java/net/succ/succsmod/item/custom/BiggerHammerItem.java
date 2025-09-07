package net.succ.succsmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class BiggerHammerItem extends HammerItem {
    public BiggerHammerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public int getAoeRange(ItemStack stack, Player player) {
        return 2; // 5x5
    }
}