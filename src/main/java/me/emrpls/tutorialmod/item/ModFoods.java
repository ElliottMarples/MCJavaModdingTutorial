package me.emrpls.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModFoods {
    public static class Properties {
        public static final FoodProperties CRUMPET = new FoodProperties.Builder()
                .nutrition(3)
                .saturationModifier(.25f)
                .alwaysEdible()
                .build();
    }
    public static class Consumables {
        public static final Consumable CRUMPET = net.minecraft.world.item.component.Consumables.defaultFood()
                .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                        List.of(
                            new MobEffectInstance(MobEffects.SPEED, 100, 4),
                            new MobEffectInstance(MobEffects.JUMP_BOOST, 100, 2)
                        )
                    )
                )
                .build();
    }
}
