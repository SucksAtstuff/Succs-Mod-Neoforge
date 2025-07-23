package net.succ.succsmod.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.block.custom.GarlicCropBlock;
import net.succ.succsmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F};


    protected ModBlockLootTablesProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

    }


    @Override
    protected void generate() {
        // Define loot tables for blocks that drop themselves
        dropSelf(ModBlocks.ATHERIUM_BLOCK.get());
        dropSelf(ModBlocks.MALACHITE_BLOCK.get());
        dropSelf(ModBlocks.RUBY_BLOCK.get());
        dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        dropSelf(ModBlocks.SUNSTONE_BLOCK.get());
        dropSelf(ModBlocks.JASPILITE_BLOCK.get());
        dropSelf(ModBlocks.GEM_POLISHING_TABLE.get());

        // Define loot tables for tree blocks that drop themselves
        dropSelf(ModBlocks.SHATTERBLOOM_LOG.get());
        dropSelf(ModBlocks.SHATTERBLOOM_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_SHATTERBLOOM_LOG.get());
        dropSelf(ModBlocks.STRIPPED_SHATTERBLOOM_WOOD.get());
        dropSelf(ModBlocks.SHATTERBLOOM_SAPLING.get());
        dropSelf(ModBlocks.SHATTERBLOOM_PLANKS.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_LOG.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_LOG.get());
        dropSelf(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_WOOD.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_SAPLING.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get());
        dropSelf(ModBlocks.CRYOHEART_LOG.get());
        dropSelf(ModBlocks.CRYOHEART_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_CRYOHEART_LOG.get());
        dropSelf(ModBlocks.STRIPPED_CRYOHEART_WOOD.get());
        dropSelf(ModBlocks.CRYOHEART_SAPLING.get());
        dropSelf(ModBlocks.CRYOHEART_PLANKS.get());

        this.add(ModBlocks.SHATTERBLOOM_DOOR.get(),
                block -> createDoorTable(ModBlocks.SHATTERBLOOM_DOOR.get()));
        dropSelf(ModBlocks.SHATTERBLOOM_TRAPDOOR.get());
        dropSelf(ModBlocks.SHATTERBLOOM_STAIRS.get());
        this.add(ModBlocks.SHATTERBLOOM_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SHATTERBLOOM_SLAB.get()));
        dropSelf(ModBlocks.SHATTERBLOOM_BUTTON.get());
        dropSelf(ModBlocks.SHATTERBLOOM_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.SHATTERBLOOM_FENCE.get());
        dropSelf(ModBlocks.SHATTERBLOOM_FENCE_GATE.get());

        this.add(ModBlocks.MYCELIAL_SPOREWOOD_DOOR.get(),
                block -> createDoorTable(ModBlocks.MYCELIAL_SPOREWOOD_DOOR.get()));
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_TRAPDOOR.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_STAIRS.get());
        this.add(ModBlocks.MYCELIAL_SPOREWOOD_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.MYCELIAL_SPOREWOOD_SLAB.get()));
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_BUTTON.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_FENCE.get());
        dropSelf(ModBlocks.MYCELIAL_SPOREWOOD_FENCE_GATE.get());

        this.add(ModBlocks.CRYOHEART_DOOR.get(),
                block -> createDoorTable(ModBlocks.CRYOHEART_DOOR.get()));
        dropSelf(ModBlocks.CRYOHEART_TRAPDOOR.get());
        dropSelf(ModBlocks.CRYOHEART_STAIRS.get());
        this.add(ModBlocks.CRYOHEART_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CRYOHEART_SLAB.get()));
        dropSelf(ModBlocks.CRYOHEART_BUTTON.get());
        dropSelf(ModBlocks.CRYOHEART_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.CRYOHEART_FENCE.get());
        dropSelf(ModBlocks.CRYOHEART_FENCE_GATE.get());

        add(ModBlocks.MYCELIAL_SPOREWOOD_VINE.get(),
                block -> createSilkTouchOnlyTable(ModBlocks.MYCELIAL_SPOREWOOD_VINE.get()));

        // Define loot tables for flowers that drop themselves
        dropSelf(ModBlocks.POISON_LILY.get());
        add(ModBlocks.POTTED_POISON_LILY.get(), createPotFlowerItemTable(ModBlocks.POISON_LILY));
        add(ModBlocks.POTTED_SHATTERBLOOM_SAPLING.get(), createPotFlowerItemTable(ModBlocks.SHATTERBLOOM_SAPLING));
        add(ModBlocks.POTTED_MYCELIAL_SPOREWOOD_SAPLING.get(), createPotFlowerItemTable(ModBlocks.MYCELIAL_SPOREWOOD_SAPLING));
        add(ModBlocks.POTTED_CRYOHEART_SAPLING.get(), createPotFlowerItemTable(ModBlocks.CRYOHEART_SAPLING));

        // Define loot tables for leaves that drop saplings
        add(ModBlocks.SHATTERBLOOM_LEAVES.get(),
                block -> createLeavesDrops(block, ModBlocks.SHATTERBLOOM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.MYCELIAL_SPOREWOOD_LEAVES.get(),
                block -> createLeavesDrops(block, ModBlocks.MYCELIAL_SPOREWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.CRYOHEART_LEAVES.get(),
                block -> createFrostLeavesDrop(block, ModBlocks.CRYOHEART_SAPLING.get(), ModItems.FROST_FRUIT.get(),
                        0.05F, 0.0625F, 0.0833F, 0.1F));

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GARLIC_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GarlicCropBlock.AGE, 3));
        this.add(ModBlocks.GARLIC_CROP.get(), this.createCropDrops(ModBlocks.GARLIC_CROP.get(),
                ModItems.GARLIC.get(), ModItems.GARLIC.get(), lootItemConditionBuilder));


        // Define loot tables for ores that drop items similar to diamond ores
        add(ModBlocks.ATHERIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.ATHERIUM_ORE.get(), ModItems.DIRTY_ATHERIUM.get()));
        add(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_ATHERIUM_ORE.get(), ModItems.DIRTY_ATHERIUM.get()));
        add(ModBlocks.NETHER_ATHERIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHER_ATHERIUM_ORE.get(), ModItems.DIRTY_ATHERIUM.get()));
        add(ModBlocks.END_ATHERIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.END_ATHERIUM_ORE.get(), ModItems.DIRTY_ATHERIUM.get()));
        add(ModBlocks.MALACHITE_ORE.get(),
                block -> createOreDrop(ModBlocks.MALACHITE_ORE.get(), ModItems.DIRTY_MALACHITE.get()));
        add(ModBlocks.DEEPSLATE_MALACHITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_MALACHITE_ORE.get(), ModItems.DIRTY_MALACHITE.get()));
        add(ModBlocks.NETHER_MALACHITE_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHER_MALACHITE_ORE.get(), ModItems.DIRTY_MALACHITE.get()));
        add(ModBlocks.END_MALACHITE_ORE.get(),
                block -> createOreDrop(ModBlocks.END_MALACHITE_ORE.get(), ModItems.DIRTY_MALACHITE.get()));
        add(ModBlocks.RUBY_ORE.get(),
                block -> createOreDrop(ModBlocks.RUBY_ORE.get(), ModItems.DIRTY_RUBY.get()));
        add(ModBlocks.DEEPSLATE_RUBY_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_RUBY_ORE.get(), ModItems.DIRTY_RUBY.get()));
        add(ModBlocks.NETHER_RUBY_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHER_RUBY_ORE.get(), ModItems.DIRTY_RUBY.get()));
        add(ModBlocks.END_RUBY_ORE.get(),
                block -> createOreDrop(ModBlocks.END_RUBY_ORE.get(), ModItems.DIRTY_RUBY.get()));
        add(ModBlocks.SUNSTONE_ORE.get(),
                block -> createOreDrop(ModBlocks.SUNSTONE_ORE.get(), ModItems.DIRTY_SUNSTONE.get()));
        add(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_SUNSTONE_ORE.get(), ModItems.DIRTY_SUNSTONE.get()));
        add(ModBlocks.NETHER_SUNSTONE_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHER_SUNSTONE_ORE.get(), ModItems.DIRTY_SUNSTONE.get()));
        add(ModBlocks.END_SUNSTONE_ORE.get(),
                block -> createOreDrop(ModBlocks.END_SUNSTONE_ORE.get(), ModItems.DIRTY_SUNSTONE.get()));
        add(ModBlocks.SAPPHIRE_ORE.get(),
                block -> createOreDrop(ModBlocks.SAPPHIRE_ORE.get(), ModItems.DIRTY_SAPPHIRE.get()));
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModItems.DIRTY_SAPPHIRE.get()));
        add(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModItems.DIRTY_SAPPHIRE.get()));
        add(ModBlocks.END_SAPPHIRE_ORE.get(),
                block -> createOreDrop(ModBlocks.END_SAPPHIRE_ORE.get(), ModItems.DIRTY_SAPPHIRE.get()));
        add(ModBlocks.JASPILITE_ORE.get(),
                block -> createOreDrop(ModBlocks.JASPILITE_ORE.get(), ModItems.DIRTY_JASPILITE.get()));
        add(ModBlocks.DEEPSLATE_JASPILITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_JASPILITE_ORE.get(), ModItems.DIRTY_JASPILITE.get()));
        add(ModBlocks.NETHER_JASPILITE_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHER_JASPILITE_ORE.get(), ModItems.DIRTY_JASPILITE.get()));
        add(ModBlocks.END_JASPILITE_ORE.get(),
                block -> createOreDrop(ModBlocks.END_JASPILITE_ORE.get(), ModItems.DIRTY_JASPILITE.get()));



    }

    protected LootTable.Builder createFrostLeavesDrop(Block leavesBlock, Block saplingBlock, ItemLike fruitItem, float... saplingChances) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        LootTable.Builder builder = LootTable.lootTable();

        // Sanity check: make sure sapling chances array isn't empty
        boolean hasValidSaplingChances = saplingChances != null && saplingChances.length > 0;

        LootPool.Builder saplingPool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .when(this.hasShearsOrSilkTouch());

        if (hasValidSaplingChances) {
            saplingPool.add(LootItem.lootTableItem(saplingBlock)
                    .when(BonusLevelTableCondition.bonusLevelFlatChance(
                            enchantments.getOrThrow(Enchantments.FORTUNE), saplingChances)));
        } else {
            // Fallback: always drop sapling if something is wrong
            saplingPool.add(LootItem.lootTableItem(saplingBlock));
        }

        builder.withPool(saplingPool);

        // Stick + fruit pool
        LootPool.Builder stickAndFruitPool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .when(this.doesNotHaveShearsOrSilkTouch())
                .add(LootItem.lootTableItem(Items.STICK)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                enchantments.getOrThrow(Enchantments.FORTUNE),
                                NORMAL_LEAVES_STICK_CHANCES)))
                .add(LootItem.lootTableItem(fruitItem)
                        .when(LootItemRandomChanceCondition.randomChance(0.05F))); // 5% chance

        builder.withPool(stickAndFruitPool);

        // Defensive dummy pool in case above somehow becomes invalid
        builder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(0.0F))
                .add(LootItem.lootTableItem(Items.AIR)));

        return builder;
    }






    protected LootTable.Builder createMultipleOreDrops (Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops,maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
        return this.hasShearsOrSilkTouch().invert();
    }

    private LootItemCondition.Builder hasShearsOrSilkTouch() {
        return HAS_SHEARS.or(this.hasSilkTouch());
    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}
