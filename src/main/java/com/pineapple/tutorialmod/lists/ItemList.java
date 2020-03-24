package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.objects.items.TutorialFuelItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemList
{
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	public static final RegistryObject<Item> TUTORIAL_ITEM = ITEMS.register("tutorial_item", () -> new Item(new Item.Properties().group(Main.TUTORIAL_TAB)));
	
	public static final RegistryObject<Item> PEPPERS = ITEMS.register("pepper_crop", () -> new BlockItem(BlockList.PEPPER_BUSH.get(), new Item.Properties().group(Main.TUTORIAL_TAB).food(FoodList.PEPPER)));

	public static final RegistryObject<Item> TUTORIAL_FUEL = ITEMS.register("tutorial_fuel", () -> new TutorialFuelItem(new Item.Properties().group(Main.TUTORIAL_TAB).maxStackSize(1)));






}
