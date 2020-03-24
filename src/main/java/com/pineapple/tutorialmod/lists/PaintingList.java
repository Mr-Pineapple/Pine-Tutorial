package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PaintingList {

	public static final DeferredRegister<PaintingType> PAINTING_TYPES = new DeferredRegister<PaintingType>(ForgeRegistries.PAINTING_TYPES, Main.MOD_ID);
	
	public static final RegistryObject<PaintingType> CIRCLE = PAINTING_TYPES.register("circle", () -> new PaintingType(64,48));
	
}
