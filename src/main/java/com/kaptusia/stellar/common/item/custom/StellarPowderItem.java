package com.kaptusia.stellar.common.item.custom;

import com.kaptusia.stellar.common.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BrushItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleHolder;

import java.awt.Color;

import static com.kaptusia.stellar.common.Particles.spawnDustingParticles;
import static com.kaptusia.stellar.common.Particles.spawnExplodingParticles;

public class StellarPowderItem extends BrushItem implements ParticleEmitterHandler.ItemParticleSupplier {

    private static final double MAX_BRUSH_DISTANCE;
    private static final Logger log = LoggerFactory.getLogger(StellarPowderItem.class);

    static {
        MAX_BRUSH_DISTANCE = Math.sqrt(ServerPlayNetworkHandler.MAX_BREAK_SQUARED_DISTANCE) - (double) 1.0F;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 55;
    }

    public StellarPowderItem(Settings settings) {
        super(settings);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks >= 0 && user instanceof PlayerEntity playerEntity) {

            HitResult hitResult = this.getHitResult(user);
            if (hitResult instanceof BlockHitResult blockHitResult) {
                if (hitResult.getType() == HitResult.Type.BLOCK) {

                    BlockPos blockPos = blockHitResult.getBlockPos();
                    int i = this.getMaxUseTime(stack) - remainingUseTicks + 1;
                    boolean bl = i % 10 == 5;
                    boolean bl2 = i % 55 == 0 && i != 0;
                    if (bl) {

                        if ((world.getTimeOfDay() / 1000.0) % 24.0 >= 13 && (world.getTimeOfDay() / 1000.0) % 24.0 <= 22) {
                            //bottom
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );

                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX) / 2),
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ) / 2)
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ) / 2)
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX) / 2),
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );

                            //middle
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + (world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY) / 2,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + (world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY) / 2,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + (world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY) / 2,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + (world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minY
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY) / 2,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );

                            //top
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );

                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX) / 2),
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ) / 2)
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX,
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minZ
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ) / 2)
                            );
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + ((world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().minX
                                            + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxX) / 2),
                                    blockPos.getY() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxY,
                                    blockPos.getZ() + world.getBlockState(blockPos).getOutlineShape(world, blockPos).getBoundingBox().maxZ
                            );
                        }

                        SoundEvent soundEvent = SoundEvents.BLOCK_SAND_PLACE;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                    }


                    if (bl2 &&
                            world.getBlockState(blockPos).getBlock().equals(Blocks.GLASS) &&
                            world.getLightLevel(LightType.SKY, blockPos) == 15 &&
                            (world.getTimeOfDay() / 1000.0) % 24.0 >= 13 &&
                            (world.getTimeOfDay() / 1000.0) % 24.0 <= 23
                    ) {
                        spawnExplodingParticles(
                                world,
                                blockPos.getX() + 0.5,
                                blockPos.getY() + 0.5,
                                blockPos.getZ() + 0.5,
                                3f);
                        SoundEvent soundEvent = SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                        soundEvent = SoundEvents.BLOCK_GLASS_BREAK;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                        soundEvent = SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);

                        world.setBlockState(blockPos, ModBlocks.STAR_CLEAR_GLASS.getDefaultState());
                        playerEntity.getItemCooldownManager().set(this, 10);
                        if (!((PlayerEntity) user).isCreative()) {
                            stack.decrement(1);
                        }
                    }

                    if (bl2 &&
                            world.getBlockState(blockPos).getBlock().equals(Blocks.GREEN_WOOL) &&
                            world.getLightLevel(LightType.SKY, blockPos) == 15 &&
                            (world.getTimeOfDay() / 1000.0) % 24.0 >= 13 &&
                            (world.getTimeOfDay() / 1000.0) % 24.0 <= 23
                    ) {
                        spawnExplodingParticles(
                                world,
                                blockPos.getX() + 0.5,
                                blockPos.getY() + 0.5,
                                blockPos.getZ() + 0.5,
                                3f);
                        SoundEvent soundEvent = SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                        soundEvent = SoundEvents.BLOCK_GLASS_BREAK;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                        soundEvent = SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);

                        world.setBlockState(blockPos, ModBlocks.KAPTUSIA_PLUSHIE.getDefaultState());
                        playerEntity.getItemCooldownManager().set(this, 10);
                        if (!((PlayerEntity) user).isCreative()) {
                            stack.decrement(1);
                        }
                    }

                    if (bl2 &&
                            (world.getBlockState(blockPos).getBlock().equals(Blocks.LANTERN) || world.getBlockState(blockPos).getBlock().equals(Blocks.SOUL_LANTERN)) &&
                            world.getLightLevel(LightType.SKY, blockPos) >= 13 &&
                            (world.getTimeOfDay() / 1000.0) % 24.0 >= 13 &&
                            (world.getTimeOfDay() / 1000.0) % 24.0 <= 23
                    ) {
                        spawnExplodingParticles(
                                world,
                                blockPos.getX() + 0.5,
                                blockPos.getY() + 0.5,
                                blockPos.getZ() + 0.5,
                                3f);
                        SoundEvent soundEvent = SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                        soundEvent = SoundEvents.BLOCK_GLASS_BREAK;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                        soundEvent = SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);

                        world.setBlockState(blockPos, ModBlocks.STAR_INFUSED_LAMP.getStateWithProperties(world.getBlockState(blockPos)));
                        playerEntity.getItemCooldownManager().set(this, 10);
                        if (!((PlayerEntity) user).isCreative()) {
                            stack.decrement(1);
                        }
                    }

                }
                return;
            }
            user.stopUsingItem();
        } else {
            user.stopUsingItem();
        }
    }


    private HitResult getHitResult(LivingEntity user) {
        return MinecraftClient.getInstance().getCameraEntity().raycast(MAX_BRUSH_DISTANCE, 0.0F, false);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, World level, float partialTick, ItemStack stack, float x, float y) {
        if (Math.ceil(Math.random() * 8) == 8 &&
                (level.getTimeOfDay() / 1000.0) % 24.0 >= 13 &&
                (level.getTimeOfDay() / 1000.0) % 24.0 <= 23) {
            int colorRandomizerRed = (int) ((Math.random() - 0.5) * 90);
            int colorRandomizerGreen = (int) ((Math.random() - 0.5) * 90);
            int colorRandomizerBlue = (int) ((Math.random() - 0.5) * 90);
            Color startingColor = new Color(60 + colorRandomizerRed, 60 + colorRandomizerGreen, 160 + colorRandomizerBlue);
            Color endingColor = new Color(140 + colorRandomizerRed, 160 + colorRandomizerGreen, 210 + colorRandomizerBlue);

            ScreenParticleBuilder.create(LodestoneScreenParticleRegistry.TWINKLE, target)
                    .setScaleData(GenericParticleData.create((float) (0.25f + ((Math.random() - 0.5)) / 5), 0).build())
                    .setTransparencyData(GenericParticleData.create(1f, 0.9f).build())
                    .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                    .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                    .setLifetime(20)
                    .addMotion(0, -0.12f)
                    .spawnOnStack((float) (0f + ((Math.random() - 0.5) * 10)), (float) (-1.2f + ((Math.random() - 0.5) * 10)));
        }
    }
}