package com.pineapple.tutorialmod.objects.blocks;

import java.util.Random;

import com.pineapple.tutorialmod.lists.BlockList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FrostberryBushBlock extends BushBlock implements IGrowable
{
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
	   private static final VoxelShape field_220126_b = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	   private static final VoxelShape field_220127_c = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	   public FrostberryBushBlock(Block.Properties properties) {
	      super(properties);
	      this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
	   }

	   public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
	      return new ItemStack(BlockList.FROSTBERRY_BUSH.get());
	   }

	   @SuppressWarnings("deprecation")
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      if (state.get(AGE) == 0) {
	         return field_220126_b;
	      } else {
	         return state.get(AGE) < 3 ? field_220127_c : super.getShape(state, worldIn, pos, context);
	      }
	   }
	   
	   @SuppressWarnings("deprecation")
	public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
	      super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
	      int i = p_225534_1_.get(AGE);
	      if (i < 3 && p_225534_2_.getLightSubtracted(p_225534_3_.up(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_225534_2_, p_225534_3_, p_225534_1_, p_225534_4_.nextInt(5) == 0)) {
	         p_225534_2_.setBlockState(p_225534_3_, p_225534_1_.with(AGE, Integer.valueOf(i + 1)), 2);
	         net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_225534_2_, p_225534_3_, p_225534_1_);
	      }

	   }

	   @SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
	      int i = p_225533_1_.get(AGE);
	      boolean flag = i == 3;
	      if (!flag && p_225533_4_.getHeldItem(p_225533_5_).getItem() == Items.BONE_MEAL) {
	         return ActionResultType.PASS;
	      } else if (i > 1) {
	         int j = 1 + p_225533_2_.rand.nextInt(2);
	         spawnAsEntity(p_225533_2_, p_225533_3_, new ItemStack(BlockList.FROSTBERRY_BUSH.get()	, j + (flag ? 1 : 0)));
	         p_225533_2_.playSound((PlayerEntity)null, p_225533_3_, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + p_225533_2_.rand.nextFloat() * 0.4F);
	         p_225533_2_.setBlockState(p_225533_3_, p_225533_1_.with(AGE, Integer.valueOf(1)), 2);
	         return ActionResultType.SUCCESS;
	      } else {
	         return super.onBlockActivated(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
	      }
	   }

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(AGE);
	   }

	   /**
	    * Whether this IGrowable can grow
	    */
	   public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
	      return state.get(AGE) < 3;
	   }

	   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
	      return true;
	   }

	   public void grow(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
	      int i = Math.min(3, p_225535_4_.get(AGE) + 1);
	      p_225535_1_.setBlockState(p_225535_3_, p_225535_4_.with(AGE, Integer.valueOf(i)), 2);
	   }
	   
	   
	   
	}