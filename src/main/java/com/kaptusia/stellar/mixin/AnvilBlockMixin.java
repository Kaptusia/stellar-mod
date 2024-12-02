package com.kaptusia.stellar.mixin;

import com.kaptusia.stellar.common.item.ModItems;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.kaptusia.stellar.common.Particles.spawnExplodingParticles;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {

    @Inject(
            method = {"onLanding"},
            at = @At("HEAD")
    )
    private void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo ci) {
        List<Entity> other = world.getOtherEntities(fallingBlockEntity.getVehicle(), new Box(pos));
        for (int i = 0; i < other.size(); i++) {
            // item crafting part
            if (other.get(i).getType() == EntityType.ITEM) {
                if (((ItemEntity) other.get(i)).getStack().isOf(ModItems.STELLAR_CRYSTAL)) {
                    other.get(i).kill();
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(ModItems.STELLAR_POWDER, ((ItemEntity) other.get(i)).getStack().getCount())));
                    spawnExplodingParticles(
                            MinecraftClient.getInstance().world,
                            pos.getX() + 0.5,
                            pos.getY() + 0.0625,
                            pos.getZ() + 0.5,
                            1.2f);
                    SoundEvent soundEvent = SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE;
                    world.playSound(MinecraftClient.getInstance().player, pos, soundEvent, SoundCategory.BLOCKS);
                    soundEvent = SoundEvents.BLOCK_GLASS_BREAK;
                    world.playSound(MinecraftClient.getInstance().player, pos, soundEvent, SoundCategory.BLOCKS);
                    soundEvent = SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST;
                    world.playSound(MinecraftClient.getInstance().player, pos, soundEvent, SoundCategory.BLOCKS);
                }

                if (((ItemEntity) other.get(i)).getStack().isOf(Items.SUGAR_CANE)) {
                    other.get(i).kill();
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.SUGAR, ((ItemEntity) other.get(i)).getStack().getCount())));
                }

                if (((ItemEntity) other.get(i)).getStack().isOf(Items.CREEPER_HEAD)) {
                    other.get(i).kill();
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.GUNPOWDER, ((ItemEntity) other.get(i)).getStack().getCount())));
                }

                if (((ItemEntity) other.get(i)).getStack().isOf(Items.BLAZE_ROD)) {
                    other.get(i).kill();
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.BLAZE_POWDER, ((ItemEntity) other.get(i)).getStack().getCount() * 2)));
                }

                if (((ItemEntity) other.get(i)).getStack().isOf(Items.BONE)) {
                    other.get(i).kill();
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.BONE_MEAL, ((ItemEntity) other.get(i)).getStack().getCount() * 3)));
                }

                if (((ItemEntity) other.get(i)).getStack().isOf(Items.REDSTONE_BLOCK)) {
                    other.get(i).kill();
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.REDSTONE, ((ItemEntity) other.get(i)).getStack().getCount() * 9)));
                }

                if (((ItemEntity) other.get(i)).getStack().isOf(Items.GLOWSTONE)) {
                    other.get(i).kill();
                    world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.GLOWSTONE_DUST, ((ItemEntity) other.get(i)).getStack().getCount() * 4)));
                }
            }
        }
    }
}