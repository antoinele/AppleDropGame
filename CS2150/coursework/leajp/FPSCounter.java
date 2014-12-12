package coursework.leajp;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.util.ResourceLoader;

import fcampos.rawengine3D.font.BitmapFont;

public class FPSCounter {
	private final int SAMPLE_LENGTH = 5;
	private int[] counts = new int[SAMPLE_LENGTH];
	private int lastidx = 0;
	
	private boolean shouldPrint = false;
	
	private BitmapFont font;
	
	public FPSCounter(BitmapFont font)
	{
		this.font = font;
		
		for(int i=0; i<SAMPLE_LENGTH; i++)
		{
			counts[i] = 0;
		}
	}
	
	public final void count()
	{
		int countidx = ((int) Math.floor(System.nanoTime() / 1000000000)) % SAMPLE_LENGTH;
		if (countidx != lastidx)
		{
			if(shouldPrint) 
				System.out.println(String.format("FPS: %f", fps()));
			
			counts[countidx] = 0;
			lastidx = countidx;
		}
		
		counts[countidx]++;
	}
	
	public final void enablePrint()
	{
		shouldPrint = true;
	}
	
	public final void disablePrint()
	{
		shouldPrint = false;
	}
	
	public final float fps()
	{
		int sum = 0,
			n   = 0;
		
		for(int c : counts)
		{
			if(c != 0)
			{
				sum += c;
				n++;
			}
		}
		
		return (float) sum / n;
	}
	
	public final void init()
	{
		
	}
	
	private long lastprint = 0;
	
	public final void draw()
	{
		long time = System.nanoTime() / 1000000000;
		
		if(time != lastprint)
		{
			System.err.println(String.format("FPS: %1$f", fps()));
			lastprint = time;
		}
		
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glDepthMask(false);
//		
//		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
//		GL11.glDisable(GL11.GL_LIGHTING);
		
//		GL11.glPushMatrix();
//		
//		GL11.glLoadIdentity();
//		
//		GL11.glEnable(GL11.GL_TEXTURE_2D);     
//        GL11.glDisable(GL11.GL_DEPTH_TEST);
//        GL11.glDisable(GL11.GL_LIGHTING);                   
//  
//        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);               
//        GL11.glClearDepth(1);                                      
//  
//        GL11.glEnable(GL11.GL_BLEND);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//		
//		Color.white.bind();
//		font.drawString(0, String.format("FPS: %f\n", fps()), 10, 10);
		
//		GL11.glPopAttrib();
//		
//		GL11.glDepthMask(true);
//		GL11.glEnable(GL11.GL_LIGHTING);
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glDisable(GL11.GL_TEXTURE_2D);
//		
//		GL11.glPopMatrix();
	}
}
