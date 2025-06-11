//package net.succ.succsmod.item.custom;
//
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.entity.ai.attributes.Attribute;
//import net.minecraft.world.entity.ai.attributes.AttributeModifier;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Rarity;
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.level.Level;
//import top.theillusivec4.curios.api.SlotContext;
//import top.theillusivec4.curios.api.type.capability.ICurioItem;
//
//import java.util.List;
//import java.util.UUID;
//
//public class CustomCurioAttributeItem extends Item implements ICurioItem {
//
//    private final Attribute attribute;
//    private final UUID attributeModifierUUID;
//    private final double amount;
//    private final AttributeModifier.Operation operation;
//    private final String slotType;
//
//    public CustomCurioAttributeItem(Attribute attribute, String uuidString, double amount, AttributeModifier.Operation operation, String slotType) {
//        super(new Properties().stacksTo(1).rarity(Rarity.COMMON));
//        this.attribute = attribute;
//        this.attributeModifierUUID = UUID.fromString(uuidString);
//        this.amount = amount;
//        this.operation = operation;
//        this.slotType = slotType;
//    }
//
//
//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
//        // Add information about the attribute modifier to the tooltip
//        tooltipComponents.add(Component.literal("Grants " + attribute.getDescriptionId() + " modifier: " + amount));
//        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
//    }
//
//    @Override
//    public void curioTick(SlotContext slotContext, ItemStack stack) {
//        // No periodic update needed for this item
//    }
//
//    @Override
//    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
//        if (slotContext.entity() instanceof Player player) {
//            AttributeModifier attributeModifier = new AttributeModifier(attributeModifierUUID, "Custom Attribute Modifier", amount, operation);
//            player.getAttribute(attribute).addTransientModifier(attributeModifier);
//        }
//    }
//
//    @Override
//    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
//        if (slotContext.entity() instanceof Player player) {
//            player.getAttribute(attribute).removeModifier(attributeModifierUUID);
//            updatePlayerHealth(player);
//        }
//    }
//
//    private void updatePlayerHealth(Player player) {
//        player.setHealth(player.getMaxHealth());
//    }
//
//    @Override
//    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
//        return slotContext.identifier().equals(slotType);
//    }
//}
