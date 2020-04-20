package com.pineapple.tutorialmod.world.gen;

import com.pineapple.tutorialmod.lists.BlockList;
import com.pineapple.tutorialmod.lists.FeatureList;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialGeneration {

	private static void setupOreGeneration() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			biome.addFeature(Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
									BlockList.SILVER_ORE.get().getDefaultState(), 10))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 5, 0, 128))));
		}
	}

	private static void generateBushes() {
		ForgeRegistries.BIOMES.forEach(biome -> {
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
					Feature.RANDOM_PATCH.withConfiguration(FeatureList.FROSTBERRY_BERRY_CONFIG)
							.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
		});
	}
	

	public static void generate() {
		setupOreGeneration();
		generateBushes();
	}
	
	
	/*
	 * If you want to create an ore for the end then you need to create a new fillerblock
	 * this is because there isn't one for endstone, as there is no naturally spawning
	 * ore blocks inside of the end currently to do this, you need to call the fillerblocktype
	 * class and use the .create() method.
	 * 
	 * 
	 * This method takes in three parameters.
	 * 
	 * 
	 * The first is the enumName (String) which is just the name of the type, (you can just call this the same as the end_stone)
	 * 
	 * The second is a String, which from what I can see isn't used in the class locally, and also isn't used by the others, so we can just set that to null
	 *
	 * The third parameter is a Java Predicated BlockState. To get this we can simply use a new BlockMatcher() which will take in the block, in this case Blocks.END_STONE
	 *
	 *	
	 * Therefore the creation of it would look somewhat similar to this:
	 * 
	 * .create("end_stone", null, new BlockMatcher(Blocks.END_STONE)
	 *
	 */
	

}
