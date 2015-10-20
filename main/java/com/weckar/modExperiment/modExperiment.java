package com.weckar.modExperiment;

import static com.weckar.modExperiment.reference.ModRef.*;

import com.weckar.modExperiment.init.ModBlocks;
import com.weckar.modExperiment.init.ModItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public class modExperiment {
	@Mod.Instance(MOD_ID)
	public static modExperiment instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
