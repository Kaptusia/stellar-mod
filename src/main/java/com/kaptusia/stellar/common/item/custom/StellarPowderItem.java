package com.kaptusia.stellar.common.item.custom;

import com.kaptusia.stellar.common.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
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
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (bl) {

                        if ((client.world.getTimeOfDay() / 1000) % 24.0 >= 13 && (client.world.getTimeOfDay() / 1000) % 24.0 <= 22) {
                            // bottom
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY(),
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY(),
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY(),
                                    blockPos.getZ() + 1);
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY(),
                                    blockPos.getZ() + 1);

                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 0.5,
                                    blockPos.getY(),
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY(),
                                    blockPos.getZ() + 0.5);
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY(),
                                    blockPos.getZ() + 0.5);
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 0.5,
                                    blockPos.getY(),
                                    blockPos.getZ() + 1);

                            // middle
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY() + 0.5,
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY() + 0.5,
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY() + 0.5,
                                    blockPos.getZ() + 1);
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY() + 0.5,
                                    blockPos.getZ() + 1);

                            // top
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY() + 1,
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY() + 1,
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY() + 1,
                                    blockPos.getZ() + 1);
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY() + 1,
                                    blockPos.getZ() + 1);

                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 0.5,
                                    blockPos.getY() + 1,
                                    blockPos.getZ());
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 1,
                                    blockPos.getY() + 1,
                                    blockPos.getZ() + 0.5);
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX(),
                                    blockPos.getY() + 1,
                                    blockPos.getZ() + 0.5);
                            spawnDustingParticles(
                                    world,
                                    blockPos.getX() + 0.5,
                                    blockPos.getY() + 1,
                                    blockPos.getZ() + 1);
                        }

                        SoundEvent soundEvent = SoundEvents.BLOCK_SAND_PLACE;
                        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS);
                    }


                    if (bl2 &&
                            world.getBlockState(blockPos).getBlock().equals(Blocks.GLASS) &&
                            world.getLightLevel(LightType.SKY, blockPos) == 15 &&
                            (client.world.getTimeOfDay() / 1000) % 24.0 >= 13 &&
                            (client.world.getTimeOfDay() / 1000) % 24.0 <= 23
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

                        world.setBlockState(blockPos, ModBlocks.STELLAR_GLASS.getDefaultState());
                        playerEntity.getItemCooldownManager().set(this, 10);
                        stack.decrement(1);
                    }

                    if (bl2 &&
                            (world.getBlockState(blockPos).getBlock().equals(Blocks.LANTERN) || world.getBlockState(blockPos).getBlock().equals(Blocks.SOUL_LANTERN)) &&
                            world.getLightLevel(LightType.SKY, blockPos) >= 13 &&
                            (client.world.getTimeOfDay() / 1000) % 24.0 >= 13 &&
                            (client.world.getTimeOfDay() / 1000) % 24.0 <= 23
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

                        world.setBlockState(blockPos, ModBlocks.STELLAR_LAMP.getStateWithProperties(world.getBlockState(blockPos)));
                        playerEntity.getItemCooldownManager().set(this, 10);
                        stack.decrement(1);
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
        return ProjectileUtil.getCollision(user, (entity) -> !entity.isSpectator() && entity.canHit(), MAX_BRUSH_DISTANCE);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, World level, float partialTick, ItemStack stack, float x, float y) {
        if (Math.ceil(Math.random() * 8) == 8 &&
                (MinecraftClient.getInstance().world.getTimeOfDay() / 1000) % 24.0 >= 13 &&
                (MinecraftClient.getInstance().world.getTimeOfDay() / 1000) % 24.0 <= 23) {
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