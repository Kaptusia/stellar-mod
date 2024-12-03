package com.kaptusia.stellar.common.block.entity;

import com.kaptusia.stellar.common.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.Color;

public class StellarLampBlockEntity extends BlockEntity {

    public StellarLampBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STELLAR_LAMP_BLOCK_ENTITY_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, StellarLampBlockEntity blockEntity) {
        if ((world.getTimeOfDay() / 1000.0) % 24.0 >= 13 &&
                ((world.getTimeOfDay() / 1000.0) % 24.0 <= 23)) {

            int colorRandomizerRed = (int) ((Math.random() - 0.5) * 30);
            int colorRandomizerGreen = (int) ((Math.random() - 0.5) * 30);
            int colorRandomizerBlue = (int) ((Math.random() - 0.5) * 30);
            Color color = new Color(100 + colorRandomizerRed, 110 + colorRandomizerGreen, 185 + colorRandomizerBlue);

            WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                    .setScaleData(GenericParticleData.create(2f, 2f).build())
                    .setTransparencyData(GenericParticleData.create(0.1f, 0.1f).build())
                    .setColorData(ColorParticleData.create(color, color).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                    .setLifetime(1)
                    .disableNoClip()
                    .setFullBrightLighting()
                    .spawn(world, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5);
            WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
                    .setScaleData(GenericParticleData.create(1.2f, 1.2f).build())
                    .setTransparencyData(GenericParticleData.create(0.35f, 0.35f).build())
                    .setColorData(ColorParticleData.create(color, color).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                    .setLifetime(1)
                    .disableNoClip()
                    .setFullBrightLighting()
                    .spawn(world, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5);
            WorldParticleBuilder.create(LodestoneParticleRegistry.STAR_PARTICLE)
                    .setScaleData(GenericParticleData.create(0.45f, 0.45f).build())
                    .setTransparencyData(GenericParticleData.create(0.75f, 0.75f).build())
                    .setColorData(ColorParticleData.create(color, color).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                    .setLifetime(1)
                    .disableNoClip()
                    .setFullBrightLighting()
                    .spawn(world, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5);
        }
    }
}