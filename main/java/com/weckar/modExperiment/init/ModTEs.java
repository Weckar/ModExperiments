package com.weckar.modExperiment.init;

import com.weckar.modExperiment.tileentity.TEBase;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTEs {
	public static void init() {
	}

	private static void register(Class<? extends TEBase> cl){
		try {
			TEBase te = cl.newInstance();
			GameRegistry.registerTileEntity(cl, te.getName());
			if (te.getRenderer()!=null){
				ClientRegistry.bindTileEntitySpecialRenderer(cl, te.getRenderer());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
