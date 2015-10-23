package com.weckar.modExperiment.recipe;

import com.weckar.modExperiment.handler.UniqueItemHandler;
import com.weckar.modExperiment.item.ItemUniqueBase;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class UniqueShapedRecipe extends ShapedOreRecipe{
	public UniqueShapedRecipe(ItemStack result, Object[] recipe) {
		super(result, recipe);
	}
	
	public UniqueShapedRecipe(Item result, Object[] recipe) {
		super(result, recipe);
	}
	
	public UniqueShapedRecipe(Block result, Object[] recipe) {
		super(result, recipe);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		ItemStack result =  super.getCraftingResult(p_77572_1_);
		if (result.getItem() instanceof ItemUniqueBase){
			if(!UniqueItemHandler.getInstance().checkUnique(result,false)){
				result = null;
			}
		}
		return result;
	}
}
