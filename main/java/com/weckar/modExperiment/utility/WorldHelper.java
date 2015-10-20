package com.weckar.modExperiment.utility;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WorldHelper {
	/** Spawns an ItemStack into the world at the set coordinates.	Returns the EntityItem for modification.*/ public static EntityItem SpawnItem
	(World world, ItemStack item, double x, double y, double z){
		EntityItem ent = new EntityItem(world,x,y,z,item);
		world.spawnEntityInWorld(ent);
		return ent;
	}	
	/** Spawns an Item into the world at the set coordinates. 		Returns the EntityItem for modification.*/ public static EntityItem SpawnItem
	(World world, Item item, double x, double y, double z, int amount){
		return SpawnItem(world,new ItemStack(item,amount),x,y,z);
	}	
	/** Spawns a Block into the world at the set coordinates.		Returns the EntityItem for modification.*/ public static EntityItem SpawnItem
	(World world, Block block, double x, double y, double z, int amount){
		return SpawnItem(world,new ItemStack(block,amount),x,y,z);
	}
}
