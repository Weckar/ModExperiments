package com.weckar.modExperiment.item;

import com.weckar.modExperiment.creativetab.CreativeTab;
import com.weckar.modExperiment.reference.ModRef;
import com.weckar.modExperiment.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item {
	private String name;
	private String iconName;

	public ItemBase() {
		super();
		this.setCreativeTab(CreativeTab.TAB);
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", ModRef.MOD_ID.toLowerCase() + ":",
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", ModRef.MOD_ID.toLowerCase() + ":",
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModRef.MOD_ID.toLowerCase() + ":" + iconName);
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	protected void setName(String name) {
		this.name = "I" + name;
		this.setUnlocalizedName(this.name);
	}

	public String getName() {
		return name;
	}

	protected void setIconName(String name) {
		this.iconName = name;
	}
}
