package com.pineapple.tutorialmod.lists;

import com.google.common.collect.ImmutableSet;
import com.pineapple.tutorialmod.objects.blocks.FrostberryBushBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class FeatureList {

	private static final BlockState FROSTBERRY_BUSH = BlockList.FROSTBERRY_BUSH.get().getDefaultState()
			.with(FrostberryBushBlock.AGE, 3);

	public static final BlockClusterFeatureConfig FROSTBERRY_BERRY_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(FROSTBERRY_BUSH), new SimpleBlockPlacer()).tries(64)
					.func_227316_a_(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock()))).func_227317_b_().build();

}
