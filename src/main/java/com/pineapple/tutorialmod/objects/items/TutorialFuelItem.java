package com.pineapple.tutorialmod.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TutorialFuelItem extends Item {
	
	public TutorialFuelItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 200;
	}

}
