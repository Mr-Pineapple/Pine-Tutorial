package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionList {

	
	/* Create a new Deferred Registry for all our potions and effects to register to
	 * This is called in our Main class and added to the EventBus, which saves us making each class one
	 * Alternatively you could use registry events. Though it doesn't make a massive difference.
	 * Deferred Registries are a new and more efficient way of registering lots of things.
	 * 
	 * For updating to 1.16 you'll need to create method from the Deferred Register class, instead of calling on the 
	 * constructor. (DeferredRegister.create(), instead of a new DeferredRegister()).
	 */
	
	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, Main.MOD_ID);
	
	public static final RegistryObject<Effect> MORE_HEALTH_EFFECT = EFFECTS.register("more_health", () -> new MoreHealthEffect(EffectType.BENEFICIAL, 0xd4ff00));
	
	
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, Main.MOD_ID);
	
	public static final RegistryObject<Potion> MORE_HEALTH_POTION = POTIONS.register("more_health", () -> new Potion(new EffectInstance(MORE_HEALTH_EFFECT.get(), 3600)));
	
	public static class MoreHealthEffect extends Effect {

		public MoreHealthEffect(EffectType typeIn, int liquidColorIn) {
			super(typeIn, liquidColorIn);
		}
		
	}
}
