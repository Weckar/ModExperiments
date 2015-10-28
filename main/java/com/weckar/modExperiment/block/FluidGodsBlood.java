package com.weckar.modExperiment.block;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidGodsBlood extends FluidBase {
	private FluidGodsBlood(Fluid fluid) {
		super(fluid);
		this.setName("GodsBlood");
		this.setIconName("godsBlood");
		setLightLevel(1.0F);
	}
	
	public static FluidGodsBlood init(){
		Fluid fluid = new Fluid("godsBlood");
		FluidRegistry.registerFluid(fluid);
		return new FluidGodsBlood(fluid);
	}
}
