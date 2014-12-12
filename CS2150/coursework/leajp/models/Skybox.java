package coursework.leajp.models;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import coursework.leajp.Renderable;

public class Skybox implements Renderable {

	private Vector3f cameraPos;
	private Texture[] skyboxTextures;
	private int list;

	private enum Faces {
		TOP    (0),	RIGHT  (1),	BOTTOM (2),
		LEFT   (3),	FRONT  (4),	BACK   (5);
		
		public final int value;
		
		private Faces(int value)
		{
			this.value = value;
		}
	}
	
	public String getName() {
		return "skybox";
	}

	public void load(int id) throws Exception {
		list = id;
		
		// Load textures
		{
			
		}
		
		GL11.glNewList(list, GL11.GL_COMPILE);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		
		GL11.glPushMatrix();
		
		GL11.glPopMatrix();
		
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEndList();
	}

	public void draw() {
		// TODO Auto-generated method stub

	}

	public void setCameraPos(Vector3f cameraPos)
	{
		this.cameraPos = cameraPos;
	}
}
