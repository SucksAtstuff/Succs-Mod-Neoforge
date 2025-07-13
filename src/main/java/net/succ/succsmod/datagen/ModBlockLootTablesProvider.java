package net.succ.succsmod.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.block.custom.GarlicCropBlock;
import net.succ.succsmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    protected ModBlockLootTablesProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
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

        // Define loot tables for leaves that drop saplings
        add(ModBlocks.SHATTERBLOOM_LEAVES.get(),
                block -> createLeavesDrops(block, ModBlocks.SHATTERBLOOM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


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

    protected LootTable.Builder createMultipleOreDrops (Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops,maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

}
