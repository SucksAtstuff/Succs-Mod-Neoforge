package net.succ.succsmod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class MobEffectSwordItem extends SwordItem {

    // Stores the MobEffect this sword applies on hit
    private final Holder<MobEffect> effect;

    // Duration (in ticks) the effect lasts
    private final int duration;

    // Amplifier level of the effect (0 = level I, 1 = level II, etc.)
    private final int amplifier;

    // Constructor to initialize sword with specific effect, duration, and amplifier
    public MobEffectSwordItem(Tier tier, Properties properties, Holder<MobEffect> effect, int duration, int amplifier) {
        super(tier, properties);
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    // Called when the sword hits an entity
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        // Only apply the effect to living entities
        if (entity instanceof LivingEntity livingEntity) {
            // Apply the configured effect with duration and amplifier
            livingEntity.addEffect(new MobEffectInstance(effect, duration, amplifier), player);
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    // Appends tooltip information when hovering over the item in inventory
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        // If Shift is held, show detailed information
        if (Screen.hasShiftDown()) {
            // Add the translated tooltip with dynamic effect name, duration (in seconds), and level
            tooltipComponents.add(Component.translatable(
                    "tooltip.succsmod.mob_effect_sword.tooltip",
                    effect.value().getDisplayName(),                    // Effect name (automatically localized)
                    duration / 20,                                      // Duration in seconds
                    amplifier + 1                                       // Amplifier level as player-friendly (I = 1)
            ));
        } else {
            // Add basic tooltip prompting to hold Shift
            tooltipComponents.add(Component.translatable("tooltip.succsmod.mob_effect_sword.tooltip.shift"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
