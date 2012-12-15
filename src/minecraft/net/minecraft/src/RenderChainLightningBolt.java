package net.minecraft.src;

import java.util.Random;
import org.lwjgl.opengl.GL11;

public class RenderChainLightningBolt extends Render
{
	
    public void doRenderChainLightningBolt(EntityChainLightningBolt entityChainLightningBolt, double par2, double par4, double par6, float par8, float par9)
    {
		Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
		
		Vector3 line1;
		Vector3 line2;
	
		System.out.println(entityChainLightningBolt.lineVectors);
	
		for(int i = 0; i < entityChainLightningBolt.lineVectors.size(); i += 2)
		{
			line1 = entityChainLightningBolt.lineVectors.get(i);
			line2 = entityChainLightningBolt.lineVectors.get(i + 1);

			tessellator.addVertex(line1.x, line1.y, line1.z);
			tessellator.addVertex(line2.x, line2.y, line2.z);
		}
		
		tessellator.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderChainLightningBolt((EntityChainLightningBolt)par1Entity, par2, par4, par6, par8, par9);
    }
}
