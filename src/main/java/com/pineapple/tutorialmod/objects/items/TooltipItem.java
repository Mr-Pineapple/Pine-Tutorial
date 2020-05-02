package com.pineapple.tutorialmod.objects.items;

import java.util.List;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.lists.SoundList;
import com.pineapple.tutorialmod.util.KeyboardUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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
			tooltip.add(new StringTextComponent("\u00A78" + "Hold " + "\u00A78" +  "\u00A76" + "Shift" + "\u00A76" + "\u00A78" + " For More Info" + "\u00A78"));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		worldIn.playSound(playerIn, new BlockPos(playerIn), SoundList.TUTORIAL_SOUND.get(), SoundCategory.VOICE, 1.0f, 1.0f);
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
	}
}
