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
					Feature.RANDOM_PATCH.withConfiguration(FeatureList.BUSH_CONFIG)
							.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
		});
	}

	public static void generate() {
		setupOreGeneration();
		generateBushes();
	}

}
