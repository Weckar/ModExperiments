package com.weckar.modExperiment.handler;

import java.util.ArrayList;
import java.util.List;

import com.weckar.modExperiment.utility.LogHelper;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.gen.structure.MapGenStructureData;
import net.minecraft.world.storage.MapStorage;

public class UniqueItemHandler extends WorldSavedData {
	public List<ItemStack> itemUnique = new ArrayList<ItemStack>();
	private static UniqueItemHandler INSTANCE;

	public UniqueItemHandler(String string) {
		super(string);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		itemUnique = new ArrayList<ItemStack>();
		NBTTagList list = nbt.getTagList("UniqueItems", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			itemUnique.add(ItemStack.loadItemStackFromNBT(stackTag));
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < itemUnique.size(); ++i) {
			NBTTagCompound stackTag = new NBTTagCompound();
			itemUnique.get(i).writeToNBT(stackTag);
			list.appendTag(stackTag);
		}
		nbt.setTag("UniqueItems", list);
	}

	public boolean checkUnique(ItemStack item, boolean doAdd){
		markDirty();
		for(ItemStack iS : itemUnique){
			if (iS.getItem().equals(item.getItem())) return false;
		}
		if (doAdd) itemUnique.add(item);
		return true;
	}
	
	public static UniqueItemHandler getInstance() {
		return INSTANCE;
	}
	public static void setInstance(World world) {
		String tagname = "MEUNIQUE";
		LogHelper.info("<UIH> LOADING...");
		UniqueItemHandler result = (UniqueItemHandler)world.loadItemData(UniqueItemHandler.class, tagname);
		if (result == null) {
			LogHelper.info("<UIH> COULD NOT BE LOADED!");
			result = new UniqueItemHandler(tagname);
			world.setItemData(tagname, result);
		}
		INSTANCE = result;
	}
	public static void resetInstance() {
		LogHelper.info("<UIH> UNLOADING...");
		INSTANCE.markDirty();
		INSTANCE = null;
	}

	public void deleteUnique(ItemStack item){
		markDirty();
		itemUnique.remove(item);
		item.getTagCompound().removeTag("Unique");
	}
}
