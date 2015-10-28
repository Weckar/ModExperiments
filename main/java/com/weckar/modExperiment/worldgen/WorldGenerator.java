package com.weckar.modExperiment.worldgen;

import java.util.Random;

import com.weckar.modExperiment.init.ModBiomes;
import com.weckar.modExperiment.init.ModBlocks;
import com.weckar.modExperiment.init.ModFluids;
import com.weckar.modExperiment.utility.LogHelper;

import cpw.mods.fml.common.IWorldGenerator;
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
		//Generate GodsBlood
		if(random.nextInt(40)==0){//TODO make config option.
			//BASIN
			int r = random.nextInt(10)+2;
			int y = random.nextInt(20)+5+r;
			int x = chunkX*16 + random.nextInt(16);
			int z = chunkZ*16 + random.nextInt(16);
			for(int ix = x-r;ix<=x+r;ix++)
			{
				for(int iy = y-r;iy<=y+r;iy++)
				{
					for(int iz = z-r;iz<=z+r;iz++)
					{
						//Make it a sphere
						double trueR = Math.sqrt(Math.pow((x-ix),2)+Math.pow((y-iy),2)+Math.pow((z-iz),2));
						if (trueR<r){
							world.setBlock(ix,iy,iz,ModFluids.godsBlood);
						}
					}
				}
			}
			//PILLAR
			for(int iy = y;!world.isAirBlock(x,iy,z) || iy<64 ;iy++)
			{
				world.setBlock(x,iy,z,ModFluids.godsBlood);
				//TOP
				if(world.isAirBlock(x,iy+2,z)&&iy+2>=64){
					world.setBlock(x-1,iy,z-1,ModFluids.godsBlood);
					world.setBlock(x-1,iy,z,ModFluids.godsBlood);
					world.setBlock(x-1,iy,z+1,ModFluids.godsBlood);
					world.setBlock(x,iy,z-1,ModFluids.godsBlood);
					world.setBlock(x,iy,z+1,ModFluids.godsBlood);
					world.setBlock(x+1,iy,z-1,ModFluids.godsBlood);
					world.setBlock(x+1,iy,z,ModFluids.godsBlood);
					world.setBlock(x+1,iy,z+1,ModFluids.godsBlood);

					world.setBlockToAir(x-1,iy+1,z-1);
					world.setBlockToAir(x-1,iy+1,z);
					world.setBlockToAir(x-1,iy+1,z+1);
					world.setBlockToAir(x,iy+1,z-1);
					world.setBlock(x,iy+1,z,ModFluids.godsBlood);
					world.setBlockToAir(x,iy+1,z+1);
					world.setBlockToAir(x+1,iy+1,z-1);
					world.setBlockToAir(x+1,iy+1,z);
					world.setBlockToAir(x+1,iy+1,z+1);
					break;
				}
			}
		}
	}
}
