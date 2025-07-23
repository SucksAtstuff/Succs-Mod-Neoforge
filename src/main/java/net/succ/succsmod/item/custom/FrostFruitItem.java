package net.succ.succsmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.item.ModFoodProperties;

import java.util.List;

public class FrostFruitItem extends Item {

    public static final int FROST_RESISTANCE_DURATION = 20 * 15; // 15 seconds

    public FrostFruitItem() {
        super(new Properties().food(ModFoodProperties.FROST_FRUIT));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        if (!level.isClientSide) {
            user.addEffect(new MobEffectInstance(ModEffects.FROST_RESISTANCE_EFFECT, FROST_RESISTANCE_DURATION));
            level.playSound(null, user.blockPosition(), SoundEvents.POWDER_SNOW_STEP, SoundSource.PLAYERS, 0.8F, 1.5F);
        }

        if (user instanceof Player player) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return result;
    }



    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Grants temporary resistance to freezing."));
    }
}
