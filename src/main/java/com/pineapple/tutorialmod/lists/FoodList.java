package com.pineapple.tutorialmod.lists;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList {

	public static final Food PEPPER = (new Food.Builder().hunger(2).saturation(0.2F).effect(new EffectInstance(Effects.GLOWING, 60, 1),  1f).setAlwaysEdible()).build();
	public static final Food ICE_BERRY = (new Food.Builder().hunger(4).saturation(0.5f).setAlwaysEdible()).build();
}
