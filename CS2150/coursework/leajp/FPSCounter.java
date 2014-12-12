package coursework.leajp;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FPSCounter {
	private final int SAMPLE_LENGTH = 5;
	private int[] counts = new int[SAMPLE_LENGTH];
	private int lastidx = 0;
	
	private boolean shouldPrint = false;
	
	@SuppressWarnings("deprecation")
	private TrueTypeFont font;
	
	public FPSCounter()
	{
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
		InputStream is = ResourceLoader.getResourceAsStream("source_models/DroidSansMono.ttf");
		
		Font awtfont;
		try {
			awtfont = Font.createFont(Font.TRUETYPE_FONT, is);
			awtfont.deriveFont(24f);
			font = new TrueTypeFont(awtfont, false);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public final void draw()
	{
//		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
//		GL11.glDisable(GL11.GL_LIGHTING);
//		Color.white.bind();
//		font.drawString(0, 0, String.format("FPS: %f\n", fps()), Color.yellow);
//		
//		GL11.glPopAttrib();
	}
}
