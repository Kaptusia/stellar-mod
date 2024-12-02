package com.kaptusia.stellar.common;

import net.minecraft.world.World;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.Color;

public class Particles {
    public static void spawnExplodingParticles(World level, double x, double y, double z, float size) {
        int colorRandomizerRed = (int) ((Math.random() - 0.5) * 90);
        int colorRandomizerGreen = (int) ((Math.random() - 0.5) * 90);
        int colorRandomizerBlue = (int) ((Math.random() - 0.5) * 90);
        Color startingColor = new Color(60 + colorRandomizerRed, 60 + colorRandomizerGreen, 160 + colorRandomizerBlue);
        Color endingColor = new Color(140 + colorRandomizerRed, 160 + colorRandomizerGreen, 210 + colorRandomizerBlue);
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(size, 0).build())
                .setTransparencyData(GenericParticleData.create(1f, 0.95f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(80)
                .disableNoClip()
                .setFullBrightLighting()
                .spawn(level, x, y, z);
    }

    public static void spawnDustingParticles(World level, double x, double y, double z) {
        int colorRandomizerRed = (int) ((Math.random() - 0.5) * 90);
        int colorRandomizerGreen = (int) ((Math.random() - 0.5) * 90);
        int colorRandomizerBlue = (int) ((Math.random() - 0.5) * 90);
        Color startingColor = new Color(60 + colorRandomizerRed, 60 + colorRandomizerGreen, 160 + colorRandomizerBlue);
        Color endingColor = new Color(140 + colorRandomizerRed, 160 + colorRandomizerGreen, 210 + colorRandomizerBlue);
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                .setScaleData(GenericParticleData.create((float) (0.10f + ((Math.random() - 0.5)) / 10), 0).build())
                .setTransparencyData(GenericParticleData.create(1f, 0.8f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(20)
                .disableNoClip()
                .setFullBrightLighting()
                .spawn(level, x, y, z);
    }
}
