package com.weckar.modExperiment.utility;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper {
	/** Checks whether item2 in the OreDictionary matches any in item1 */
	public static boolean matches(Item item1, Item item2){
		for(Integer oreID1 : OreDictionary.getOreIDs(new ItemStack(item1))){
			for(Integer oreID2 : OreDictionary.getOreIDs(new ItemStack(item2))){
				if (oreID1==oreID2) return true;
			}
		}
		return false;
	}
	
	/** Registers item2 in the OreDictionary as if it were exactly item1 */
	public void regAs(Item item1, Item item2){
		for(Integer oreID : OreDictionary.getOreIDs(new ItemStack(item1))){
			OreDictionary.registerOre(OreDictionary.getOreName(oreID),item2);
		}
	}
}
