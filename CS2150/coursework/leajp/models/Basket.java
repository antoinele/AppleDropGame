package coursework.leajp.models;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;
import coursework.leajp.Helpers;
import coursework.leajp.Renderable;

public class Basket implements Renderable {

	private int list;
	private Texture wickerTexture;
	
	public String getName() {
		// TODO Auto-generated method stub
		return "basket";
	}

	public void load(int id) throws Exception {
		list = id;
		
		wickerTexture = Helpers.loadTexture("source_models/wicker.png", "PNG");

    	float[] verticesf = new float[] {
       		 // x     , y     , z
       			0.7f  , 0.3f  , 0f    , //1
       			0.7f  , -0.3f , 0f    ,
       			-0.7f , -0.3f , 0f    ,
       			-0.7f , 0.3f  , 0f    ,
       			1f    , 0.5f  , 0.4f  , //5
       			1f    , -0.5f , 0.4f  ,
       			-1f   , -0.5f , 0.4f  ,
       			-1f   , 0.5f  , 0.4f  ,
       			-0.9f , 0.4f  , 0.4f  ,
       			0.9f  , 0.4f  , 0.4f  , //10
       			0.9f  , -0.4f , 0.4f  ,
       			-0.9f , -0.4f , 0.4f  ,
       			-0.6f , 0.2f  , 0.05f ,
       			0.6f  , 0.2f  , 0.05f , //15
       			0.6f  , -0.2f , 0.05f ,
       			-0.6f , -0.2f , 0.05f ,
       	};
   		
       	int[] quadfaces = new int[] {
       		 1,  2,  3,  4, // bottom
       		 3,  2,  6,  7, // sides
       		 4,  3,  7,  8,
       		 1,  4,  8,  5,
       		 2,  1,  5,  6,
       		 5, 10, 11,  6, // top bezels
       		11, 12,  7,  6,
       		12,  9,  8,  7,
       		 9, 10,  5,  8,
       		10,  9, 13, 14, // inside
       		11, 10, 14, 15, 
       		12, 11, 15, 16,
       		 9, 12, 16, 13,
       		16, 15, 14, 13, // inside bottom
       	};
       	
       	float[] texcoordorder = new float[] {
       		0, 0,
       		2, 0,
       		2, 2,
       		0, 2,
       	};
       	
		
		GL11.glNewList(list, GL11.GL_COMPILE);
       	
		// how shiny are the front faces of the house (specular exponent)
        float basketShininess  = 2.0f;
        float basketSpecular[] = {0.5f, 0.5f, 0.5f, 1.0f};
        float basketDiffuse[]  = {0.7f, 0.7f, 0.7f, 1.0f};
        
        // set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, basketShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(basketSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(basketDiffuse));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(basketDiffuse));

		
		Colour.WHITE.submit();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, wickerTexture.getTextureID());
//       	wickerTexture.bind();
       	
       	GL11.glBegin(GL11.GL_QUADS);
       	for(int i=0; i < quadfaces.length; i+=4)
       	{
       		for(int j=0; j<4; j++)
       		{
       			int v = (quadfaces[i+j] - 1) * 3;
       			
       			GL11.glTexCoord2f(texcoordorder[2*j], texcoordorder[(2*j)+1]);
       			
       			GL11.glVertex3f(verticesf[v], verticesf[v+1], verticesf[v+2]);
       		}
       	}
       	GL11.glEnd();
       	
       	GL11.glDisable(GL11.GL_TEXTURE_2D);
       	
       	GL11.glEndList();
	}

	public void draw() {
		GL11.glCallList(list);
	}

}
