package com.pineapple.tutorialmod.util;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KeyboardUtil {

	private static final long MINECRAFT_WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	/*
	 * Just a method that we made so we can easily know if the shift key is down anywhere
	 */
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingShift() {
		return InputMappings.isKeyDown(MINECRAFT_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT);
	}
}
