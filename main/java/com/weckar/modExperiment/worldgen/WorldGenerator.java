package com.weckar.modExperiment.worldgen;

import java.util.Random;

import com.weckar.modExperiment.init.ModBiomes;
import com.weckar.modExperiment.init.ModBlocks;
import com.weckar.modExperiment.init.ModFluids;
import com.weckar.modExperiment.utility.LogHelper;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenerator implements IWorldGenerator 
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX*16+8,chunkZ*16+8);
		if (biome.equals(ModBiomes.biomeMourn)) 
			generateMournland(world,random,chunkX,chunkZ);
	}

	private void generateMournland(World world, Random random, int chunkX, int chunkZ) 
	{
		if(random.nextInt(100)==0){//TODO make config option.
			generateGodsBloodWell(world,random,chunkX,chunkZ);
		}
	}


	private void generateGodsBloodWell(World world, Random random, int chunkX, int chunkZ){
		//Generate GodsBlood
		int r = random.nextInt(10)+2;
		int y = random.nextInt(20)+5+r;
		int x = chunkX*16 + random.nextInt(16);
		int z = chunkZ*16 + random.nextInt(16);
		//BASIN
		for(int ix = x-r;ix<=x+r;ix++){
			for(int iy = y-r;iy<=y+r;iy++){
				for(int iz = z-r;iz<=z+r;iz++){
					//Make it a sphere
					double trueR = Math.sqrt(Math.pow((x-ix),2)+Math.pow((y-iy),2)+Math.pow((z-iz),2));
					if (trueR<r){
						world.setBlock(ix,iy,iz,ModFluids.godsBlood);
					}
				}
			}
		}
		//PILLAR
		for(int iy = y; y<128 ;iy++)
		{
			world.setBlock(x,iy,z,ModFluids.godsBlood);
			//TOP
			if(world.isAirBlock(x,iy+2,z)&&iy+2>=64){
				setBlocksInArea(world,x-1,iy,z-1,x+1,iy,z+1,ModFluids.godsBlood);
				setBlocksInArea(world,x-1,iy+1,z-1,x+1,iy+1,z+1,Blocks.air);
				world.setBlock(x,iy+1,z,ModFluids.godsBlood);
				break;
			}
		}
	}

	private void setBlocksInArea(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block block){
		for(int x=Math.min(x1,x2);x<=Math.max(x1,x2);x++){
			for(int y=Math.min(y1,y2);y<=Math.max(y1,y2);y++){
				for(int z=Math.min(z1,z2);z<=Math.max(z1,z2);z++){
					world.setBlock(x,y,z,block);
				}
			}
		}
	}
}
