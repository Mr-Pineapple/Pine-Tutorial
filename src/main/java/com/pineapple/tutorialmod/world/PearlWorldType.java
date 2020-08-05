package com.pineapple.tutorialmod.world;

import com.pineapple.tutorialmod.lists.BiomeList;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class PearlWorldType extends WorldType {

	/*
	 * This class is just so we could test to see if our biome worked, it isn't really needed as there are buffet worlds now.
	 */
	
	public PearlWorldType() {
		super("pearl_land_type");
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		
		OverworldGenSettings genSettings = new OverworldGenSettings();
		SingleBiomeProviderSettings singleSettings = new SingleBiomeProviderSettings(world.getWorldInfo());
		singleSettings.setBiome(BiomeList.PEARL_LAND.get());
		return new OverworldChunkGenerator(world, new SingleBiomeProvider(singleSettings), genSettings);
		
	}

}
