package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.objects.items.RockItem;
import com.pineapple.tutorialmod.objects.items.TooltipItem;
import com.pineapple.tutorialmod.objects.items.TutorialFuelItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList
{
	/* Create a new Deferred Registry for all our items to register to
	 * This is called in our Main class and added to the EventBus, which saves us making each class one
	 * Alternatively you could use registry events. Though it doesn't make a massive difference.
	 * Deferred Registries are a new and more efficient way of registering lots of things.
	 * 
	 * For updating to 1.16 you'll need to create method from the Deferred Register class, instead of calling on the 
	 * constructor. (DeferredRegister.create(), instead of a new DeferredRegister()).
	 */
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<Item> TUTORIAL_ITEM = ITEMS.register("tutorial_item", () -> new Item(new Item.Properties().group(Main.TUTORIAL_TAB)));
	
	public static final RegistryObject<Item> PEPPERS = ITEMS.register("pepper_crop", () -> new BlockItem(BlockList.PEPPER_BUSH.get(), new Item.Properties().group(Main.TUTORIAL_TAB).food(FoodList.PEPPER)));
	public static final RegistryObject<Item> FROSTBERRY = ITEMS.register("frostberry_bush", () -> new BlockItem(BlockList.FROSTBERRY_BUSH.get(), new Item.Properties().group(Main.TUTORIAL_TAB).food(FoodList.ICE_BERRY)));
	
	public static final RegistryObject<Item> TUTORIAL_FUEL = ITEMS.register("tutorial_fuel", () -> new TutorialFuelItem(new Item.Properties().group(Main.TUTORIAL_TAB).maxStackSize(1)));
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().group(Main.TUTORIAL_TAB)));

	public static final RegistryObject<Item> TOOLTIP_ITEM = ITEMS.register("tooltip_item", TooltipItem::new);
	
	public static final RegistryObject<Item> ROCK_ITEM = ITEMS.register("rock", () -> new RockItem(new Item.Properties().group(Main.TUTORIAL_TAB)));
	
	/* 
	 * In the music disc tutorial I created a new class which extended the vanilla music disc
	 * However, if you are a java nerd then you know that this really isn't needed, and we can
	 * "extend" it here without the need of a separate class. 
	 */
	
	public static final RegistryObject<Item> TUTORIAL_MUSIC_DISC = ITEMS.register("tutorial_disc", () -> new MusicDiscItem(1, SoundList.TUTORIAL_DISC_LAZY.get(), new Item.Properties().maxStackSize(1).group(Main.TUTORIAL_TAB)) {} );
	
}
