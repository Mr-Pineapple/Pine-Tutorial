package com.pineapple.tutorialmod.lists;

import com.google.common.collect.ImmutableSet;
import com.pineapple.tutorialmod.objects.blocks.FrostberryBushBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class FeatureList {

	/* This just gets the BlockState for the frostberry bush, and returns the highest age, so we can call it easily */
	public static final BlockState BUSH = BlockList.FROSTBERRY_BUSH.get().getDefaultState()
			.with(FrostberryBushBlock.AGE, 3);

	/* This is a vanilla like BlockCluserFeatureConfig, used just like vanilla to call */
	public static final BlockClusterFeatureConfig BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(BUSH), new SimpleBlockPlacer()).tries(64)
					//whitelist
					.func_227316_a_(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock()))).func_227317_b_().build();

}
