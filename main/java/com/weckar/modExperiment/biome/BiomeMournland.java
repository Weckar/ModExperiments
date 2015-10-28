package com.weckar.modExperiment.biome;

import com.weckar.modExperiment.init.ModBlocks;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class BiomeMournland extends BiomeGenBase {
	public BiomeMournland(int id){//TODO: biome ID config
		super(id);
		this.setBiomeName("Mournland");
		this.setDisableRain();
		this.setHeight(new Height(0.05F,0.05F));
		this.topBlock=ModBlocks.mournSand;
		this.fillerBlock=ModBlocks.mournSand;
		this.theBiomeDecorator.generateLakes = false;
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		BiomeManager.addBiome(BiomeType.COOL,new BiomeManager.BiomeEntry(this, 10000));
	}
}
