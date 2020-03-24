package com.pineapple.tutorialmod.world.biome;

import com.pineapple.tutorialmod.lists.BlockList;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class PearlLandBiome extends Biome {

	public PearlLandBiome() {
		super(new Biome.Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockList.TUTORIAL_BLOCK.get().getDefaultState(), BlockList.TUTORIAL_BLOCK.get().getDefaultState(), BlockList.TUTORIAL_BLOCK.get().getDefaultState()))
				.precipitation(RainType.RAIN)
				.category(Category.PLAINS)
				.downfall(0.3f)
				.depth(0.125f)
				.temperature(0.5f)
				.scale(0.5f)
				.waterColor(0x047d53)
				.waterFogColor(0x047d53)
				.parent(null));
		
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addBamboo(this);
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addStructures(this);
		DefaultBiomeFeatures.addSparseOakTrees(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSprings(this);

		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 100, 5, 10));
		
		
	}

	
	
}
