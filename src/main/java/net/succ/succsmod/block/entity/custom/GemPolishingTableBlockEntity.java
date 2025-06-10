package net.succ.succsmod.block.entity.custom;

import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.WaterFluid;
import net.succ.succsmod.block.custom.GemPolishingTableBlock;
import net.succ.succsmod.recipe.GemPolishingRecipe;
import net.succ.succsmod.recipe.GemPolishingRecipeInput;
import net.succ.succsmod.recipe.ModRecipes;
import net.succ.succsmod.screen.custom.GemPolishingTableBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GemPolishingTableBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int FLUID_ITEM_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    private static final int FLUID_CRAFT_AMOUNT = 1000; // amount of fluid per crafting that is consumed

    private final FluidTank FLUID_TANK = createFluidTank();
    private FluidTank createFluidTank() {
        return new FluidTank(64000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if(!level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                // Defensive null check in case the FluidStack is null or empty
                if (stack == null || stack.isEmpty()) {
                    return false;
                }

                // Compare the fluid in the stack to the vanilla water fluid
                return stack.getFluid() == Fluids.WATER;
            }
        };
    }

    public GemPolishingTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(net.succ.succsmod.block.entity.ModBlockEntities.GEM_POLISHING_TABLE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> GemPolishingTableBlockEntity.this.progress;
                    case 1 -> GemPolishingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0: GemPolishingTableBlockEntity.this.progress = pValue;
                    case 1: GemPolishingTableBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }


    public IFluidHandler getFluidTank(@Nullable Direction direction) {
        return this.FLUID_TANK;
    }

    public FluidStack getFluid() {
        return FLUID_TANK.getFluid();
    }

    public IItemHandler getItemHandler(Direction direction) {
        return this.itemHandler;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.succsessentials.gem_polishing_table");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new GemPolishingTableBlockMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("gem_polsihing_table.progress", progress);
        pTag.putInt("gem_polsihing_table.max_progress", maxProgress);

        pTag = FLUID_TANK.writeToNBT(pRegistries, pTag);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("gem_polsihing_table.progress");
        maxProgress = pTag.getInt("gem_polsihing_table.max_progress");

        FLUID_TANK.readFromNBT(pRegistries, pTag);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if(hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            increaseCraftingProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(GemPolishingTableBlock.LIT, true));
            setChanged(level, pPos, pState);

            if (hasCraftingFinished()) {
                craftItem();
                extractFluidForCrafting();
                resetProgress();
            }

        } else {
            resetProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(GemPolishingTableBlock.LIT, false));
        }

        if (hasFluidStackInSlot()) {
            transferFluidToTank();
        }
    }

    private void extractFluidForCrafting() {
        this.FLUID_TANK.drain(FLUID_CRAFT_AMOUNT, IFluidHandler.FluidAction.EXECUTE);
    }

    private void transferFluidToTank() {
        FluidActionResult result = FluidUtil.tryEmptyContainer(itemHandler.getStackInSlot(0), this.FLUID_TANK, Integer.MAX_VALUE, null, true);
        if(result.result != ItemStack.EMPTY) {
            itemHandler.setStackInSlot(FLUID_ITEM_SLOT, result.result);
        }
    }

    private boolean hasFluidStackInSlot() {
        return !itemHandler.getStackInSlot(FLUID_ITEM_SLOT).isEmpty()
                && itemHandler.getStackInSlot(FLUID_ITEM_SLOT).getCapability(Capabilities.FluidHandler.ITEM, null) != null
                && !itemHandler.getStackInSlot(FLUID_ITEM_SLOT).getCapability(Capabilities.FluidHandler.ITEM, null).getFluidInTank(0).isEmpty();
    }


    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        Optional<RecipeHolder<GemPolishingRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<GemPolishingRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().getResultItem(null);
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output) && hasEnoughFluidToCraft();
    }

    private boolean hasEnoughFluidToCraft() {
        return FLUID_TANK.getFluidAmount() >= FLUID_CRAFT_AMOUNT;
    }



    private Optional<RecipeHolder<GemPolishingRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.GEM_POLISHING_TYPE.get(), new GemPolishingRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}