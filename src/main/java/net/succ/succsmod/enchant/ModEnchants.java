package net.succ.succsmod.enchant;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.succ.succsmod.SuccsMod;

public final class ModEnchants {
    public static final ResourceKey<Enchantment> WIDENING =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "widening"));

    public static final ResourceKey<Enchantment> DEPTH =
            ResourceKey.create(Registries.ENCHANTMENT,
                    ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "depth"));

    private ModEnchants() {}
}
