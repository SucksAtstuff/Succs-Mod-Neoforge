package net.succ.succsmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.succ.succsmod.block.entity.ModBlockEntities;
import net.succ.succsmod.block.entity.custom.GemPolishingTableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class GemPolishingTableBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 5, 16);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public GemPolishingTableBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    /* Block Shape + Facing */
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    /* BlockEntity */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GemPolishingTableBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pType) {
        if (pLevel.isClientSide) return null;

        return createTickerHelper(pType, ModBlockEntities.GEM_POLISHING_TABLE_BE.get(),
                (lvl, pos, state, be) -> be.tick(lvl, pos, state));

    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof GemPolishingTableBlockEntity station) {
                station.drops();
            }

        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    /* Interactions */

    public ItemInteractionResult useItemOn (BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
                                 InteractionHand pHand, BlockHitResult pHit) {
        if (!(pLevel.getBlockEntity(pPos) instanceof GemPolishingTableBlockEntity be))
            return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;

        ItemStack heldItem = pPlayer.getItemInHand(pHand);

        // Sneak-right-click = open GUI
        if (pPlayer.isShiftKeyDown()) {
            // Check that we're on the server and the player are a ServerPlayer
            if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayer) {
                // Open the menu using vanilla API supported by NeoForge
                serverPlayer.openMenu(be);
            }
            return ItemInteractionResult.SUCCESS;
        }


        // Insert item
        if (be.inventory.getStackInSlot(0).isEmpty() && !heldItem.isEmpty()) {
            be.inventory.insertItem(0, heldItem.copyWithCount(1), false);
            heldItem.shrink(1);
            pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            return ItemInteractionResult.SUCCESS;
        }

        // Take item
        if (heldItem.isEmpty() && !be.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack inside = be.inventory.extractItem(0,1, false);
            be.clearContents();
            pPlayer.setItemInHand(pHand, inside);
            pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
    }
}
