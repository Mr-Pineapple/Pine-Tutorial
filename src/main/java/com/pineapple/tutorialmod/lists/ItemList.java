package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.objects.items.TooltipItem;
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
	public static final RegistryObject<Item> FROSTBERRY = ITEMS.register("frostberry_bush", () -> new BlockItem(BlockList.FROSTBERRY_BUSH.get(), new Item.Properties().group(Main.TUTORIAL_TAB).food(FoodList.ICE_BERRY)));
	
	public static final RegistryObject<Item> TUTORIAL_FUEL = ITEMS.register("tutorial_fuel", () -> new TutorialFuelItem(new Item.Properties().group(Main.TUTORIAL_TAB).maxStackSize(1)));
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().group(Main.TUTORIAL_TAB)));

	public static final RegistryObject<Item> TOOLTIP_ITEM = ITEMS.register("tooltip_item", TooltipItem::new);
	
	
}
