package coursework.leajp.models;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;
import coursework.leajp.GLErrorCodes;
import coursework.leajp.Helpers;
import coursework.leajp.ModelStore;
import coursework.leajp.Renderable;

public class World implements Renderable {

	private int list;
	private Texture grassTexture;
	
	private Renderable barn;
	private Renderable windmill;
	
	public String getName() {
		// TODO Auto-generated method stub
		return "world";
	}

	public void load(int id) throws Exception {
		list = id;
		
		grassTexture = Helpers.loadTexture("source_models/grass-texture-2.jpg", "JPG");
//		grassTexture.bind();
//		
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
//		
		
		barn = ModelStore.getModel("barn");
		windmill = ModelStore.getModel("windmill");
		
		GL11.glNewList(list, GL11.GL_COMPILE);
		
		GL11.glPushMatrix();
			
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			
			// how shiny are the front faces of the roof (specular exponent)
            float groundShininess = 2.0f;
            // specular reflection of the front faces of the roof
            float groundSpecular[] = {0.1f, 0.1f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the roof
            float groundDiffuse[] = {0.3f, 0.3f, 0.3f, 1.0f};
            
            // Set the material properties for the house using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, groundShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(groundSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(groundDiffuse));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(groundDiffuse));
			
			Colour.WHITE.submit();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_REPLACE);

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, grassTexture.getTextureID());
			
			final float texturesize = 25f;
			final float worldsize   = 100f;
			
			GL11.glBegin(GL11.GL_QUADS);
			{
			    GL11.glNormal3f(0.0f,0.0f,1.0f);
			    
			    GL11.glTexCoord2f(0f, 0f);
			    GL11.glVertex3f(-worldsize,-worldsize,0.0f);
			    
			    GL11.glTexCoord2f(texturesize, 0f);
			    GL11.glVertex3f(worldsize,-worldsize,0.0f);
			    
			    GL11.glTexCoord2f(texturesize, texturesize);
			    GL11.glVertex3f(worldsize,worldsize,0.0f);
			    
			    GL11.glTexCoord2f(0f, texturesize);
			    GL11.glVertex3f(-worldsize,worldsize,0.0f);
			}
			GL11.glEnd();
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
			
		GL11.glPopMatrix();
		
		GL11.glEndList();
	}

	public void draw() {
		GL11.glCallList(list);
		
		// Draw barn
		{
			GL11.glPushMatrix();
			
			GL11.glTranslatef(-8f, 5f, 0f);
			GL11.glScalef(4f, 4f, 4f);
			GL11.glRotatef(-35f, 0f, 0f, 1f);
			
			barn.draw();
			
			GL11.glPopMatrix();
		}
		
		// Draw windmill
		{
			GL11.glPushMatrix();
			
			GL11.glTranslatef(-12f, -7f, 5f);
			GL11.glScalef(2.5f, 2.5f, 2.5f);
			GL11.glRotatef(-50f, 0f, 0f, 1f);
			
			windmill.draw();
			
			GL11.glPopMatrix();
		}
	}

}
