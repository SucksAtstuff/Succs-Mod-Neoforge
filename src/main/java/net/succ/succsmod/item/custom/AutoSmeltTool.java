package net.succ.succsmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Optional;

public interface AutoSmeltTool {

    ResourceKey<Enchantment> SILK_TOUCH_KEY = Enchantments.SILK_TOUCH;
    ResourceKey<Enchantment> FORTUNE_KEY = Enchantments.FORTUNE;

    /**
     * Shared auto-smelt logic.
     */
    default boolean tryAutoSmelt(ItemStack stack, Level level, BlockState state,
                                 BlockPos pos, LivingEntity user) {

        if (!(level instanceof ServerLevel serverLevel)) return false;
        if (state.isAir()) return false;

        RegistryAccess access = serverLevel.registryAccess();

        ItemStack blockStack = new ItemStack(state.getBlock().asItem());
        Optional<ItemStack> smeltedOpt = getSmeltingResult(blockStack, serverLevel, access);

        if (smeltedOpt.isEmpty()) return false;

        ItemStack smelted = smeltedOpt.get().copy();

        var enchantRegistry = access.registryOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> silkHolder = enchantRegistry.getHolderOrThrow(SILK_TOUCH_KEY);
        Holder<Enchantment> fortuneHolder = enchantRegistry.getHolderOrThrow(FORTUNE_KEY);

        int silk = EnchantmentHelper.getTagEnchantmentLevel(silkHolder, stack);
        if (silk > 0) return false;

        int fortune = EnchantmentHelper.getTagEnchantmentLevel(fortuneHolder, stack);
        if (fortune > 0) {
            int extra = level.random.nextInt(fortune + 1);
            smelted.grow(extra);
        }

        Block.popResource(serverLevel, pos, smelted);
        serverLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);

        return true;
    }

    /**
     * Smelting recipe lookup.
     */
    private Optional<ItemStack> getSmeltingResult(ItemStack input,
                                                  ServerLevel level,
                                                  RegistryAccess access) {

        return level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING,
                        new SingleRecipeInput(input),
                        level)
                .map(recipe -> recipe.value().getResultItem(access));
    }

    /**
     * Shared tooltip method for auto-smelting tools.
     * Items implementing this interface can call it from appendHoverText().
     */
    default void addAutoSmeltTooltip(ItemStack stack,
                                     Item.TooltipContext ctx,
                                     List<Component> tooltip,
                                     TooltipFlag flag) {

        tooltip.add(net.minecraft.network.chat.Component.translatable("tooltip.succsessentials.auto_smelt"));
    }
}
