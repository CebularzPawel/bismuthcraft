package net.cebularz.bismuthcraft.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties PINEAPPLE = new FoodProperties.Builder().nutrition(1).fast().
            saturationMod(0.2f).build();
}
