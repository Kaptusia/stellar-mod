package com.kaptusia.stellar.mixin;

import com.kaptusia.stellar.common.block.ModBlocks;
import com.kaptusia.stellar.common.block.entity.HookBlockEntity;
import com.kaptusia.stellar.common.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.BuddingAmethystBlock;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kaptusia.stellar.common.block.custom.HookBlock.WATERLOGGED;
import static net.minecraft.world.RedstoneView.DIRECTIONS;

@Mixin(BuddingAmethystBlock.class)
public class BuddingAmethystBlockMixin {

    @Inject(
            method = {"randomTick"},
            at = @At("HEAD")
    )
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState == ModBlocks.HOOK.getDefaultState().with(WATERLOGGED, true) &&
                    ((HookBlockEntity) world.getBlockEntity(blockPos)).getStack(0).isOf(Items.ECHO_SHARD))
            {
                ((HookBlockEntity) world.getBlockEntity(blockPos)).setStack(0, ModItems.STELLAR_CRYSTAL.getDefaultStack());
                SoundEvent soundEvent = SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE;
                world.playSoundAtBlockCenter(blockPos, soundEvent, SoundCategory.BLOCKS, 1, 1, true);
                soundEvent = SoundEvents.BLOCK_GLASS_BREAK;
                world.playSoundAtBlockCenter(blockPos, soundEvent, SoundCategory.BLOCKS, 1, 1, true);
            }
        }
    }
}
