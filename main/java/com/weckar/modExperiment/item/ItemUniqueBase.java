package com.weckar.modExperiment.item;

import java.util.List;

import com.weckar.modExperiment.entity.UniqueEntityItem;
import com.weckar.modExperiment.handler.UniqueItemHandler;
import com.weckar.modExperiment.utility.LogHelper;

import javafx.beans.property.adapter.ReadOnlyJavaBeanStringProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public abstract class ItemUniqueBase extends ItemBase {
	public ItemUniqueBase() {
		super();
		this.setMaxStackSize(1);
	}

	//SPAWN A MUCH HARDER TO DESTROY CUSTOM ENTITYITEM
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemStack) {
		Entity resp = new UniqueEntityItem(world,location.posX,location.posY,location.posZ,itemStack);
		resp.setVelocity(location.motionX,location.motionY,location.motionZ);
		return resp;
	}

	@Override
	public boolean hasCustomEntity(ItemStack itemStack){
		return true;
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity ent, int num,	boolean bool) {
		//THERE CAN ONLY BE ONE
		if (!world.isRemote && (!itemStack.hasTagCompound() || itemStack.getTagCompound().getBoolean("Unique")==false)){
			LogHelper.info("Registering Unique "+itemStack.getItem().getUnlocalizedName());
			if(!UniqueItemHandler.getInstance().checkUnique(itemStack,true)){
				if (ent instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer)ent;
					player.addChatMessage(new ChatComponentText("Banish the Fake!"));
					player.inventory.setInventorySlotContents(num,null);
				}
			}
			else{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean("Unique",true);
				itemStack.setTagCompound(nbt);
			}
		}
		//KILL OTHER ENTITIES WHO WOULD USE ME
		if (!(ent instanceof EntityPlayer)){
			ent.entityDropItem(itemStack,1);
			ent.setDead();
		}
	}
}
