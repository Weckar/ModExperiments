package com.weckar.modExperiment.init;

import java.util.HashSet;

import com.weckar.modExperiment.item.*;
import com.weckar.modExperiment.reference.ModRef;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ModRef.MOD_ID)
public class ModItems {
	private static HashSet<ItemBase> preReg = new HashSet<ItemBase>();
	//Items go here
	public static final ItemBase test = register(ItemTest.class);
	////
	public static void init() {
		for (ItemBase item : preReg) {
			GameRegistry.registerItem(item, item.getName());
		}
		preReg.clear();
		preReg = null;
	}
	private static ItemBase register(Class cl) {
		ItemBase item = null;
		try {
			item = (ItemBase) cl.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		preReg.add(item);
		return item;
	}
}
