package com.weckar.modExperiment.recipe;

import com.weckar.modExperiment.handler.UniqueItemHandler;
import com.weckar.modExperiment.item.ItemUniqueBase;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class UniqueShapelessRecipe extends ShapelessOreRecipe{
	public UniqueShapelessRecipe(ItemStack result, Object[] recipe) {
		super(result, recipe);
	}
	
	public UniqueShapelessRecipe(Item result, Object[] recipe) {
		super(result, recipe);
	}
	
	public UniqueShapelessRecipe(Block result, Object[] recipe) {
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
