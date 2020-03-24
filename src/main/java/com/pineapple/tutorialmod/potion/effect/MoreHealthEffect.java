package com.pineapple.tutorialmod.potion.effect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.pineapple.tutorialmod.lists.ItemList;
import com.pineapple.tutorialmod.lists.PotionList;

import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class MoreHealthEffect extends Effect
{
	public MoreHealthEffect(EffectType type, int color)
	{
		super(type, color);
	}
	
	
	private static Method brewing;
	
	private static void addMix(Potion original, Item ingredient, Potion result) {
		
		if(brewing == null) {
			brewing = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
			brewing.setAccessible(true);
		}
		
		try {
			brewing.invoke(null, original, ingredient, result);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void addBrewingRecipes() {
		addMix(Potions.AWKWARD, ItemList.TUTORIAL_ITEM.get(), PotionList.MORE_HEALTH_POTION.get());
	}
	
	
}
