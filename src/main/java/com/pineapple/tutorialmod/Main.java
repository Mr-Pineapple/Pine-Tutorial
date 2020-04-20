package com.pineapple.tutorialmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pineapple.tutorialmod.lists.BiomeList;
import com.pineapple.tutorialmod.lists.BlockList;
import com.pineapple.tutorialmod.lists.ItemList;
import com.pineapple.tutorialmod.lists.PaintingList;
import com.pineapple.tutorialmod.lists.PotionList;
import com.pineapple.tutorialmod.world.PearlWorldType;
import com.pineapple.tutorialmod.world.gen.TutorialGeneration;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(Main.MOD_ID)
@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class Main
{
	public static Main instance;
	public static final String MOD_ID = "pinetutorial";
	public static final ItemGroup TUTORIAL_TAB = new Main.TutorialItemGroup("tutorial_group");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final WorldType PEARL_LAND_TYPE = new PearlWorldType();
	
	public Main()
	{
		instance = this;
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientSetup);
		
		ItemList.ITEMS.register(modEventBus);
		BlockList.BLOCKS.register(modEventBus);
		BlockList.NO_ITEM_BLOCK.register(modEventBus);
		PotionList.EFFECTS.register(modEventBus);
		PotionList.POTIONS.register(modEventBus);
		BiomeList.BIOMES.register(modEventBus);
		PaintingList.PAINTING_TYPES.register(modEventBus);
	}
	
	
	
	@SubscribeEvent
	public static void createBlockItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		BlockList.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(TUTORIAL_TAB);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
		
	}
	
	@SubscribeEvent
	public static void spawnBiomes(final RegistryEvent.Register<Biome> event) {
		BiomeList.registerBiomes();
	}
	
	
	private void setup(final FMLCommonSetupEvent event)
	{
		PotionList.addBrewingRecipes();
		TutorialGeneration.generate();
	}
	
	private void clientSetup(final FMLClientSetupEvent event)
	{
		RenderTypeLookup.setRenderLayer(BlockList.PEPPER_BUSH.get(), RenderType.getCutout());  //getCutout()
		RenderTypeLookup.setRenderLayer(BlockList.TUTORIAL_DOOR.get(), RenderType.getCutout());
	}
	
	public void onServerStarting(FMLServerStartingEvent event)
	{
	}
	
	@SubscribeEvent
	public static void loadEvent(FMLLoadCompleteEvent event) {
			
	}
	
	public static class TutorialItemGroup extends ItemGroup {

		public TutorialItemGroup(String name) {
			super(name);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemList.PEPPERS.get());
		}
		
	}
}
