package net.succ.succsmod.util;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.succ.succsmod.SuccsMod;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> POLISHABLE_GEMS = createTag("polishable_gems");
        public static final TagKey<Item> POLISHED_GEMS = createTag("polished_gems");
        public static final TagKey<Item> SUNSTONE_SAPPHIRE_TOOLS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "sunstone_sapphire_tools"));
        public static final TagKey<Item> HAMMER_ENCHANTABLE = createTag("hammer_enchantable");
        public static final TagKey<Item> IS_BUCKET = createTag("is_bucket");
        public static final TagKey<Item> SHATTERBLOOM_LOGS = createTag("shatterbloom_logs");
        public static final TagKey<Item> MYCELIAL_SPOREWOOD_LOGS = createTag("mycelial_sporewood_logs");
        public static final TagKey<Item> CRYOHEART_LOGS = createTag("cryoheart_logs");
        public static final TagKey<Item> EMBERPINE_LOGS = createTag("emberpine_logs");
        public static final TagKey<Item> GLOWCAP_STEMS = createTag("glowcap_stems");
        public static final TagKey<Item> JEWELRY = createTag("jewelry");
        public static final TagKey<Item> RARE_JEWELRY = createTag("rare_jewelry");

        // Global Tags
        public static final TagKey<Item> ORES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores"));
        public static final TagKey<Item> GEMS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gems"));
        public static final TagKey<Item> TOOLS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools"));
        public static final TagKey<Item> MINING_TOOLS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/mining_tool"));
        public static final TagKey<Item> MELEE_WEAPONS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/melee_weapon"));
        public static final TagKey<Item> PICKAXES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/pickaxes"));
        public static final TagKey<Item> SHOVELS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/shovels"));
        public static final TagKey<Item> AXES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/axes"));
        public static final TagKey<Item> SWORDS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "tools/swords"));
        public static final TagKey<Item> ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "armors"));
        public static final TagKey<Item> ENCHANTABLES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "enchantables"));

        private static TagKey<Item> createTag (String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> NEEDS_ATHERIUM_TOOL = createTag("needs_atherium_tool");
        public static final TagKey<Block> INCORRECT_FOR_ATHERIUM_TOOL = createTag("incorrect_for_atherium_tool");
        public static final TagKey<Block> NEEDS_MALACHITE_TOOL = createTag("needs_malachite_tool");
        public static final TagKey<Block> INCORRECT_FOR_MALACHITE_TOOL = createTag("incorrect_for_malachite_tool");
        public static final TagKey<Block> NEEDS_RUBY_TOOL = createTag("needs_ruby_tool");
        public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOL = createTag("incorrect_for_ruby_tool");
        public static final TagKey<Block> NEEDS_SAPPHIRE_TOOL = createTag("needs_sapphire_tool");
        public static final TagKey<Block> INCORRECT_FOR_SAPPHIRE_TOOL = createTag("incorrect_for_sapphire_tool");
        public static final TagKey<Block> NEEDS_SUNSTONE_TOOL = createTag("needs_sunstone_tool");
        public static final TagKey<Block> INCORRECT_FOR_SUNSTONE_TOOL = createTag("incorrect_for_sunstone_tool");
        public static final TagKey<Block> INCORRECT_FOR_JASPILITE_TOOL = createTag("incorrect_for_jaspilite_tool");
        public static final TagKey<Block> NEEDS_JASPILITE_TOOL = createTag("needs_jaspilite_tool");

        // Global Tags
        public static final TagKey<Block> ORES =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores"));


        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");

        // Define the custom tag for blocks mineable with Sunstone or Sapphire pickaxes
        public static final TagKey<Block> MINEABLE_WITH_SUNSTONE_OR_SAPPHIRE = BlockTags.create(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "mineable_with_sunstone_or_sapphire"));


        private static TagKey<Block> createTag (String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
        }


    }

    public static class Structures {
        public static final TagKey<Structure> VENOMOUS_FEN_ROCK_FORMATIONS = TagKey.create(
                Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "venomous_fen_rock_formations")
        );

        public static final TagKey<Structure> SHATTERGROVE_ROCK_FORMATIONS = TagKey.create(
                Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "shattergrove_rock_formations")
        );

        public static final TagKey<Structure> CRYSTALFROST_VALE_ROCK_FORMATIONS = TagKey.create(
                Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "crystalfrost_vale_rock_formations")
        );

        public static final TagKey<Structure> SOLARBLIGHT_EXPANSE_STRUCTURES = TagKey.create(
                Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "solarblight_expanse_structures")
        );
    }
}
