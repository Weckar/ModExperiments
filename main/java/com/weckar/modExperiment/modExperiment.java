package com.weckar.modExperiment;

import static com.weckar.modExperiment.reference.ModRef.*;

import com.weckar.modExperiment.handler.EventHandler;
import com.weckar.modExperiment.handler.UniqueItemHandler;
import com.weckar.modExperiment.init.*;
import com.weckar.modExperiment.worldgen.WorldGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public class modExperiment {
	@Mod.Instance(MOD_ID)
	public static modExperiment instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
		new EventHandler();
		ModTEs.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.init();
		ModBiomes.init();
		GameRegistry.registerWorldGenerator(new WorldGenerator(),1000000);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
