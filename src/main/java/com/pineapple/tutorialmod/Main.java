package com.pineapple.tutorialmod;

import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pineapple.tutorialmod.lists.BiomeList;
import com.pineapple.tutorialmod.lists.BlockList;
import com.pineapple.tutorialmod.lists.EntityList;
import com.pineapple.tutorialmod.lists.ItemList;
import com.pineapple.tutorialmod.lists.PaintingList;
import com.pineapple.tutorialmod.lists.ParticleList;
import com.pineapple.tutorialmod.lists.PotionList;
import com.pineapple.tutorialmod.lists.SoundList;
import com.pineapple.tutorialmod.world.PearlWorldType;
import com.pineapple.tutorialmod.world.gen.TutorialGeneration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("deprecation")
@Mod(Main.MOD_ID)
@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class Main
{
	/*
	 * This is our Main class. And without this nothing in our mod would work!
	 * It's like the bread of a sandwich, you need it otherwise you don't have a sandwich.
	 * 
	 * We call everything here and add it to the EventBus so it can be registered to the game
	 * We do lots of things in this class, which you could split of into separate classes and
	 * call seperately (better for bigger mods), but for this we'll just call everything here.
	 */
	
	
	
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
		
		/* Register all of our deferred registries from our list/init classes, which get added to the IEventBus */
		ParticleList.PARTICLES.register(modEventBus);
		SoundList.SOUNDS.register(modEventBus);
		ItemList.ITEMS.register(modEventBus);
		BlockList.BLOCKS.register(modEventBus);
		BlockList.NO_ITEM_BLOCK.register(modEventBus);
		PotionList.EFFECTS.register(modEventBus);
		PotionList.POTIONS.register(modEventBus);
		BiomeList.BIOMES.register(modEventBus);
		PaintingList.PAINTING_TYPES.register(modEventBus);
		EntityList.ENTITIES.register(modEventBus);
	}
	
	
	/* In here we feed everything from our BLOCKS deferred register to make BlockItems for us.
	 * Instead of using the filter, if we wanted special properties, we can just use the NO_ITEM_BLOCK
	 */
	
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
	
	/* A registry event to register all biomes into the game */
	@SubscribeEvent
	public static void spawnBiomes(final RegistryEvent.Register<Biome> event) {
		BiomeList.registerBiomes();
	}
	
	/* The FMLCommonSetupEvent (FML - Forge Mod Loader) */
	private void setup(final FMLCommonSetupEvent event)
	{
		/* In the Brewing tutorial I couldn't find this method, so instead I reflected the one that vanilla uses - Use this instead */
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.fromItems(ItemList.PEPPERS.get()), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),  PotionList.MORE_HEALTH_POTION.get()));
		
		/*
		 * Here we call the public generate method from our Generation class.
		 * You may notice that we don't call it directly, and that is because
		 * Minecraft is not thread safe, so we can't add non-thread safe
		 * variables anywhere we feel like.
		 * 
		 * Even though this class is deprecated, it is perfectly fine to use
		 * deprecated just means that there is a new sytem in the works, but this
		 * still works completely fine!
		 */
		DeferredWorkQueue.runLater(TutorialGeneration::generate);
	}
	
	
	/*
	 * ClientSetup, this registers things we want on the client side that the
	 * server doesn't really care about, like rendering layers and other stuff.
	 */
	private void clientSetup(final FMLClientSetupEvent event)
	{	
		RenderTypeLookup.setRenderLayer(BlockList.PEPPER_BUSH.get(), RenderType.getCutout());  //getCutout()
		RenderTypeLookup.setRenderLayer(BlockList.TUTORIAL_DOOR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockList.FROSTBERRY_BUSH.get(), RenderType.getCutout());
		
		//Just call the method from below, and get the supplier from the event
		registerEntityModels(event.getMinecraftSupplier());
	}
	
	
	/*
	 * This is a helper method we are using just to save a little space in clientSetup
	 * It takes in the Minecraft Supplier as a parameter, which can be called from the event
	 */
	private void registerEntityModels(Supplier<Minecraft> minecraft) {
		//Just a variable I have set incase I want to add more entites, which will make the code more efficient
		ItemRenderer renderer = minecraft.get().getItemRenderer();
		
		/*
		 * We now need to render the entity on the client using the Rendering Registry
		 * We take in the Entity then the RenderType. I use a lambda function here for ease.
		 * Most projectiles will use SpriteRenderers for their rendering, using the same texture as the item
		 * It taes in the manager from the lambda and the variable above for the item renderer.
		 */
		RenderingRegistry.registerEntityRenderingHandler(EntityList.ROCK_PROJETILE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
	}
	
	
	/*
	 * This is an inner class, you don't need to do this but I have in this case
	 * We are creating an ItemGroup (previously known as creative tabs)
	 */	
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
