package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.entity.EntityType;
import net.succ.succsmod.entity.ModEntities;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.util.ModTags;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModEntityLootTablesProvider extends LootTableProvider {
    public ModEntityLootTablesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Set.of(),
                List.of(new SubProviderEntry(ModEntityLootTables::new, LootContextParamSets.ENTITY)),
                lookupProvider);
    }

    public static class ModEntityLootTables extends EntityLootSubProvider {
        protected ModEntityLootTables(HolderLookup.Provider lookupProvider) {
            super(FeatureFlags.REGISTRY.allFlags(), lookupProvider);
        }

        @Override
        public void generate() {
            // === TJ ENTITY LOOT TABLE ===
            this.add(ModEntities.TJ.get(),
                    LootTable.lootTable()

                            // Rare Jewelry
                            .withPool(LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1))
                                    .add(TagEntry.expandTag(ModTags.Items.RARE_JEWELRY))
                                    .when(LootItemRandomChanceCondition.randomChance(0.05f)) // 5% total chance
                            )

                            // Jewelry (rare)
                            .withPool(LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1))
                                    .add(TagEntry.expandTag(ModTags.Items.JEWELRY))
                                    .when(LootItemRandomChanceCondition.randomChance(0.25f)) // 25% total chance
                            )

                            .withPool(LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1))
                                    .add(LootItem.lootTableItem(ModItems.REINFORCEMENT_SMITHING_TEMPLATE.get()))
                                    .when(LootItemRandomChanceCondition.randomChance(0.25f)) // 25% total chance
                            )

                            // Filler / common loot
                            .withPool(LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(5)) // 2 random filler items
                                    .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(10))
                                    .add(LootItem.lootTableItem(Items.BONE).setWeight(8))
                                    .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(5))
                                    .add(LootItem.lootTableItem(Items.COAL).setWeight(3))
                                    .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(3))
                                    .add(LootItem.lootTableItem(Items.EMERALD).setWeight(1)) // rare filler
                            )
            );
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return Stream.of(ModEntities.TJ.get());
        }
    }
}
