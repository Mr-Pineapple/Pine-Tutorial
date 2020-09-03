package com.pineapple.tutorialmod.objects.items;

import com.pineapple.tutorialmod.objects.entities.RockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 *	Author:	Mr. Pineapple
 */
public class RockItem extends Item {

	//Default constructor extended from Item
	public RockItem(Properties properties) {
		super(properties);
	}
	
	/*
	 * A method we can override to set what happens when the item is right clicked.
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		//This just gets the stack the player is holding.
		ItemStack stack = playerIn.getHeldItem(handIn);
		//Here I check if the world is the server, if so we can spawn the entity
		if(!worldIn.isRemote) {
			//Create a new instance of our entity
			RockEntity rock = new RockEntity(playerIn, worldIn);
			//This sets the entity to the item we are holding
			rock.setItem(stack);
			//This is the second most important method here, it sets how the entity shall shoot.
			rock.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			//This is the most important, it actually adds it to the world!
			worldIn.addEntity(rock);
		}
		
		/*
		 * I have added a small check here
		 * If the player is not in creative
		 * then it will remove 1 item from
		 * the stack, you could do anything.
		 * Like damage the item, or play a sound
		 */
		if(!playerIn.abilities.isCreativeMode) {
			stack.shrink(1);
		}
		
		//Then we can just return success for the method
		return ActionResult.resultSuccess(stack);
	}
	
}
