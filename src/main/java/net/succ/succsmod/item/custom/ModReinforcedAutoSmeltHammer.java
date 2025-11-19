package net.succ.succsmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ModReinforcedAutoSmeltHammer extends HammerItem implements AutoSmeltTool {

    public ModReinforcedAutoSmeltHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    // Reinforced = mines full 3×3×3 (or larger if enchanted)
    @Override
    public boolean minesVolume(ItemStack stack, Player player) {
        return true;
    }

    @Override
    public int getBaseWidthRange(ItemStack stack, Player player) {
        return 1;  // 3×3 width
    }

    @Override
    public int getBaseDepthRange(ItemStack stack, Player player) {
        return 1;  // 3 deep
    }

    // --- AUTO-SMELT INJECTION ---------------------------------------------------------------
    @Override
    public boolean mineBlock(ItemStack stack,
                             Level level,
                             BlockState state,
                             BlockPos pos,
                             LivingEntity user) {

        // Try auto-smelting (from AutoSmeltTool interface)
        if (tryAutoSmelt(stack, level, state, pos, user)) {
            return true; // We handled the block and removed it
        }

        // Otherwise behave like normal reinforced hammer
        return super.mineBlock(stack, level, state, pos, user);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
        addAutoSmeltTooltip(stack, ctx, tooltip, flag);
        super.appendHoverText(stack, ctx, tooltip, flag);
    }
}
