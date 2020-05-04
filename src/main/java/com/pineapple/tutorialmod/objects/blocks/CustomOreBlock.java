package com.pineapple.tutorialmod.objects.blocks;

import java.util.Random;

import com.pineapple.tutorialmod.lists.ParticleList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CustomOreBlock extends Block {

	public CustomOreBlock(Properties properties) {
		super(properties);
	}
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		return MathHelper.nextInt(RANDOM, 2, 10);
	}
	
	
	@OnlyIn(Dist.CLIENT)
	   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	      double d0 = (double)pos.getX() + 0.5D;
	      double d1 = (double)pos.getY() + 1.5D;
	      double d2 = (double)pos.getZ() + 0.5D;
	      worldIn.addParticle(ParticleList.ORANGE_PARTICLE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	   }
	
}
