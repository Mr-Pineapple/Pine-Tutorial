package com.pineapple.tutorialmod.objects.entities;

import com.pineapple.tutorialmod.lists.EntityList;
import com.pineapple.tutorialmod.lists.ItemList;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 *	Author:	Mr. Pineapple
 */
public class RockEntity extends ProjectileItemEntity {

	/*
	 * We add three different constructors so we can use the class in a variety of places as well as feeding
	 * values into the class itself when needed.
	 */
	public RockEntity(EntityType<RockEntity> type, World world) {
		super(type, world);
	}
	
	public RockEntity(LivingEntity entity, World world) {
		super(EntityList.ROCK_PROJETILE.get(), entity, world);
	}
	
	public RockEntity(double x, double y, double z, World world) {
		super(EntityList.ROCK_PROJETILE.get(), x, y, z, world);
	}
	
	/*
	 * This gets the default item that the rock will be thrown by.
	 * An example would be the snowball throwing the snowball.
	 * If you choose to use a block for this, then you will need to
	 * add .asItem() onto the end to ensure you are gettin the block item
	 */
	@Override
	protected Item getDefaultItem() {
		return ItemList.ROCK_ITEM.get().asItem();
	}
	
	/*
	 * This is a method used to spawn the actual entity in the world
	 * It uses the NetworkHooks class which is a common class used.
	 * It then gets the entity which is this.
	 */
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	/*
	 * The onImpact method is where you get to customise the projectile
	 * to fit what you want.
	 * There are infinite things that you can do in here.
	 * However, for this example I have included an example for each
	 * RayTrace types. (Entity, Block and Miss). Miss would likely not 
	 * be used as much as the others, however, you could also do anything here.
	 */
	@Override
	protected void onImpact(RayTraceResult result) {
		//This line is checking the type of RayTraceResult, in this case 
		//it will be when it hits and entity
		if(result.getType() == RayTraceResult.Type.ENTITY) {
			//This is a variable that we have set, it gets the entity from the RayTraceResult.
			//We cast it to EntityRayTraceResult, just to ensure that it is infact an entity.
			Entity entity = ((EntityRayTraceResult)result).getEntity();
			//This integer is the damage value that it gives to the entity when it is hit
			//I haven't initialized it here as I will do that below.
			int damage;
			
			//Simple if statement which checks the instance of the entity.
			//If you just wanted 1 thing here, then you could use a ternary
			//operator, however, I have left this as it makes it easier to read
			if(entity instanceof CowEntity) {
				//If it is a cow, it will damage it by 5
				damage = 5;
			} else {
				//Any other entity will be attacked by 0;
				damage = 0;
			}
			//This line will actually attack the entity. gettin the entity, attacking it, getting the Damage
			//Source from the thrown, it takes in the entity which is this, then the attacker which is, the
			//thrower of this, then the damage as a float
			entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)damage);
			
			//After hitting the entity, we need to remove it from the world, otherwise it will keep flying the air.
			//We first use if to check if the world is the server by using isRemote.
			if(!world.isRemote) {
				//If it is the server then it will remove this (the entity) from the world.
				this.remove();
				
				//I have put this here as I want to remove it from the world after it has hit the entity.
				//if you wanted it to go through entities then you could remove this and put it later.
			}
		}
		
		//Just like before this checks the result and if it hits a block this code will run
		if(result.getType() == RayTraceResult.Type.BLOCK) {
			//Now we get the BlockRayTraceResult from the result
			//Casting it to the BlockRayTraceResult.
			BlockRayTraceResult blockRTR = (BlockRayTraceResult)result;
			
			//Just like a lot of things, this is optional to your projectile
			//This checks the direction that the entity has hit the block.
			//I have checked to see if it hits the top of the block
			if(blockRTR.getFace() == Direction.UP) {
				//Then I have added a small check here to only allow something to happen when it
				//Hits a grass block
				if(world.getBlockState(blockRTR.getPos()) == Blocks.GRASS_BLOCK.getDefaultState()) {
					//This gets the world, and then sets the blockstate of the position of the entity
					//and the blockstate
					world.setBlockState(this.getPosition(), Blocks.STONE.getDefaultState());
				}
				
				if(!world.isRemote) {
					this.remove();
				}
			}
		}
		
		//Now I am checking if it has missed a block or entity, the only instance I can think this might occur
		//Would be if it went into the void
		if(result.getType() == RayTraceResult.Type.MISS) {
			//I am just going to be playing a sound if it misses something, which would likely not happen
			world.playSound((PlayerEntity)this.getThrower(), this.getPosition(), SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.AMBIENT, 1.0F, 1.0F);
			if(!world.isRemote) {
				this.remove();
			}
		}
		//And just incase non of these are true, I am removing it from the world.
		if(!world.isRemote) {
			this.remove();
		}
		
	}
	
	
}


















