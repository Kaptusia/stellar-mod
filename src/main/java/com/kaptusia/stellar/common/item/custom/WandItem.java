package com.kaptusia.stellar.common.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.registry.common.particle.LodestoneScreenParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleHolder;

import java.awt.*;

public class WandItem extends Item implements ParticleEmitterHandler.ItemParticleSupplier {
    public WandItem(Settings settings) {
        super(settings);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, World level, float partialTick, ItemStack stack, float x, float y) {
        if (Math.ceil(Math.random() * 6) == 6 &&
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
                    .setLifetime(18)
                    .addMotion(0, 0)
                    .spawnOnStack(3, -3);

        }
    }

}
