package net.succ.succsmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ModAutoSmeltHammer extends HammerItem implements AutoSmeltTool {

    public ModAutoSmeltHammer(Tier tier, Properties props) {
        super(tier, props);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity user) {
        // let our smelting logic run first
        boolean didSmelt = tryAutoSmelt(stack, level, state, pos, user);

        if (didSmelt) {

            if (!level.isClientSide()) {
                stack.hurtAndBreak(
                        1,                                              // damage
                        (ServerLevel) level,                            // server world
                        user instanceof ServerPlayer
                                ? (ServerPlayer) user
                                : null,                                  // player or null
                        item -> {}                                      // on-break callback (unused)
                );
            }

            return true;
        }
        return super.mineBlock(stack, level, state, pos, user);
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
        addAutoSmeltTooltip(stack, ctx, tooltip, flag);
        super.appendHoverText(stack, ctx, tooltip, flag);
    }
}

