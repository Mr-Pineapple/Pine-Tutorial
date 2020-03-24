package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.objects.blocks.CustomStairsBlock;
import com.pineapple.tutorialmod.objects.blocks.PepperCropsBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockList
{
	
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MOD_ID);
	
	public static final RegistryObject<Block> TUTORIAL_BLOCK = BLOCKS.register("tutorial_block", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0f)));
	public static final RegistryObject<Block> TUTORIAL_SLAB = BLOCKS.register("tutorial_slab", () -> new SlabBlock(Block.Properties.from(TUTORIAL_BLOCK.get())));
	public static final RegistryObject<Block> TUTORIAL_STAIRS = BLOCKS.register("tutorial_stairs", () -> new CustomStairsBlock(TUTORIAL_BLOCK.get().getDefaultState(), Block.Properties.from(TUTORIAL_BLOCK.get())));
	public static final RegistryObject<Block> TUTORIAL_WALL =BLOCKS.register("tutorial_wall", () -> new WallBlock(Block.Properties.from(TUTORIAL_BLOCK.get())));
	
	
	
	public static final DeferredRegister<Block> NO_ITEM_BLOCK = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MOD_ID);
	
	public static final RegistryObject<Block> PEPPER_BUSH = NO_ITEM_BLOCK.register("pepper_crop", () -> new PepperCropsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)));
	
	
}