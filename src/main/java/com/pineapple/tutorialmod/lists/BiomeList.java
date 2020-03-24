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
	
	
	
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<Biome>(ForgeRegistries.BIOMES, Main.MOD_ID);
	
	public static final RegistryObject<Biome> PEARL_LAND = BIOMES.register("pearl_land", () -> new PearlLandBiome());
	
	
	public static void registerBiomes() {
		registerBiome(PEARL_LAND.get(), Type.PLAINS, Type.DRY);
	}
	
	public static void registerBiome(Biome biome, Type... type) {
		BiomeDictionary.addTypes(biome, type);
		BiomeManager.addSpawnBiome(biome);
	}
	
}
