package com.weckar.modExperiment.entity;

import com.weckar.modExperiment.utility.LogHelper;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UniqueEntityItem extends EntityItem{

	public UniqueEntityItem(World world, double x, double y, double z, ItemStack itemStack) {
		super(world, x, y, z, itemStack);
		delayBeforeCanPickup=50;
		lifespan = Integer.MAX_VALUE;
	}

	//Covers general damage and explosions
	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	//Covers other cases
	@Override
	public void onEntityUpdate(){
		//Covers lava and fire
		if(isBurning()){
			entityDropItem(getEntityItem(),1);
			setDead();
		}
	}

}
