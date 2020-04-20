package com.pineapple.tutorialmod.objects.items;

import java.util.List;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.util.KeyboardUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TooltipItem extends Item {

	public TooltipItem() {
		super(new Item.Properties().group(Main.TUTORIAL_TAB));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(KeyboardUtil.isHoldingShift()) {
			tooltip.add(new StringTextComponent("\u00A7b" + "This is our item" + "\u00A7b"));
		} else {
			tooltip.add(new StringTextComponent("Hold" + "\u00A76" + "Shift" + "For More Info"));
		}
	}
	
	

}
