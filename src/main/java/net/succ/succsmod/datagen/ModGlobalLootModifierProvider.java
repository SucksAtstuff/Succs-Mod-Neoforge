package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SuccsMod.MOD_ID);
    }

    // Define an array of all dungeon chest loot tables
    ResourceLocation[] dungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/simple_dungeon"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_corridor"),
            ResourceLocation.withDefaultNamespace("chests/nether_bridge"),
            ResourceLocation.withDefaultNamespace("chests/bastion_treasure"),
            ResourceLocation.withDefaultNamespace( "chests/end_city_treasure"),
            ResourceLocation.withDefaultNamespace( "chests/abandoned_mineshaft")
    };

    // Define an array of all end dungeon chest loot tables
    ResourceLocation[] endDungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/end_city_treasure"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_library")
    };

    // Define an array of all nether dungeon chest loot tables
    ResourceLocation[] netherDungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/nether_bridge"),
            ResourceLocation.withDefaultNamespace("chests/bastion_treasure")
    };

    // Define an array of all overworld dungeon chest loot tables
    ResourceLocation[] overworldDungeonChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace( "chests/simple_dungeon"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_corridor"),
            ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft")
    };

    ResourceLocation[] highTierChestLootTables = new ResourceLocation[] {
            ResourceLocation.withDefaultNamespace("chests/bastion_treasure"),
            ResourceLocation.withDefaultNamespace("chests/ancient_city"),
            ResourceLocation.withDefaultNamespace("chests/end_city_treasure"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_library"),
            ResourceLocation.withDefaultNamespace("chests/stronghold_corridor"),
            ResourceLocation.withDefaultNamespace("chests/nether_bridge")
    };

    // Loop over each end dungeon chest loot table and add a chance for atherium gems to spawn
    private void addAtheriumGemsToEndDungeonChests() {
        for (ResourceLocation lootTable : endDungeonChestLootTables) {
            this.add("atherium_gem_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.ATHERIUM.get()));
        }
    }

    // Loop over each nether dungeon chest loot table and add a chance for ruby gems to spawn
    private void addRubyGemsToNetherDungeonChests() {
        for (ResourceLocation lootTable : netherDungeonChestLootTables) {
            this.add("ruby_gem_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.RUBY.get()));
        }
    }

    // Loop over each overworld dungeon chest loot table and add a chance for sapphire gems to spawn
    private void addSapphireGemsToOverworldDungeonChests() {
        for (ResourceLocation lootTable : overworldDungeonChestLootTables) {
            this.add("sapphire_gem_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.SAPPHIRE.get()));
        }
    }

    // Loop over each overworld dungeon chest loot table and add a chance for sunstone gems to spawn
    private void addSunstoneGemsToOverworldDungeonChests() {
        for (ResourceLocation lootTable : overworldDungeonChestLootTables) {
            this.add("sunstone_gem_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.SUNSTONE.get()));
        }
    }

    // Loop over each overworld dungeon chest loot table and add a chance for malachite gems to spawn
    private void addMalachiteGemsToOverworldDungeonChests() {
        for (ResourceLocation lootTable : overworldDungeonChestLootTables) {
            this.add("malachite_gem_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.MALACHITE.get()));
        }
    }

    private void addReinforcementTemplateToHighTierChests() {
        for (ResourceLocation lootTable : highTierChestLootTables) {
            this.add("reinforcement_template_in_" + lootTable.getPath(),
                    new AddItemModifier(new LootItemCondition[] {
                            LootTableIdCondition.builder(lootTable).build(),
                            LootItemRandomChanceCondition.randomChance(0.25f).build()
                    }, ModItems.REINFORCEMENT_SMITHING_TEMPLATE.get()));
        }
    }

    private void addCurioItemstoDungeonChests() {
        // Loop over each dungeon chest loot table and add all rings and bracelet to each one
        for (ResourceLocation lootTable : dungeonChestLootTables) {
            // Add Ring of Ruby
            add("ring_of_ruby_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.1f).build()
            }, ModItems.RING_OF_RUBY.get()));

            // Add Ring of Sapphire
            add("ring_of_sapphire_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.1f).build()
            }, ModItems.RING_OF_SAPPHIRE.get()));

            // Add Ring of Sunstone
            add("ring_of_sunstone_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.1f).build()
            }, ModItems.RING_OF_SUNSTONE.get()));

            // Add Ring of Atherium
            add("ring_of_atherium_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.1f).build()
            }, ModItems.RING_OF_ATHERIUM.get()));

            // Add Bracelet of Malachite
            add("bracelet_of_malachite_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.1f).build()
            }, ModItems.BRACELET_OF_MALACHITE.get()));

            // Add Necklace of Amethyst
            add("necklace_of_jaspilite_in_" + lootTable.getPath(), new AddItemModifier(new LootItemCondition[]{
                    LootTableIdCondition.builder(lootTable).build(),
                    LootItemRandomChanceCondition.randomChance(0.1f).build()
            }, ModItems.NECKLACE_OF_JASPILITE.get()));
        }
    }

    private void addClearMudDiscToPiglinBarter() {
        this.add("clear_mud_disc_from_piglin_barter",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("gameplay/piglin_bartering")).build(),
                        LootItemRandomChanceCondition.randomChance(0.10f).build() // 2% chance, adjust as you like
                }, ModItems.CLEAR_MUD_DISC.get()));
    }

    @Override
    protected void start() {
        this.add("garlic_to_short_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.GARLIC.get()));

        add("garlic_from_tall_grass", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build() }, ModItems.GARLIC.get()));

        this.add("rock_to_stone",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.STONE).build(),
                        LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.ROCK.get()));



        // Add loot modifiers
        addAtheriumGemsToEndDungeonChests();
        addRubyGemsToNetherDungeonChests();
        addSapphireGemsToOverworldDungeonChests();
        addSunstoneGemsToOverworldDungeonChests();
        addMalachiteGemsToOverworldDungeonChests();
        addCurioItemstoDungeonChests();
        addReinforcementTemplateToHighTierChests();
        addClearMudDiscToPiglinBarter();
    }

}

