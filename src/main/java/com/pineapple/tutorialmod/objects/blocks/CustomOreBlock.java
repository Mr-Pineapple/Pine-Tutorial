package com.pineapple.tutorialmod.objects.blocks;

import java.util.Random;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

public class CustomOreBlock extends OreBlock {

	public CustomOreBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	protected int getExperience(Random random) {
		return MathHelper.nextInt(random, 2, 10);
	}
	
}
