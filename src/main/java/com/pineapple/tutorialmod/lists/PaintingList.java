package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PaintingList {

	/* Create a new Deferred Registry for all our paintings to register to
	 * This is called in our Main class and added to the EventBus, which saves us making each class one
	 * Alternatively you could use registry events. Though it doesn't make a massive difference.
	 * Deferred Registries are a new and more efficient way of registering lots of things.
	 * 
	 * For updating to 1.16 you'll need to create method from the Deferred Register class, instead of calling on the 
	 * constructor. (DeferredRegister.create(), instead of a new DeferredRegister()).
	 */
	public static final DeferredRegister<PaintingType> PAINTING_TYPES = new DeferredRegister<PaintingType>(ForgeRegistries.PAINTING_TYPES, Main.MOD_ID);
	
	/* Because all painting and other textures are stiched together on startup, they are now in their own texture files
	 * This means we can now easily add as many paintings as we want to the game, without having the possibility 
	 * of overriding vanilla or other mods paintings.
	 * 
	 * To do this we get the DeferredRegistry and we add a new Painting Type, this takes the dimensions of the texutre (x, y)
	 * A 16x16 painting would take up a 1x1 block, meaning that the picture below takes up a 4x3 block as it is ((16x4), (16x3))
	 */
	
	public static final RegistryObject<PaintingType> CIRCLE = PAINTING_TYPES.register("circle", () -> new PaintingType(64,48));
	
}
