package com.pineapple.tutorialmod.lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.pineapple.tutorialmod.Main;

import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionList {

	
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, Main.MOD_ID);
	
	public static final RegistryObject<Effect> MORE_HEALTH_EFFECT = EFFECTS.register("more_health", () -> new MoreHealthEffect(EffectType.BENEFICIAL, 0xd4ff00));
	
	
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, Main.MOD_ID);
	
	public static final RegistryObject<Potion> MORE_HEALTH_POTION = POTIONS.register("more_health", () -> new Potion(new EffectInstance(MORE_HEALTH_EFFECT.get(), 3600)));
	
	
	
	
	
	
	private static Method brewing_mixes;
	
	
	private static void addMix(Potion start, Item ingredient, Potion result) {
		if(brewing_mixes == null) {
			brewing_mixes = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
			brewing_mixes.setAccessible(true);
		}
		
		try {
			brewing_mixes.invoke(null, start, ingredient, result);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void addBrewingRecipes() {
		addMix(Potions.AWKWARD, ItemList.PEPPERS.get(), PotionList.MORE_HEALTH_POTION.get());
	}
	
	
	
	public static class MoreHealthEffect extends Effect {

		public MoreHealthEffect(EffectType typeIn, int liquidColorIn) {
			super(typeIn, liquidColorIn);
		}
		
	}
}
