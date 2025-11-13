package net.succ.succsmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, SuccsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // --------------------------------------------------
        //  POLISHABLE → POLISHED GEM TAGS
        // --------------------------------------------------
        tag(ModTags.Items.POLISHABLE_GEMS)
                .add(ModItems.DIRTY_ATHERIUM.get())
                .add(ModItems.DIRTY_RUBY.get())
                .add(ModItems.DIRTY_SAPPHIRE.get())
                .add(ModItems.DIRTY_SUNSTONE.get())
                .add(ModItems.DIRTY_MALACHITE.get())
                .add(ModItems.DIRTY_JASPILITE.get());

        tag(ModTags.Items.POLISHED_GEMS)
                .add(ModItems.ATHERIUM.get())
                .add(ModItems.RUBY.get())
                .add(ModItems.SAPPHIRE.get())
                .add(ModItems.SUNSTONE.get())
                .add(ModItems.MALACHITE.get())
                .add(ModItems.JASPILITE.get());


        // --------------------------------------------------
        //  VANILLA TOOL TAGS (SWORDS, PICKAXES, AXES, SHOVELS, HOES)
        // --------------------------------------------------

        // SWORDS
        tag(ItemTags.SWORDS)
                .add(ModItems.ATHERIUM_SWORD.get())
                .add(ModItems.RUBY_SWORD.get())
                .add(ModItems.MALACHITE_SWORD.get())
                .add(ModItems.SUNSTONE_SWORD.get())
                .add(ModItems.SAPPHIRE_SWORD.get())
                .add(ModItems.JASPILITE_SWORD.get());

        // PICKAXES
        tag(ItemTags.PICKAXES)
                .add(ModItems.ATHERIUM_PICKAXE.get())
                .add(ModItems.RUBY_PICKAXE.get())
                .add(ModItems.MALACHITE_PICKAXE.get())
                .add(ModItems.SUNSTONE_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get())
                .add(ModItems.JASPILITE_PICKAXE.get());

        // AXES
        tag(ItemTags.AXES)
                .add(ModItems.ATHERIUM_AXE.get())
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.MALACHITE_AXE.get())
                .add(ModItems.SUNSTONE_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get())
                .add(ModItems.JASPILITE_AXE.get());

        // SHOVELS
        tag(ItemTags.SHOVELS)
                .add(ModItems.ATHERIUM_SHOVEL.get())
                .add(ModItems.RUBY_SHOVEL.get())
                .add(ModItems.MALACHITE_SHOVEL.get())
                .add(ModItems.SUNSTONE_SHOVEL.get())
                .add(ModItems.SAPPHIRE_SHOVEL.get())
                .add(ModItems.JASPILITE_SHOVEL.get());

        // HOES
        tag(ItemTags.HOES)
                .add(ModItems.ATHERIUM_HOE.get())
                .add(ModItems.RUBY_HOE.get())
                .add(ModItems.MALACHITE_HOE.get())
                .add(ModItems.SUNSTONE_HOE.get())
                .add(ModItems.SAPPHIRE_HOE.get())
                .add(ModItems.JASPILITE_HOE.get());

        // --------------------------------------------------
        //  VILLAGER PLANTABLE SEEDS
        // --------------------------------------------------
        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.GARLIC.get());

        // --------------------------------------------------
        //  TRIMMABLE ARMOR
        // --------------------------------------------------
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ATHERIUM_HELMET.get())
                .add(ModItems.ATHERIUM_CHESTPLATE.get())
                .add(ModItems.ATHERIUM_LEGGINGS.get())
                .add(ModItems.ATHERIUM_BOOTS.get())

                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.RUBY_BOOTS.get())

                .add(ModItems.MALACHITE_HELMET.get())
                .add(ModItems.MALACHITE_CHESTPLATE.get())
                .add(ModItems.MALACHITE_LEGGINGS.get())
                .add(ModItems.MALACHITE_BOOTS.get())

                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get())
                .add(ModItems.SAPPHIRE_BOOTS.get())

                .add(ModItems.SUNSTONE_HELMET.get())
                .add(ModItems.SUNSTONE_CHESTPLATE.get())
                .add(ModItems.SUNSTONE_LEGGINGS.get())
                .add(ModItems.SUNSTONE_BOOTS.get())

                .add(ModItems.JASPILITE_HELMET.get())
                .add(ModItems.JASPILITE_CHESTPLATE.get())
                .add(ModItems.JASPILITE_LEGGINGS.get())
                .add(ModItems.JASPILITE_BOOTS.get());

        // --------------------------------------------------
        //  ARMOR ENCHANTABLE (ALL PIECES)
        // --------------------------------------------------
        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.ATHERIUM_HELMET.get())
                .add(ModItems.ATHERIUM_CHESTPLATE.get())
                .add(ModItems.ATHERIUM_LEGGINGS.get())
                .add(ModItems.ATHERIUM_BOOTS.get())

                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.RUBY_BOOTS.get())

                .add(ModItems.MALACHITE_HELMET.get())
                .add(ModItems.MALACHITE_CHESTPLATE.get())
                .add(ModItems.MALACHITE_LEGGINGS.get())
                .add(ModItems.MALACHITE_BOOTS.get())

                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get())
                .add(ModItems.SAPPHIRE_BOOTS.get())

                .add(ModItems.SUNSTONE_HELMET.get())
                .add(ModItems.SUNSTONE_CHESTPLATE.get())
                .add(ModItems.SUNSTONE_LEGGINGS.get())
                .add(ModItems.SUNSTONE_BOOTS.get())

                .add(ModItems.JASPILITE_HELMET.get())
                .add(ModItems.JASPILITE_CHESTPLATE.get())
                .add(ModItems.JASPILITE_LEGGINGS.get())
                .add(ModItems.JASPILITE_BOOTS.get());

        // --------------------------------------------------
        //  HEAD ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.ATHERIUM_HELMET.get())
                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.SUNSTONE_HELMET.get())
                .add(ModItems.MALACHITE_HELMET.get())
                .add(ModItems.JASPILITE_HELMET.get());

        // --------------------------------------------------
        //  CHEST ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.ATHERIUM_CHESTPLATE.get())
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.SUNSTONE_CHESTPLATE.get())
                .add(ModItems.MALACHITE_CHESTPLATE.get())
                .add(ModItems.JASPILITE_CHESTPLATE.get());

        // --------------------------------------------------
        //  LEG ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.ATHERIUM_LEGGINGS.get())
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get())
                .add(ModItems.SUNSTONE_LEGGINGS.get())
                .add(ModItems.MALACHITE_LEGGINGS.get())
                .add(ModItems.JASPILITE_LEGGINGS.get());

        // --------------------------------------------------
        //  FOOT ARMOR ONLY
        // --------------------------------------------------
        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.ATHERIUM_BOOTS.get())
                .add(ModItems.RUBY_BOOTS.get())
                .add(ModItems.SAPPHIRE_BOOTS.get())
                .add(ModItems.SUNSTONE_BOOTS.get())
                .add(ModItems.MALACHITE_BOOTS.get())
                .add(ModItems.JASPILITE_BOOTS.get());

        // --------------------------------------------------
        //  DURABILITY ENCHANTABLE (TOOLS + ARMOR)
        // --------------------------------------------------
        tag(ItemTags.DURABILITY_ENCHANTABLE)

                // swords
                .add(ModItems.ATHERIUM_SWORD.get())
                .add(ModItems.RUBY_SWORD.get())
                .add(ModItems.MALACHITE_SWORD.get())
                .add(ModItems.SUNSTONE_SWORD.get())
                .add(ModItems.SAPPHIRE_SWORD.get())
                .add(ModItems.JASPILITE_SWORD.get())

                // axes
                .add(ModItems.ATHERIUM_AXE.get())
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.MALACHITE_AXE.get())
                .add(ModItems.SUNSTONE_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get())
                .add(ModItems.JASPILITE_AXE.get())

                // pickaxes
                .add(ModItems.ATHERIUM_PICKAXE.get())
                .add(ModItems.RUBY_PICKAXE.get())
                .add(ModItems.MALACHITE_PICKAXE.get())
                .add(ModItems.SUNSTONE_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get())
                .add(ModItems.JASPILITE_PICKAXE.get())

                // shovels
                .add(ModItems.ATHERIUM_SHOVEL.get())
                .add(ModItems.RUBY_SHOVEL.get())
                .add(ModItems.MALACHITE_SHOVEL.get())
                .add(ModItems.SUNSTONE_SHOVEL.get())
                .add(ModItems.SAPPHIRE_SHOVEL.get())
                .add(ModItems.JASPILITE_SHOVEL.get())

                // hoes
                .add(ModItems.ATHERIUM_HOE.get())
                .add(ModItems.RUBY_HOE.get())
                .add(ModItems.MALACHITE_HOE.get())
                .add(ModItems.SUNSTONE_HOE.get())
                .add(ModItems.SAPPHIRE_HOE.get())
                .add(ModItems.JASPILITE_HOE.get())

                // hammers
                .add(ModItems.ATHERIUM_HAMMER.get())
                .add(ModItems.RUBY_HAMMER.get())
                .add(ModItems.MALACHITE_HAMMER.get())
                .add(ModItems.SUNSTONE_HAMMER.get())
                .add(ModItems.SAPPHIRE_HAMMER.get())
                .add(ModItems.JASPILITE_HAMMER.get())

                // reinforced hammers
                .add(ModItems.ATHERIUM_REINFORCED_HAMMER.get())
                .add(ModItems.MALACHITE_REINFORCED_HAMMER.get())
                .add(ModItems.RUBY_REINFORCED_HAMMER.get())
                .add(ModItems.SAPPHIRE_REINFORCED_HAMMER.get())
                .add(ModItems.SUNSTONE_REINFORCED_HAMMER.get())
                .add(ModItems.JASPILITE_REINFORCED_HAMMER.get())

                // paxels
                .add(ModItems.ATHERIUM_PAXEL.get())
                .add(ModItems.RUBY_PAXEL.get())
                .add(ModItems.MALACHITE_PAXEL.get())
                .add(ModItems.SUNSTONE_PAXEL.get())
                .add(ModItems.SAPPHIRE_PAXEL.get())
                .add(ModItems.JASPILITE_PAXEL.get())

                // armor
                .add(ModItems.ATHERIUM_HELMET.get())
                .add(ModItems.ATHERIUM_CHESTPLATE.get())
                .add(ModItems.ATHERIUM_LEGGINGS.get())
                .add(ModItems.ATHERIUM_BOOTS.get())

                .add(ModItems.RUBY_HELMET.get())
                .add(ModItems.RUBY_CHESTPLATE.get())
                .add(ModItems.RUBY_LEGGINGS.get())
                .add(ModItems.RUBY_BOOTS.get())

                .add(ModItems.SAPPHIRE_HELMET.get())
                .add(ModItems.SAPPHIRE_CHESTPLATE.get())
                .add(ModItems.SAPPHIRE_LEGGINGS.get())
                .add(ModItems.SAPPHIRE_BOOTS.get())

                .add(ModItems.SUNSTONE_HELMET.get())
                .add(ModItems.SUNSTONE_CHESTPLATE.get())
                .add(ModItems.SUNSTONE_LEGGINGS.get())
                .add(ModItems.SUNSTONE_BOOTS.get())

                .add(ModItems.MALACHITE_HELMET.get())
                .add(ModItems.MALACHITE_CHESTPLATE.get())
                .add(ModItems.MALACHITE_LEGGINGS.get())
                .add(ModItems.MALACHITE_BOOTS.get())

                .add(ModItems.JASPILITE_HELMET.get())
                .add(ModItems.JASPILITE_CHESTPLATE.get())
                .add(ModItems.JASPILITE_LEGGINGS.get())
                .add(ModItems.JASPILITE_BOOTS.get());

        // --------------------------------------------------
        //  WEAPON ENCHANTABLE (SWORDS + AXES)
        // --------------------------------------------------
        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.ATHERIUM_SWORD.get())
                .add(ModItems.RUBY_SWORD.get())
                .add(ModItems.MALACHITE_SWORD.get())
                .add(ModItems.SUNSTONE_SWORD.get())
                .add(ModItems.SAPPHIRE_SWORD.get())
                .add(ModItems.JASPILITE_SWORD.get())

                // axes count as weapons in vanilla
                .add(ModItems.ATHERIUM_AXE.get())
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.MALACHITE_AXE.get())
                .add(ModItems.SUNSTONE_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get())
                .add(ModItems.JASPILITE_AXE.get());

        // --------------------------------------------------
        //  SHARP WEAPON ENCHANTABLE (Swords + Axes)
        // --------------------------------------------------
        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.ATHERIUM_SWORD.get())
                .add(ModItems.RUBY_SWORD.get())
                .add(ModItems.MALACHITE_SWORD.get())
                .add(ModItems.SUNSTONE_SWORD.get())
                .add(ModItems.SAPPHIRE_SWORD.get())
                .add(ModItems.JASPILITE_SWORD.get())

                .add(ModItems.ATHERIUM_AXE.get())
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.MALACHITE_AXE.get())
                .add(ModItems.SUNSTONE_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get())
                .add(ModItems.JASPILITE_AXE.get());

        // --------------------------------------------------
        //  FIRE ASPECT ENCHANTABLE (Swords + Axes)
        // --------------------------------------------------
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.ATHERIUM_SWORD.get())
                .add(ModItems.RUBY_SWORD.get())
                .add(ModItems.MALACHITE_SWORD.get())
                .add(ModItems.SUNSTONE_SWORD.get())
                .add(ModItems.SAPPHIRE_SWORD.get())
                .add(ModItems.JASPILITE_SWORD.get())

                .add(ModItems.ATHERIUM_AXE.get())
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.MALACHITE_AXE.get())
                .add(ModItems.SUNSTONE_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get())
                .add(ModItems.JASPILITE_AXE.get());

        // --------------------------------------------------
        //  MINING ENCHANTABLE (Pickaxe, shovel, hoe, axe, hammer, paxel)
        // --------------------------------------------------
        tag(ItemTags.MINING_ENCHANTABLE)

                // pickaxes
                .add(ModItems.ATHERIUM_PICKAXE.get())
                .add(ModItems.RUBY_PICKAXE.get())
                .add(ModItems.MALACHITE_PICKAXE.get())
                .add(ModItems.SUNSTONE_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get())
                .add(ModItems.JASPILITE_PICKAXE.get())

                // shovels
                .add(ModItems.ATHERIUM_SHOVEL.get())
                .add(ModItems.RUBY_SHOVEL.get())
                .add(ModItems.MALACHITE_SHOVEL.get())
                .add(ModItems.SUNSTONE_SHOVEL.get())
                .add(ModItems.SAPPHIRE_SHOVEL.get())
                .add(ModItems.JASPILITE_SHOVEL.get())

                // hoes
                .add(ModItems.ATHERIUM_HOE.get())
                .add(ModItems.RUBY_HOE.get())
                .add(ModItems.MALACHITE_HOE.get())
                .add(ModItems.SUNSTONE_HOE.get())
                .add(ModItems.SAPPHIRE_HOE.get())
                .add(ModItems.JASPILITE_HOE.get())

                // axes
                .add(ModItems.ATHERIUM_AXE.get())
                .add(ModItems.RUBY_AXE.get())
                .add(ModItems.MALACHITE_AXE.get())
                .add(ModItems.SUNSTONE_AXE.get())
                .add(ModItems.SAPPHIRE_AXE.get())
                .add(ModItems.JASPILITE_AXE.get())

                // hammers
                .add(ModItems.ATHERIUM_HAMMER.get())
                .add(ModItems.RUBY_HAMMER.get())
                .add(ModItems.MALACHITE_HAMMER.get())
                .add(ModItems.SUNSTONE_HAMMER.get())
                .add(ModItems.SAPPHIRE_HAMMER.get())
                .add(ModItems.JASPILITE_HAMMER.get())

                // reinforced hammers
                .add(ModItems.ATHERIUM_REINFORCED_HAMMER.get())
                .add(ModItems.MALACHITE_REINFORCED_HAMMER.get())
                .add(ModItems.RUBY_REINFORCED_HAMMER.get())
                .add(ModItems.SAPPHIRE_REINFORCED_HAMMER.get())
                .add(ModItems.SUNSTONE_REINFORCED_HAMMER.get())
                .add(ModItems.JASPILITE_REINFORCED_HAMMER.get())

                // paxels
                .add(ModItems.ATHERIUM_PAXEL.get())
                .add(ModItems.RUBY_PAXEL.get())
                .add(ModItems.MALACHITE_PAXEL.get())
                .add(ModItems.SUNSTONE_PAXEL.get())
                .add(ModItems.SAPPHIRE_PAXEL.get())
                .add(ModItems.JASPILITE_PAXEL.get());

        // --------------------------------------------------
        //  MINING LOOT ENCHANTABLE (Pickaxe, hammer, paxel)
        // --------------------------------------------------
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)

                // pickaxes
                .add(ModItems.ATHERIUM_PICKAXE.get())
                .add(ModItems.RUBY_PICKAXE.get())
                .add(ModItems.MALACHITE_PICKAXE.get())
                .add(ModItems.SUNSTONE_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get())
                .add(ModItems.JASPILITE_PICKAXE.get())

                // hammers
                .add(ModItems.ATHERIUM_HAMMER.get())
                .add(ModItems.RUBY_HAMMER.get())
                .add(ModItems.MALACHITE_HAMMER.get())
                .add(ModItems.SUNSTONE_HAMMER.get())
                .add(ModItems.SAPPHIRE_HAMMER.get())
                .add(ModItems.JASPILITE_HAMMER.get())

                // reinforced hammers
                .add(ModItems.ATHERIUM_REINFORCED_HAMMER.get())
                .add(ModItems.MALACHITE_REINFORCED_HAMMER.get())
                .add(ModItems.RUBY_REINFORCED_HAMMER.get())
                .add(ModItems.SAPPHIRE_REINFORCED_HAMMER.get())
                .add(ModItems.SUNSTONE_REINFORCED_HAMMER.get())
                .add(ModItems.JASPILITE_REINFORCED_HAMMER.get())

                // paxels
                .add(ModItems.ATHERIUM_PAXEL.get())
                .add(ModItems.RUBY_PAXEL.get())
                .add(ModItems.MALACHITE_PAXEL.get())
                .add(ModItems.SUNSTONE_PAXEL.get())
                .add(ModItems.SAPPHIRE_PAXEL.get())
                .add(ModItems.JASPILITE_PAXEL.get());

        // --------------------------------------------------
        //  LOGS THAT BURN (Vanilla)
        // --------------------------------------------------
        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.CRYOHEART_LOGS)
                .addTag(ModTags.Items.EMBERPINE_LOGS)
                .addTag(ModTags.Items.MYCELIAL_SPOREWOOD_LOGS)
                .addTag(ModTags.Items.SHATTERBLOOM_LOGS);

        // --------------------------------------------------
        //  LOGS (Vanilla)
        // --------------------------------------------------
        tag(ItemTags.LOGS)
                .addTag(ModTags.Items.GLOWCAP_STEMS);

        // --------------------------------------------------
        //  PLANKS (Vanilla)
        // --------------------------------------------------
        tag(ItemTags.PLANKS)
                .add(ModBlocks.SHATTERBLOOM_PLANKS.get().asItem())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_PLANKS.get().asItem())
                .add(ModBlocks.CRYOHEART_PLANKS.get().asItem())
                .add(ModBlocks.EMBERPINE_PLANKS.get().asItem())
                .add(ModBlocks.GLOWCAP_PLANKS.get().asItem());

        // --------------------------------------------------
        //  SHATTERBLOOM LOGSET
        // --------------------------------------------------
        tag(ModTags.Items.SHATTERBLOOM_LOGS)
                .add(ModBlocks.SHATTERBLOOM_LOG.get().asItem())
                .add(ModBlocks.SHATTERBLOOM_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_SHATTERBLOOM_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_SHATTERBLOOM_WOOD.get().asItem());

        // --------------------------------------------------
        //  MYCELIAL SPOREWOOD LOGSET
        // --------------------------------------------------
        tag(ModTags.Items.MYCELIAL_SPOREWOOD_LOGS)
                .add(ModBlocks.MYCELIAL_SPOREWOOD_LOG.get().asItem())
                .add(ModBlocks.MYCELIAL_SPOREWOOD_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_MYCELIAL_SPOREWOOD_WOOD.get().asItem());

        // --------------------------------------------------
        //  CRYOHEART LOGSET
        // --------------------------------------------------
        tag(ModTags.Items.CRYOHEART_LOGS)
                .add(ModBlocks.CRYOHEART_LOG.get().asItem())
                .add(ModBlocks.CRYOHEART_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_CRYOHEART_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_CRYOHEART_WOOD.get().asItem());

        // --------------------------------------------------
        //  EMBERPINE LOGSET
        // --------------------------------------------------
        tag(ModTags.Items.EMBERPINE_LOGS)
                .add(ModBlocks.EMBERPINE_LOG.get().asItem())
                .add(ModBlocks.EMBERPINE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_EMBERPINE_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_EMBERPINE_WOOD.get().asItem());

        // --------------------------------------------------
        //  GLOWCAP STEMSET (Stems / Hyphae)
        // --------------------------------------------------
        tag(ModTags.Items.GLOWCAP_STEMS)
                .add(ModBlocks.GLOWCAP_STEM.get().asItem())
                .add(ModBlocks.STRIPPED_GLOWCAP_STEM.get().asItem())
                .add(ModBlocks.GLOWCAP_HYPHAE.get().asItem())
                .add(ModBlocks.STRIPPED_GLOWCAP_HYPHAE.get().asItem());

        // --------------------------------------------------
        //  JEWELRY
        // --------------------------------------------------
        tag(ModTags.Items.JEWELRY)
                .add(ModItems.RING_OF_ATHERIUM.get())
                .add(ModItems.RING_OF_RUBY.get())
                .add(ModItems.RING_OF_SAPPHIRE.get())
                .add(ModItems.RING_OF_SUNSTONE.get())
                .add(ModItems.NECKLACE_OF_JASPILITE.get())
                .add(ModItems.BRACELET_OF_MALACHITE.get());

        // --------------------------------------------------
        //  SUNSTONE + SAPPHIRE SPECIAL TOOLS
        // --------------------------------------------------
        tag(ModTags.Items.SUNSTONE_SAPPHIRE_TOOLS)
                .add(ModItems.SUNSTONE_PICKAXE.get())
                .add(ModItems.SAPPHIRE_PICKAXE.get())
                .add(ModItems.SUNSTONE_HAMMER.get())
                .add(ModItems.SAPPHIRE_HAMMER.get())
                .add(ModItems.SUNSTONE_PAXEL.get())
                .add(ModItems.SAPPHIRE_PAXEL.get());

        // --------------------------------------------------
        //  SEEDS (Villager)
        // --------------------------------------------------
        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.GARLIC.get());

        // --------------------------------------------------
        //  BUCKET TAG
        // --------------------------------------------------
        tag(ModTags.Items.IS_BUCKET)
                .add(Items.BUCKET)
                .add(Items.WATER_BUCKET);

        // --------------------------------------------------
        //  CUSTOM HAMMER ENCHANTABLE TAG
        // --------------------------------------------------
        tag(ModTags.Items.HAMMER_ENCHANTABLE)
                .add(ModItems.JASPILITE_HAMMER.get())
                .add(ModItems.SUNSTONE_HAMMER.get())
                .add(ModItems.SAPPHIRE_HAMMER.get())
                .add(ModItems.ATHERIUM_HAMMER.get())
                .add(ModItems.RUBY_HAMMER.get())
                .add(ModItems.MALACHITE_HAMMER.get())
                .add(ModItems.ATHERIUM_REINFORCED_HAMMER.get())
                .add(ModItems.MALACHITE_REINFORCED_HAMMER.get());


    }


}
