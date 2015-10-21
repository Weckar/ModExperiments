package com.weckar.modExperiment.renderer;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

public abstract class RendererBase extends TileEntitySpecialRenderer{
	private Tessellator tess = Tessellator.instance;

	public RendererBase(){
	}

	@Override
	public abstract void renderTileEntityAt(TileEntity te, double x, double y, double z, float f);

	private void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, Color color, float thick){
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4d(color.getRed()/255.0, color.getGreen()/255.0, color.getBlue()/255.0, 0.5);
		GL11.glLineWidth(thick);
		tess.startDrawing(2);
		tess.addVertex(x1, y1, z1);
		tess.addVertex(x2, y2, z2);
		tess.draw();
		GL11.glPopMatrix();
	}
	
	private void drawOnTop(TileEntity te, ItemStack item, float x, float y, float z){
		EntityItem entItem = new EntityItem(te.getWorldObj(),0,0,0,item);
		GL11.glPushMatrix();
		entItem.hoverStart = 0.0F;
		RenderItem.renderInFrame = true;
		GL11.glTranslatef(x + 0.5F, y + 1.02F, z + 0.3F);
		GL11.glRotatef(180, 0, 1, 1);
		RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		RenderItem.renderInFrame = false;
		GL11.glPopMatrix();
	}

	private void renderBlock(float x, float y, float z, Block block, int meta){
		IIcon[] icons = new IIcon[6];
		for(int i = 0; i<6; i++){
			block.getIcon(i,meta);
		}
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		tess.startDrawingQuads();
		//top
		tess.addVertexWithUV(1,1,1,icons[1].getMaxU(),icons[1].getMaxV());
		tess.addVertexWithUV(1,0,1,icons[1].getMaxU(),icons[1].getMinV());
		tess.addVertexWithUV(0,0,1,icons[1].getMinU(),icons[1].getMinV());
		tess.addVertexWithUV(0,1,1,icons[1].getMinU(),icons[1].getMaxV());
		//bottom
		tess.addVertexWithUV(1,1,0,icons[0].getMaxU(),icons[0].getMaxV());
		tess.addVertexWithUV(1,0,0,icons[0].getMaxU(),icons[0].getMinV());
		tess.addVertexWithUV(0,0,0,icons[0].getMinU(),icons[0].getMinV());
		tess.addVertexWithUV(0,1,0,icons[0].getMinU(),icons[0].getMaxV());
		//south
		tess.addVertexWithUV(1,1,1,icons[2].getMaxU(),icons[2].getMaxV());
		tess.addVertexWithUV(1,1,0,icons[2].getMaxU(),icons[2].getMinV());
		tess.addVertexWithUV(0,1,0,icons[2].getMinU(),icons[2].getMinV());
		tess.addVertexWithUV(0,1,1,icons[2].getMinU(),icons[2].getMaxV());
		//north
		tess.addVertexWithUV(1,0,1,icons[3].getMaxU(),icons[3].getMaxV());
		tess.addVertexWithUV(1,0,0,icons[3].getMaxU(),icons[3].getMinV());
		tess.addVertexWithUV(0,0,0,icons[3].getMinU(),icons[3].getMinV());
		tess.addVertexWithUV(0,0,1,icons[3].getMinU(),icons[3].getMaxV());
		//east
		tess.addVertexWithUV(1,1,1,icons[5].getMaxU(),icons[5].getMaxV());
		tess.addVertexWithUV(1,1,0,icons[5].getMaxU(),icons[5].getMinV());
		tess.addVertexWithUV(1,0,0,icons[5].getMinU(),icons[5].getMinV());
		tess.addVertexWithUV(1,0,1,icons[5].getMinU(),icons[5].getMaxV());
		//west
		tess.addVertexWithUV(0,1,1,icons[4].getMaxU(),icons[4].getMaxV());
		tess.addVertexWithUV(0,1,0,icons[4].getMaxU(),icons[4].getMinV());
		tess.addVertexWithUV(0,0,0,icons[4].getMinU(),icons[4].getMinV());
		tess.addVertexWithUV(0,0,1,icons[4].getMinU(),icons[4].getMaxV());
		tess.draw();
		GL11.glPopMatrix();
	}
}
