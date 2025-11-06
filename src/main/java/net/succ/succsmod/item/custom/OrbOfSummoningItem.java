package net.succ.succsmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.entity.custom.TjEntity;
import net.succ.succsmod.sound.ModSounds;

import java.util.List;

public class OrbOfSummoningItem extends Item {
    public OrbOfSummoningItem(Properties props) {
        super(props);
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("I wonder what happens, if you use it....").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            TjEntity boss = ModEntities.TJ.get().create(serverLevel);
            if (boss != null) {
                boss.moveTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                        level.random.nextFloat() * 360F, 0);
                serverLevel.addFreshEntity(boss);

                //  Play the custom summon sound server-wide
                serverLevel.playSound(
                        null, // null = broadcast to everyone
                        pos,
                        ModSounds.ORB_SUMMON.get(),
                        SoundSource.MASTER,
                        1.0F,
                        1.0F
                );

                if (player != null && !player.isCreative()) {
                    stack.shrink(1);
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // prevents blockless right-click from doing nothing
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
