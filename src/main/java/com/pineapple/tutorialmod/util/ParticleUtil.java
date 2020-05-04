package com.pineapple.tutorialmod.util;

import com.pineapple.tutorialmod.Main;
import com.pineapple.tutorialmod.lists.ParticleList;
import com.pineapple.tutorialmod.objects.particles.OrangeParticle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class ParticleUtil {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticles(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(ParticleList.ORANGE_PARTICLE.get(), OrangeParticle.Factory::new);
	}

}
