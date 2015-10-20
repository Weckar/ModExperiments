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

public abstract class BlockBase extends Block {
	private String name;
	private String [] iconName;
	private IIcon [] icons;

	public BlockBase(Material material) {
		super(material);
		this.setCreativeTab(CreativeTab.TAB);
	}

	public BlockBase() {
		this(Material.rock);
	}

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
		this.name = "B" + name;
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
}
