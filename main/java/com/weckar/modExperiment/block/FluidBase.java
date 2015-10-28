package com.weckar.modExperiment.block;

import com.weckar.modExperiment.creativetab.CreativeTab;
import com.weckar.modExperiment.reference.ModRef;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public abstract class FluidBase extends BlockFluidClassic{
	private String name;
	private String [] iconName;
	private IIcon [] icons;

	public FluidBase(Fluid fluid, Material material) {
		super(fluid, material);
		this.setCreativeTab(CreativeTab.TAB);
	}

	public FluidBase(Fluid fluid) {
		this(fluid,Material.water);
	}
	
	public static FluidBase init(){ return null;}

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", ModRef.MOD_ID.toLowerCase() + ":",
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[iconName.length];
		for(int i = 0; i<iconName.length;i++){
			icons[i] = iconRegister.registerIcon(ModRef.MOD_ID.toLowerCase() + ":" + iconName[i]);
		}
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	protected void setName(String name) {
		this.name = "F" + name;
		this.setBlockName(this.name);
	}

	public String getName() {
		return name;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[side%icons.length];
	}
	
	protected void setIconName(String... name) {
		this.iconName = name;
	}

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
    
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
}
