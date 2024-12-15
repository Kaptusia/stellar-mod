package com.kaptusia.stellar.common.block.custom;

import com.kaptusia.stellar.common.block.ModBlockEntities;
import com.kaptusia.stellar.common.block.ModBlocks;
import com.kaptusia.stellar.common.block.entity.StellarLampBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class StellarLampBlock extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
    public static final BooleanProperty HANGING;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape STANDING_SHAPE;
    protected static final VoxelShape HANGING_SHAPE;

    public StellarLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)));
    }


    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StellarLampBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = (BlockState) this.getDefaultState().with(HANGING, direction == Direction.UP);
                if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return (BlockState) blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Boolean) state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HANGING, WATERLOGGED});
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        return Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite());
    }

    protected static Direction attachedDirection(BlockState state) {
        return (Boolean) state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean) state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean) state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        HANGING = Properties.HANGING;
        WATERLOGGED = Properties.WATERLOGGED;
        STANDING_SHAPE = VoxelShapes.union(Block.createCuboidShape((double) 5.0F, (double) 0.0F, (double) 5.0F, (double) 11.0F, (double) 7.0F, (double) 11.0F), Block.createCuboidShape((double) 6.0F, (double) 7.0F, (double) 6.0F, (double) 10.0F, (double) 9.0F, (double) 10.0F));
        HANGING_SHAPE = VoxelShapes.union(Block.createCuboidShape((double) 5.0F, (double) 1.0F, (double) 5.0F, (double) 11.0F, (double) 8.0F, (double) 11.0F), Block.createCuboidShape((double) 6.0F, (double) 8.0F, (double) 6.0F, (double) 10.0F, (double) 10.0F, (double) 10.0F));
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Make sure to check world.isClient if you only want to tick only on serverside.
        return checkType(type, ModBlockEntities.STELLAR_LAMP_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, StellarLampBlockEntity::tick);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!player.isCreative()) {
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, ModBlocks.STELLAR_LAMP.getPickStack(world, pos, state)));
        }
    }
}