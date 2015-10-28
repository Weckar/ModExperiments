package com.weckar.modExperiment.init;

import com.weckar.modExperiment.biome.BiomeMournland;

import net.minecraftforge.common.BiomeManager;

public class ModBiomes {
	public static BiomeMournland biomeMourn;
	public static void init(){
		biomeMourn = new BiomeMournland(100);
	}
}
