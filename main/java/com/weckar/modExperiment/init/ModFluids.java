package com.weckar.modExperiment.init;

import com.weckar.modExperiment.block.BlockBase;
import com.weckar.modExperiment.block.FluidBase;
import com.weckar.modExperiment.block.FluidGodsBlood;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModFluids {
	public static FluidBase godsBlood;
	
	public static void init(){
		godsBlood = FluidGodsBlood.init();
		GameRegistry.registerBlock(godsBlood, godsBlood.getName());
	}
}
