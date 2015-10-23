package com.weckar.modExperiment.init;

import com.weckar.modExperiment.recipe.UniqueShapedRecipe;
import com.weckar.modExperiment.recipe.UniqueShapelessRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

	public static void init(){
		//Example
		addShapeless(ModItems.test,Items.stick,5,Blocks.log,2);
	}

	private static void addShaped(Object...objects){
		Object[] ingredients = new Object[objects.length-1];
		for(int i=1; i<objects.length;i++){
			ingredients[i-1]=objects[i];
		}
		ItemStack output;
		if (objects[0] instanceof ItemStack) output = (ItemStack)objects[0];
		else if (objects[0] instanceof Item) output = new ItemStack((Item)objects[0]);
		else output = new ItemStack((Block)objects[0]);
		GameRegistry.addRecipe(new UniqueShapedRecipe(output, ingredients));
	}
	private static void addShapeless(Object...objects){
		int count = 0;
		for(int i=1; i<objects.length;i++){
			if (objects[i] instanceof Integer){
				count+=(Integer)objects[i];
			}
		}
		Object[] ingredients = new Object[count];
		count = 0;
		for(int i=1; i<objects.length;i++){
			if (objects[i] instanceof Integer){
				for(int j=0; j<(Integer)objects[i];j++){
					ingredients[count]=objects[i-1];
					count++;
				}
			}
		}
		ItemStack output;
		if (objects[0] instanceof ItemStack) output = (ItemStack)objects[0];
		else if (objects[0] instanceof Item) output = new ItemStack((Item)objects[0]);
		else output = new ItemStack((Block)objects[0]);
		GameRegistry.addRecipe(new UniqueShapelessRecipe(output,ingredients));
	}
}
