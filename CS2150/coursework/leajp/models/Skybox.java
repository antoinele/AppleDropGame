package coursework.leajp.models;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import coursework.leajp.Camera;
import coursework.leajp.Helpers;
import coursework.leajp.Renderable;

public class Skybox implements Renderable {

	private Texture skyboxTexture;
	private int list;

	private Camera camera = null;
	
	public String getName() {
		return "skybox";
	}

	public void setCamera(Camera camera)
	{
		this.camera = camera;
	}
	
	public void load(int id) throws Exception {
		list = id;
		
		skyboxTexture = Helpers.loadTexture("source_models/skybox.jpg", "JPG");
		
		GL11.glNewList(list, GL11.GL_COMPILE);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);

		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, skyboxTexture.getTextureID());
		
		GL11.glPushMatrix();
		
		if(camera != null)
		{
			Vector3f cameraPos = camera.getCameraPos();
			
			GL11.glTranslatef(cameraPos.x, cameraPos.y, cameraPos.z);
		}
		
		GL11.glScalef(15f, 15f, 15f);
		
		/**
		* Source file: /home/antoine/BlenderStuff/skybox.dae
		* Date: Fri Dec 12 19:54:50 2014
		* Generated with collada2java by Antoine Le
		* http://antoine.me.uk/codedumps/collada2java/
		*/

		GL11.glBegin(GL11.GL_QUADS);
		{
		    GL11.glNormal3f(1.0f,0.0f,0.0f);
		    GL11.glTexCoord2f(1/3f,1/3f);
		    GL11.glVertex3f(-1.0f,-1.0f,1.0f);
		    GL11.glTexCoord2f(1/3f,0f);
		    GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
		    GL11.glTexCoord2f(2/3f,0f);
		    GL11.glVertex3f(-1.0f,1.0f,-1.0f);
		    GL11.glTexCoord2f(2/3f,1/3f);
		    GL11.glVertex3f(-1.0f,1.0f,1.0f);
		    GL11.glNormal3f(0.0f,-1.0f,0.0f);
		    GL11.glTexCoord2f(0f,1/3f);
		    GL11.glVertex3f(-1.0f,1.0f,1.0f);
		    GL11.glTexCoord2f(0f,0f);
		    GL11.glVertex3f(-1.0f,1.0f,-1.0f);
		    GL11.glTexCoord2f(1/3f,0f);
		    GL11.glVertex3f(1.0f,1.0f,-1.0f);
		    GL11.glTexCoord2f(1/3f,1/3f);
		    GL11.glVertex3f(1.0f,1.0f,1.0f);
		    GL11.glNormal3f(-1.0f,0.0f,0.0f);
		    GL11.glTexCoord2f(1/3f,2/3f);
		    GL11.glVertex3f(1.0f,1.0f,1.0f);
		    GL11.glTexCoord2f(1/3f,1/3f);
		    GL11.glVertex3f(1.0f,1.0f,-1.0f);
		    GL11.glTexCoord2f(2/3f,1/3f);
		    GL11.glVertex3f(1.0f,-1.0f,-1.0f);
		    GL11.glTexCoord2f(2/3f,2/3f);
		    GL11.glVertex3f(1.0f,-1.0f,1.0f);
		    GL11.glNormal3f(0.0f,1.0f,0.0f);
		    GL11.glTexCoord2f(0f,2/3f);
		    GL11.glVertex3f(1.0f,-1.0f,1.0f);
		    GL11.glTexCoord2f(0f,1/3f);
		    GL11.glVertex3f(1.0f,-1.0f,-1.0f);
		    GL11.glTexCoord2f(1/3f,1/3f);
		    GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
		    GL11.glTexCoord2f(1/3f,2/3f);
		    GL11.glVertex3f(-1.0f,-1.0f,1.0f);
		    GL11.glNormal3f(0.0f,0.0f,1.0f);
		    GL11.glTexCoord2f(2/3f,2/3f);
		    GL11.glVertex3f(-1.0f,-1.0f,-1.0f);
		    GL11.glTexCoord2f(2/3f,1/3f);
		    GL11.glVertex3f(1.0f,-1.0f,-1.0f);
		    GL11.glTexCoord2f(1f,1/3f);
		    GL11.glVertex3f(1.0f,1.0f,-1.0f);
		    GL11.glTexCoord2f(1f,2/3f);
		    GL11.glVertex3f(-1.0f,1.0f,-1.0f);
		    GL11.glNormal3f(0.0f,0.0f,-1.0f);
		    GL11.glTexCoord2f(2/3f,1/3f);
		    GL11.glVertex3f(1.0f,-1.0f,1.0f);
		    GL11.glTexCoord2f(2/3f,0.001f);
		    GL11.glVertex3f(-1.0f,-1.0f,1.0f);
		    GL11.glTexCoord2f(1f,0.001f);
		    GL11.glVertex3f(-1.0f,1.0f,1.0f);
		    GL11.glTexCoord2f(1f,1/3f);
		    GL11.glVertex3f(1.0f,1.0f,1.0f);
		}
		GL11.glEnd();

		GL11.glPopMatrix();
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		GL11.glPopAttrib();
		
		GL11.glEndList();
	}

	public void draw() {
		GL11.glCallList(list);
	}
}
