package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.objects.blocks.CustomOreBlock;
import com.pineapple.tutorialmod.objects.blocks.FrostberryBushBlock;
import com.pineapple.tutorialmod.objects.blocks.PepperCropsBlock;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockList
{
	
	/* Create a new Deferred Registry for all our blocks to register to
	 * This is called in our Main class and added to the EventBus, which saves us making each class one
	 * Alternatively you could use registry events. Though it doesn't make a massive difference.
	 * Deferred Registries are a new and more efficient way of registering lots of things.
	 * 
	 * For updating to 1.16 you'll need to create method from the Deferred Register class, instead of calling on the 
	 * constructor. (DeferredRegister.create(), instead of a new DeferredRegister()).
	 */
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MOD_ID);
	
	public static final RegistryObject<Block> TUTORIAL_BLOCK = BLOCKS.register("tutorial_block", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0f)));
	public static final RegistryObject<Block> TUTORIAL_SLAB = BLOCKS.register("tutorial_slab", () -> new SlabBlock(Block.Properties.from(TUTORIAL_BLOCK.get())));
	
	/*
	 * In the stairs tutorial I created a new stairs class as the constructor was deprecated, for some reason I
	 * didn't read it and saw there was a second one which takes in a supplier `() -> ` now we just get the defaultState
	 * of the block. Simple!
	 */
	public static final RegistryObject<Block> TUTORIAL_STAIRS = BLOCKS.register("tutorial_stairs", () -> new StairsBlock(() -> TUTORIAL_BLOCK.get().getDefaultState(), Block.Properties.from(TUTORIAL_BLOCK.get())));
	
	public static final RegistryObject<Block> TUTORIAL_WALL =BLOCKS.register("tutorial_wall", () -> new WallBlock(Block.Properties.from(TUTORIAL_BLOCK.get())));
	
	/*
	 * Just like in the music disc tutorial I made it visually easier for people to understand making a new class for the block/item
	 * However, we can just extend it here
	 */
	public static final RegistryObject<Block> TUTORIAL_DOOR = BLOCKS.register("tutorial_door", () -> new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)) {} );
	public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new CustomOreBlock(Block.Properties.from(Blocks.IRON_ORE)));
	
	
	/* A normal Deferred Register, however, this one won't be called in our loop in Main, therefore if we wanted the item to have
	 * special properties then we don't have to filter each of them out manually, or find each instance of the block
	 */
	public static final DeferredRegister<Block> NO_ITEM_BLOCK = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MOD_ID);
	
	public static final RegistryObject<Block> PEPPER_BUSH = NO_ITEM_BLOCK.register("pepper_crop", () -> new PepperCropsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> FROSTBERRY_BUSH = NO_ITEM_BLOCK.register("frostberry_bush", () -> new FrostberryBushBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));
	
	
}
