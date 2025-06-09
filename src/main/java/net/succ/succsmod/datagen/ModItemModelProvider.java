package net.succ.succsmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.block.ModBlocks;
import net.succ.succsmod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;


import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SuccsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Register models for gems
        basicItem(ModItems.ATHERIUM.get());
        basicItem(ModItems.RUBY.get());
        basicItem(ModItems.SAPPHIRE.get());
        basicItem(ModItems.SUNSTONE.get());
        basicItem(ModItems.MALACHITE.get());

        // Register models for dirty gems
        basicItem(ModItems.DIRTY_ATHERIUM.get());
        basicItem(ModItems.DIRTY_RUBY.get());
        basicItem(ModItems.DIRTY_SAPPHIRE.get());
        basicItem(ModItems.DIRTY_SUNSTONE.get());
        basicItem(ModItems.DIRTY_MALACHITE.get());

        // Register models for other items
        basicItem(ModItems.GOLD_HANDLE.get());
        basicItem(ModItems.GARLIC.get());
        basicItem(ModItems.GARLIC_BREAD.get());
        basicItem(ModItems.ROCK.get());
        basicItem(ModItems.ROCK_CANDY.get());

        // Register models for atherium tools
        handheldItem(ModItems.ATHERIUM_SWORD.get());
        handheldItem(ModItems.ATHERIUM_PICKAXE.get());
        handheldItem(ModItems.ATHERIUM_AXE.get());
        handheldItem(ModItems.ATHERIUM_SHOVEL.get());
        handheldItem(ModItems.ATHERIUM_HOE.get());
        handheldItem(ModItems.ATHERIUM_HAMMER.get());
        handheldItem(ModItems.ATHERIUM_PAXEL.get());

        // Register models for malachite tools
        handheldItem(ModItems.MALACHITE_SWORD.get());
        handheldItem(ModItems.MALACHITE_PICKAXE.get());
        handheldItem(ModItems.MALACHITE_AXE.get());
        handheldItem(ModItems.MALACHITE_SHOVEL.get());
        handheldItem(ModItems.MALACHITE_HOE.get());
        handheldItem(ModItems.MALACHITE_HAMMER.get());
        handheldItem(ModItems.MALACHITE_PAXEL.get());

        // Register models for ruby tools
        handheldItem(ModItems.RUBY_SWORD.get());
        handheldItem(ModItems.RUBY_PICKAXE.get());
        handheldItem(ModItems.RUBY_AXE.get());
        handheldItem(ModItems.RUBY_SHOVEL.get());
        handheldItem(ModItems.RUBY_HOE.get());
        handheldItem(ModItems.RUBY_HAMMER.get());
        handheldItem(ModItems.RUBY_PAXEL.get());

        // Register models for sapphire tools
        handheldItem(ModItems.SAPPHIRE_SWORD.get());
        handheldItem(ModItems.SAPPHIRE_PICKAXE.get());
        handheldItem(ModItems.SAPPHIRE_AXE.get());
        handheldItem(ModItems.SAPPHIRE_SHOVEL.get());
        handheldItem(ModItems.SAPPHIRE_HOE.get());
        handheldItem(ModItems.SAPPHIRE_HAMMER.get());
        handheldItem(ModItems.SAPPHIRE_PAXEL.get());

        // Register models for sunstone tools
        handheldItem(ModItems.SUNSTONE_SWORD.get());
        handheldItem(ModItems.SUNSTONE_PICKAXE.get());
        handheldItem(ModItems.SUNSTONE_AXE.get());
        handheldItem(ModItems.SUNSTONE_SHOVEL.get());
        handheldItem(ModItems.SUNSTONE_HOE.get());
        handheldItem(ModItems.SUNSTONE_HAMMER.get());
        handheldItem(ModItems.SUNSTONE_PAXEL.get());

        // Register models for atherium armor
        trimmedArmorItem(ModItems.ATHERIUM_HELMET);
        trimmedArmorItem(ModItems.ATHERIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.ATHERIUM_LEGGINGS);
        trimmedArmorItem(ModItems.ATHERIUM_BOOTS);

        // Register models for malachite armor
        trimmedArmorItem(ModItems.MALACHITE_HELMET);
        trimmedArmorItem(ModItems.MALACHITE_CHESTPLATE);
        trimmedArmorItem(ModItems.MALACHITE_LEGGINGS);
        trimmedArmorItem(ModItems.MALACHITE_BOOTS);

        // Register models for ruby armor
        trimmedArmorItem(ModItems.RUBY_HELMET);
        trimmedArmorItem(ModItems.RUBY_CHESTPLATE);
        trimmedArmorItem(ModItems.RUBY_LEGGINGS);
        trimmedArmorItem(ModItems.RUBY_BOOTS);

        // Register models for sapphire armor
        trimmedArmorItem(ModItems.SAPPHIRE_HELMET);
        trimmedArmorItem(ModItems.SAPPHIRE_CHESTPLATE);
        trimmedArmorItem(ModItems.SAPPHIRE_LEGGINGS);
        trimmedArmorItem(ModItems.SAPPHIRE_BOOTS);

        // Register models for sunstone armor
        trimmedArmorItem(ModItems.SUNSTONE_HELMET);
        trimmedArmorItem(ModItems.SUNSTONE_CHESTPLATE);
        trimmedArmorItem(ModItems.SUNSTONE_LEGGINGS);
        trimmedArmorItem(ModItems.SUNSTONE_BOOTS);

        complexBlock(ModBlocks.GEM_POLISHING_TABLE.get());
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = SuccsMod.MOD_ID; // Change this to your mod id

        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }


    private ItemModelBuilder complexBlock(Block block) {
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
        return withExistingParent(blockId.getPath(), ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "block/" + blockId.getPath()));
    }


    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
