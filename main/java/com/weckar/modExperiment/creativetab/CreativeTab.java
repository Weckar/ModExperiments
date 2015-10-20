package com.weckar.modExperiment.creativetab;

import com.weckar.modExperiment.init.ModItems;
import com.weckar.modExperiment.reference.ModRef;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab {

	public static final CreativeTabs TAB = new CreativeTabs(ModRef.MOD_ID.toLowerCase()) {
		@Override
		public Item getTabIconItem() {
			return ModItems.test;
		}
	};
}
