package com.kaptusia.stellar.common.block.custom;

import com.kaptusia.stellar.common.block.ModBlocks;
import com.kaptusia.stellar.common.block.entity.HookBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class HookBlock extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
    public HookBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HookBlockEntity(pos, state);
    }


    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape HOOK_SHAPE = Block.createCuboidShape(7, 7, 7, 9, 16, 9);

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof HookBlockEntity hookBlockEntity) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (!world.isClient) {
                world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, hookBlockEntity.getItems().get(0)));
                hookBlockEntity.addItem(player, itemStack);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return HOOK_SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockState blockState = (BlockState) this.getDefaultState();
        if (this.getDefaultState().canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
            return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!player.isCreative()) {
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, ModBlocks.HOOK.getPickStack(world, pos, state)));
        }
    }

}
