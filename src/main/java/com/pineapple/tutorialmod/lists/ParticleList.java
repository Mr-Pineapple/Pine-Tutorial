package com.pineapple.tutorialmod.lists;

import com.pineapple.tutorialmod.Main;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleList {
	
	public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Main.MOD_ID);
	
	public static final RegistryObject<BasicParticleType> ORANGE_PARTICLE = PARTICLES.register("orange_particle", () -> new BasicParticleType(true));
	
	

}
