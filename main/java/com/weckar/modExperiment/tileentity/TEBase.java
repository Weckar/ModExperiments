package com.weckar.modExperiment.tileentity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public abstract class TEBase extends TileEntity implements IFluidTank, IInventory {
	private ItemStack [] inventory = new ItemStack[getSizeInventory()];
	private FluidStack fluid;
	
	public abstract String getName();
	public abstract TileEntitySpecialRenderer getRenderer();
	@Override
	public abstract int getSizeInventory();
	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return null;
		return this.inventory[index];
	}
	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, null);
		return stack;
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.inventory[index] = stack;
		this.markDirty();
	}
	@Override
	public String getInventoryName() {
		return null;
	}
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return Math.sqrt(Math.pow(xCoord-player.posX,2)+Math.pow(yCoord-player.posY,2)+Math.pow(zCoord-player.posZ,2))<= 64;
	}
	@Override
	public void openInventory() {
	}
	@Override
	public void closeInventory() {
	}
	@Override
	public abstract boolean isItemValidForSlot(int index, ItemStack item);
	
	@Override
	public FluidStack getFluid() {
		return fluid;
	}
	@Override
	public int getFluidAmount() {
		return fluid.amount;
	}
	@Override
	public abstract int getCapacity();
	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(fluid, getCapacity());
	}
	@Override
	public int fill(FluidStack resource, boolean doFill) {
		int filled = 0;
		if (fluid!=null && fluid.getFluid().equals(resource.getFluid())){
			filled = fluid.amount-Math.min(fluid.amount+resource.amount,getCapacity());
			if (doFill) fluid.amount+=filled;
		}
		else if (fluid == null){
			filled = Math.min(resource.amount,getCapacity());
			if (doFill) {
				fluid = resource;
				fluid.amount = filled;
			}
		}
		return filled;
	}
	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		FluidStack drainedF = null;
		int drainedA = 0;
		if (fluid!=null){
			drainedA = Math.min(fluid.amount,maxDrain);
			drainedF = new FluidStack(fluid.getFluid(),drainedA);
			if (doDrain){
				fluid.amount-=drainedA;
				if (fluid.amount==0) fluid=null;
			}
		}
		return drainedF;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);
	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
	    }
	    fluid.loadFluidStackFromNBT(nbt);
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) {
	        if (this.getStackInSlot(i) != null) {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);
	    fluid.writeToNBT(nbt);
	}
	@Override
	public void updateEntity() {
		if (this.getWorldObj().isRemote){
			updateClient();
		}
		else{
			updateServer();
		}
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty();
	}
	public abstract void updateClient();
	public abstract void updateServer();
	@Override
	public Packet getDescriptionPacket() {
	       NBTTagCompound syncData = new NBTTagCompound();
	       this.writeToNBT(syncData);
	       return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}
}
