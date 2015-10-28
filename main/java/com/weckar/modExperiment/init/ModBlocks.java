package com.weckar.modExperiment.init;

import java.util.HashSet;

import com.weckar.modExperiment.block.*;
import com.weckar.modExperiment.reference.ModRef;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(ModRef.MOD_ID)
public class ModBlocks {
	private static HashSet<BlockBase> preReg = new HashSet<BlockBase>();
	//Blocks go here
	public static final BlockBase test = register(BlockTest.class);
	public static final BlockBase mournSand = register(BlockMournSand.class);
	////
	public static void init() {
		for (BlockBase block : preReg) {
			GameRegistry.registerBlock(block, block.getName());
		}
		preReg.clear();
		preReg = null;
	}
	private static BlockBase register(Class cl) {
		BlockBase block = null;
		try {
			block = (BlockBase) cl.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		preReg.add(block);
		return block;
	}
}
