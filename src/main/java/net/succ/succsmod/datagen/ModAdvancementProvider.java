package net.succ.succsmod.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.item.ModItems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    private static class ModAdvancementGenerator implements AdvancementGenerator {

        private final ResourceLocation ROOT_ID = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succs_essentials/root");
        private final Map<String, ResourceLocation> parentMap = new HashMap<>();

        private void createRootAdvancement(Consumer<AdvancementHolder> consumer) {
            Advancement.Builder.advancement()
                    .display(
                            ModItems.ATHERIUM.get(),
                            Component.literal("Succ's Essentials"),
                            Component.literal("Discover your first Succ's Essentials gem."),
                            ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/stone.png"),
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("has_any_gem", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ModItems.RUBY.get(),
                            ModItems.SAPPHIRE.get(),
                            ModItems.MALACHITE.get(),
                            ModItems.SUNSTONE.get(),
                            ModItems.ATHERIUM.get(),
                            ModItems.JASPILITE.get()
                    ))
                    .save(holder -> {
                        consumer.accept(holder);
                        parentMap.put("root", holder.id());
                    }, String.valueOf(ROOT_ID));
        }

        private void createToolAdvancements(Consumer<AdvancementHolder> consumer, String parentKey, Item... tools) {
            for (Item tool : tools) {
                String id = tool.builtInRegistryHolder().key().location().getPath();

                Advancement.Builder.advancement()
                        .parent(parentMap.get(parentKey))
                        .display(
                                tool,
                                Component.translatable(tool.getDescriptionId()), // ✅ Use translation key
                                Component.literal("Craft a " + Component.translatable(tool.getDescriptionId()).getString() + "."), // Or localize whole line if desired
                                null,
                                AdvancementType.TASK,
                                true, true, false
                        )
                        .addCriterion("has_" + id, InventoryChangeTrigger.TriggerInstance.hasItems(tool))
                        .save(holder -> {
                            consumer.accept(holder);
                            parentMap.put(id, holder.id());
                        }, String.valueOf(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "gems/" + id)));
            }
        }


        private void createGemAdvancement(Consumer<AdvancementHolder> consumer, Item item, String name, String title, String description, String parentKey) {
            Advancement.Builder.advancement()
                    .parent(parentMap.get(parentKey))
                    .display(item, Component.literal(title), Component.literal(description), null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(item))
                    .save(holder -> {
                        consumer.accept(holder);
                        parentMap.put(name, holder.id());
                    }, String.valueOf(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succs_essentials/" + name)));
        }

        private void createFullArmorSetAdvancement(Consumer<AdvancementHolder> consumer, String name, String title, String description, String parentKey, AdvancementType type, Item... armorPieces) {
            Advancement.Builder.advancement()
                    .parent(parentMap.get(parentKey))
                    .display(
                            armorPieces[0],
                            Component.literal(title),
                            Component.literal(description),
                            null,
                            type,
                            true, true, false
                    )
                    .addCriterion("has_full_" + name + "_set", InventoryChangeTrigger.TriggerInstance.hasItems(armorPieces))
                    .save(holder -> {
                        consumer.accept(holder);
                        parentMap.put(name + "_armor_set", holder.id());
                    }, String.valueOf(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "succs_essentials/" + name + "_armor_set")));
        }

        private void createMiscItemAdvancement(Consumer<AdvancementHolder> consumer, Item item, String name, String title, String description, String parentKey) {
            Advancement.Builder.advancement()
                    .parent(parentMap.get(parentKey))
                    .display(item, Component.literal(title), Component.literal(description), null, AdvancementType.TASK, true, true, false)
                    .addCriterion("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(item))
                    .save(holder -> {
                        consumer.accept(holder);
                        parentMap.put(name, holder.id());
                    }, String.valueOf(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "misc/" + name)));
        }

        private void createHiddenAdvancement(Consumer<AdvancementHolder> consumer, Item item, String name, String title, String description, String parentKey) {
            Advancement.Builder.advancement()
                    .parent(parentMap.get(parentKey))
                    .display(item, Component.literal(title), Component.literal(description), null, AdvancementType.TASK,
                            true, true, true) // showToast=false, announce=false, hidden=true
                    .addCriterion("has_" + name, InventoryChangeTrigger.TriggerInstance.hasItems(item))
                    .save(holder -> {
                        consumer.accept(holder);
                        parentMap.put(name, holder.id());
                    }, String.valueOf(ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "hidden/" + name)));
        }


        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            createRootAdvancement(saver);

            createGemAdvancement(saver, ModItems.JASPILITE.get(), "jaspilite", "Earthy Jaspilite", "Obtain a polished Jaspilite.", "root");
            createToolAdvancements(saver, "jaspilite",  ModItems.JASPILITE_SWORD.get(), ModItems.JASPILITE_PICKAXE.get(), ModItems.JASPILITE_AXE.get(), ModItems.JASPILITE_SHOVEL.get(), ModItems.JASPILITE_HOE.get());
            createFullArmorSetAdvancement(saver, "jaspilite", "Jaspilite Armor Set", "Craft a full set of Jaspilite armor.", "jaspilite", AdvancementType.TASK,
                    ModItems.JASPILITE_HELMET.get(), ModItems.JASPILITE_CHESTPLATE.get(), ModItems.JASPILITE_LEGGINGS.get(), ModItems.JASPILITE_BOOTS.get());


            createGemAdvancement(saver, ModItems.SAPPHIRE.get(), "sapphire", "Blue Sapphire", "Obtain a polished Sapphire.", "root");
            createToolAdvancements(saver, "sapphire",  ModItems.SAPPHIRE_SWORD.get(), ModItems.SAPPHIRE_PICKAXE.get(), ModItems.SAPPHIRE_AXE.get(), ModItems.SAPPHIRE_SHOVEL.get(), ModItems.SAPPHIRE_HOE.get());
            createFullArmorSetAdvancement(saver, "sapphire", "Sapphire Armor Set", "Craft a full set of Sapphire armor.", "sapphire", AdvancementType.TASK, ModItems.SAPPHIRE_HELMET.get(), ModItems.SAPPHIRE_CHESTPLATE.get(), ModItems.SAPPHIRE_LEGGINGS.get(), ModItems.SAPPHIRE_BOOTS.get());

            createGemAdvancement(saver, ModItems.SUNSTONE.get(), "sunstone", "Bright Sunstone", "Obtain a polished Sunstone.", "sapphire");
            createToolAdvancements(saver, "sunstone",  ModItems.SUNSTONE_SWORD.get(), ModItems.SUNSTONE_PICKAXE.get(), ModItems.SUNSTONE_AXE.get(), ModItems.SUNSTONE_SHOVEL.get(), ModItems.SUNSTONE_HOE.get());
            createFullArmorSetAdvancement(saver, "sunstone", "Sunstone Armor Set", "Craft a full set of Sunstone armor.", "sunstone", AdvancementType.TASK, ModItems.SUNSTONE_HELMET.get(), ModItems.SUNSTONE_CHESTPLATE.get(), ModItems.SUNSTONE_LEGGINGS.get(), ModItems.SUNSTONE_BOOTS.get());

            createGemAdvancement(saver, ModItems.RUBY.get(), "ruby", "Red Ruby", "Obtain a polished Ruby.", "sunstone");
            createToolAdvancements(saver, "ruby",  ModItems.RUBY_SWORD.get(), ModItems.RUBY_PICKAXE.get(), ModItems.RUBY_AXE.get(), ModItems.RUBY_SHOVEL.get(), ModItems.RUBY_HOE.get());
            createFullArmorSetAdvancement(saver, "ruby", "Ruby Armor Set", "Craft a full set of Ruby armor.", "ruby", AdvancementType.TASK, ModItems.RUBY_HELMET.get(), ModItems.RUBY_CHESTPLATE.get(), ModItems.RUBY_LEGGINGS.get(), ModItems.RUBY_BOOTS.get());

            createGemAdvancement(saver, ModItems.MALACHITE.get(), "malachite", "Green Malachite", "Obtain a polished Malachite.", "ruby");
            createToolAdvancements(saver, "malachite",  ModItems.MALACHITE_SWORD.get(), ModItems.MALACHITE_PICKAXE.get(), ModItems.MALACHITE_AXE.get(), ModItems.MALACHITE_SHOVEL.get(), ModItems.MALACHITE_HOE.get());
            createFullArmorSetAdvancement(saver, "malachite", "Malachite Armor Set", "Craft a full set of Malachite armor.", "malachite", AdvancementType.TASK, ModItems.MALACHITE_HELMET.get(), ModItems.MALACHITE_CHESTPLATE.get(), ModItems.MALACHITE_LEGGINGS.get(), ModItems.MALACHITE_BOOTS.get());

            createGemAdvancement(saver, ModItems.ATHERIUM.get(), "atherium", "Rare Atherium", "Obtain a polished Atherium.", "malachite");
            createToolAdvancements(saver, "atherium", ModItems.ATHERIUM_SWORD.get(), ModItems.ATHERIUM_PICKAXE.get(), ModItems.ATHERIUM_AXE.get(), ModItems.ATHERIUM_SHOVEL.get(), ModItems.ATHERIUM_HOE.get());
            createFullArmorSetAdvancement(saver, "atherium", "Atherium Armor Set", "Craft a full set of Atherium armor.", "atherium", AdvancementType.CHALLENGE, ModItems.ATHERIUM_HELMET.get(), ModItems.ATHERIUM_CHESTPLATE.get(), ModItems.ATHERIUM_LEGGINGS.get(), ModItems.ATHERIUM_BOOTS.get());

            createMiscItemAdvancement(saver, ModItems.GARLIC.get(), "garlic", "Garlic", "Obtain some garlic.", "root");
            createMiscItemAdvancement(saver, ModItems.GARLIC_BREAD.get(), "garlic_bread", "mmm yummy", "I LOVE GARLIC BREAD!!!", "garlic");

            createHiddenAdvancement(saver, ModItems.ROCK_CANDY.get(), "rock_candy", "'Rock Candy?'", "This doesn't look like candy....", "root");
        }


    }
}
