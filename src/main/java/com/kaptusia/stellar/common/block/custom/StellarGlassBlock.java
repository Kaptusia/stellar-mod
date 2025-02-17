package com.kaptusia.stellar.common.block.custom;

import com.kaptusia.stellar.common.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TintedGlassBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StellarGlassBlock extends TintedGlassBlock {
    public StellarGlassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!player.isCreative()) {
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, ModBlocks.STAR_CLEAR_GLASS.getPickStack(world, pos, state)));
        }
    }
}