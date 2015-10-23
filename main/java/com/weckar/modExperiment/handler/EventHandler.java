package com.weckar.modExperiment.handler;

import com.weckar.modExperiment.item.ItemUniqueBase;
import com.weckar.modExperiment.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;

public class EventHandler {
	
	public EventHandler(){
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	//Events powering WorldDataHandler
	private World savedWorld;
	@SubscribeEvent(priority = EventPriority.NORMAL)
    public void onWorldLoad(WorldEvent.Load e) {
		if (!e.world.isRemote && WorldDataHandler.getInstance()==null){
			savedWorld=e.world;
			WorldDataHandler.setInstance(e.world);
		}
    }
	@SubscribeEvent(priority = EventPriority.NORMAL)
    public void onWorldUnload(WorldEvent.Unload e) {
		if (!e.world.isRemote && e.world.equals(savedWorld)){
			WorldDataHandler.resetInstance();
		}
    }
}
