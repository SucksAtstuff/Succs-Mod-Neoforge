package net.succ.succsmod.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.succ.succsmod.SuccsMod;

public class AttackSpeedEffect extends MobEffect {

    public AttackSpeedEffect(MobEffectCategory category, int color) {
        super(category, color);

        // Define the modifier with a ResourceLocation (correct in NeoForge)
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "attack_speed_modifier");

        this.addAttributeModifier(
                Attributes.ATTACK_SPEED, // <== this was missing!
                id,
                0.5, // 50% increase
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL // or ADD_VALUE for flat increase
        );
    }
}
