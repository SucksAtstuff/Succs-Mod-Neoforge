package net.succ.succsmod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.succ.succsmod.item.custom.HammerItem;

public record Excavation() implements EnchantmentEntityEffect {
    public static final MapCodec<Excavation> CODEC =
            MapCodec.unit(Excavation::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (entity instanceof ServerPlayer player &&
                enchantedItemInUse.itemStack().getItem() instanceof HammerItem) {

            BlockPos pos = BlockPos.containing(vec3);
            HammerItem.performExcavation(player, pos, enchantmentLevel);
        }
    }




    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
