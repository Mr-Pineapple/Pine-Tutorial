package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.world.biome.PearlLandBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.RegistryObject;

public class BiomeList {
	
	
	/* Create a new Deferred Registry for all our biomes to register to
	 * This is called in our Main class and added to the EventBus, which saves us making each class one
	 * Alternatively you could use registry events. Though it doesn't make a massive difference.
	 * Deferred Registries are a new and more efficient way of registering lots of things.
	 * 
	 * For updating to 1.16 you'll need to create method from the Deferred Register class, instead of calling on the 
	 * constructor. (DeferredRegister.create(), instead of a new DeferredRegister()).
	 */
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<Biome>(ForgeRegistries.BIOMES, Main.MOD_ID);
	
	
	
	public static final RegistryObject<Biome> PEARL_LAND = BIOMES.register("pearl_land", () -> new PearlLandBiome());
	
	/* Uses the method below to allow all the biomes to spawn and adds the types */
	public static void registerBiomes() {
		registerBiome(PEARL_LAND.get(), Type.PLAINS, Type.DRY);
	}
	
	/* Create a seperate method to implement above so we don't need many more lines for each biome */
	public static void registerBiome(Biome biome, Type... type) {
		BiomeDictionary.addTypes(biome, type);
		BiomeManager.addSpawnBiome(biome);
	}
	
}
