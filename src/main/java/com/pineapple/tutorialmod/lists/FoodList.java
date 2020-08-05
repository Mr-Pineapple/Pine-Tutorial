package com.pineapple.tutorialmod.lists;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList {
	
	/* This class isn't really needed if your mod doesn't have many foods, but for instance
	 * like my food mod, I have a class like this so I can easily modify values and not have
	 * a clusetered ItemList/ItemInit class */

	public static final Food PEPPER = (new Food.Builder().hunger(2).saturation(0.2F).effect(new EffectInstance(Effects.GLOWING, 60, 1),  1f).setAlwaysEdible()).build();
	public static final Food ICE_BERRY = (new Food.Builder().hunger(4).saturation(0.5f).setAlwaysEdible()).build();
}
