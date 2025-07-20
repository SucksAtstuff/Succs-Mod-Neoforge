package net.succ.succsmod.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.entity.custom.HedgehogEntity;
import net.succ.succsmod.entity.custom.PukekoEntity;
import net.succ.succsmod.entity.custom.ToxicSlimeEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SuccsMod.MOD_ID);

    public static final Supplier<EntityType<PukekoEntity>> PUKEKO =
            ENTITY_TYPES.register("pukeko", () -> EntityType.Builder.of(PukekoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.95f).build("pukeko"));


    public static final Supplier<EntityType<HedgehogEntity>> HEDGEHOG =
            ENTITY_TYPES.register("hedgehog", () -> EntityType.Builder.of(HedgehogEntity::new, MobCategory.CREATURE)
                    .sized(0.35f, 0.35f).build("hedgehog"));

    public static final Supplier<EntityType<ToxicSlimeEntity>> TOXIC_SLIME =
            ENTITY_TYPES.register("toxic_slime", () -> EntityType.Builder.of(ToxicSlimeEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 0.6f).build("toxic_slime"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
