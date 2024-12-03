package com.kaptusia.stellar.mixin;

import com.kaptusia.stellar.common.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.Color;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Shadow
    public abstract ItemStack getStack();

    @Shadow
    public abstract ItemEntity copy();

    @Inject(
            method = {"tick"},
            at = @At("HEAD")
    )
    public void tick(CallbackInfo ci) {
        if (this.getStack().isOf(ModItems.STELLAR_CRYSTAL) || this.getStack().isOf(ModItems.STELLAR_POWDER)) {
            double x = this.copy().getX();
            double y = this.copy().getY();
            double z = this.copy().getZ();
            if (Math.ceil(Math.random() * 14) == 14 &&
                    (MinecraftClient.getInstance().world.getTimeOfDay() / 1000.0) % 24.0 >= 13 &&
                    (MinecraftClient.getInstance().world.getTimeOfDay() / 1000.0) % 24.0 <= 23) {
                int colorRandomizerRed = (int) ((Math.random() - 0.5) * 90);
                int colorRandomizerGreen = (int) ((Math.random() - 0.5) * 90);
                int colorRandomizerBlue = (int) ((Math.random() - 0.5) * 90);
                Color startingColor = new Color(60 + colorRandomizerRed, 60 + colorRandomizerGreen, 160 + colorRandomizerBlue);
                Color endingColor = new Color(140 + colorRandomizerRed, 160 + colorRandomizerGreen, 210 + colorRandomizerBlue);

                WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                        .setScaleData(GenericParticleData.create((float) (0.13f + ((Math.random() - 0.5) / 10)), 0).build())
                        .setTransparencyData(GenericParticleData.create(0.6f, 0.5f).build())
                        .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                        .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                        .setLifetime(30)
                        .addMotion(0, 0.006f, 0)
                        .spawn(MinecraftClient.getInstance().world, x + ((Math.random() - 0.5) / 3), y + 0.25 + ((Math.random() - 0.5) / 2), z + ((Math.random() - 0.5) / 3));
            }
        }
    }
}