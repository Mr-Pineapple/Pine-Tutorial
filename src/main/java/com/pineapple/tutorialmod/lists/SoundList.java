package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundList {

	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS,
			Main.MOD_ID);

	public static final RegistryObject<SoundEvent> TUTORIAL_SOUND = SOUNDS.register("item.tutorial_sound",
			() -> new SoundEvent(new ResourceLocation(Main.MOD_ID, "item.tutorial_sound")));

	public static final Lazy<SoundEvent> TUTORIAL_DISC_LAZY = Lazy
			.of(() -> new SoundEvent(new ResourceLocation(Main.MOD_ID, "item.tutorial_disc")));
	
	public static final RegistryObject<SoundEvent> TUTORIAL_DISC = SOUNDS.register("item.tutorial_disc.disc", TUTORIAL_DISC_LAZY);

}
