package coursework.leajp.models;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;
import coursework.leajp.Helpers;
import coursework.leajp.ModelStore;
import coursework.leajp.Renderable;

public class WindmillPivot implements Renderable {

	private Texture windmillTexture;
	private int list;
	
	public String getName() {
		return "windmillpivot";
	}

	public void load(int id) throws Exception {
		list = id;
		
		windmillTexture = Helpers.loadTexture("source_models/windmill_texture.png", "png");

		GL11.glNewList(list, GL11.GL_COMPILE);
		
        float windmillShininess  = 0.7f;
        float windmillSpecular[] = {0.8f, 0.08f, 0.08f, 1.0f};
        float windmillDiffuse[]  = {0.5f, 0.5f, 0.5f, 1.0f};
        
        // set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, windmillShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(windmillSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(windmillDiffuse));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(windmillDiffuse));

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);

		Colour.WHITE.submit();
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, windmillTexture.getTextureID());
		
		
		/**
		* Source file: windmill.dae
		* Date: Fri Dec 12 03:47:42 2014
		* Generated with collada2java by Antoine Le
		* http://antoine.me.uk/codedumps/collada2java/
		*/

		GL11.glBegin(GL11.GL_QUADS);
		{
		    GL11.glNormal3f(0.382683694363f,-5.6563300177e-07f,0.923879384995f);
		    GL11.glTexCoord2f(0.883921384811f,0.00415980815887f);
		    GL11.glVertex3f(0.0f,0.808834016323f,1.6447520256f);
		    GL11.glTexCoord2f(0.883921384811f,0.105063699186f);
		    GL11.glVertex3f(0.0f,0.420235991478f,1.6447520256f);
		    GL11.glTexCoord2f(0.849107921124f,0.105063699186f);
		    GL11.glVertex3f(0.13407279551f,0.420235991478f,1.58921694756f);
		    GL11.glTexCoord2f(0.849107921124f,0.00415980815887f);
		    GL11.glVertex3f(0.13407279551f,0.808834075928f,1.58921694756f);
		    GL11.glNormal3f(0.923879384995f,-2.35336003129e-07f,0.382683694363f);
		    GL11.glTexCoord2f(0.849107921124f,0.00416004611179f);
		    GL11.glVertex3f(0.13407279551f,0.808834075928f,1.58921694756f);
		    GL11.glTexCoord2f(0.849107921124f,0.105063699186f);
		    GL11.glVertex3f(0.13407279551f,0.420235991478f,1.58921694756f);
		    GL11.glTexCoord2f(0.81429451704f,0.105036698282f);
		    GL11.glVertex3f(0.189607605338f,0.420236110687f,1.45514404774f);
		    GL11.glTexCoord2f(0.81429451704f,0.00413304520771f);
		    GL11.glVertex3f(0.189607605338f,0.808834075928f,1.45514500141f);
		    GL11.glNormal3f(0.923879623413f,1.73405993564e-07f,-0.382683306932f);
		    GL11.glTexCoord2f(0.81429451704f,0.00413304520771f);
	 	    GL11.glVertex3f(0.189607605338f,0.808834075928f,1.45514500141f);
		    GL11.glTexCoord2f(0.81429451704f,0.105036698282f);
		    GL11.glVertex3f(0.189607605338f,0.420236110687f,1.45514404774f);
		    GL11.glTexCoord2f(0.779480874538f,0.105063699186f);
		    GL11.glVertex3f(0.13407279551f,0.420236200094f,1.32107102871f);
		    GL11.glTexCoord2f(0.779480874538f,0.00416004611179f);
		    GL11.glVertex3f(0.13407279551f,0.808834195137f,1.32107198238f);
		    GL11.glNormal3f(0.382682591677f,4.25257013603e-07f,-0.923879921436f);
		    GL11.glTexCoord2f(0.918735027313f,0.105090498924f);
		    GL11.glVertex3f(0.13407279551f,0.808834195137f,1.32107198238f);
		    GL11.glTexCoord2f(0.918735027313f,0.205994293094f);
		    GL11.glVertex3f(0.13407279551f,0.420236200094f,1.32107102871f);
		    GL11.glTexCoord2f(0.883921504021f,0.205994293094f);
		    GL11.glVertex3f(0.0f,0.420236200094f,1.26553702354f);
		    GL11.glTexCoord2f(0.883921504021f,0.105090498924f);
		    GL11.glVertex3f(0.0f,0.808834314346f,1.26553702354f);
		    GL11.glNormal3f(-0.382682591677f,4.25257013603e-07f,-0.923879921436f);
		    GL11.glTexCoord2f(0.883921504021f,0.105090498924f);
		    GL11.glVertex3f(0.0f,0.808834314346f,1.26553702354f);
		    GL11.glTexCoord2f(0.883921504021f,0.205994293094f);
		    GL11.glVertex3f(0.0f,0.420236200094f,1.26553702354f);
		    GL11.glTexCoord2f(0.849107921124f,0.205994293094f);
		    GL11.glVertex3f(-0.13407279551f,0.420236200094f,1.32107102871f);
		    GL11.glTexCoord2f(0.849107921124f,0.105090498924f);
		    GL11.glVertex3f(-0.13407279551f,0.808834195137f,1.32107198238f);
		    GL11.glNormal3f(-0.923879683018f,1.77534005275e-07f,-0.382683098316f);
		    GL11.glTexCoord2f(0.849107921124f,0.10509070009f);
		    GL11.glVertex3f(-0.13407279551f,0.808834195137f,1.32107198238f);
		    GL11.glTexCoord2f(0.849107921124f,0.205994293094f);
		    GL11.glVertex3f(-0.13407279551f,0.420236200094f,1.32107102871f);
		    GL11.glTexCoord2f(0.814294278622f,0.205967307091f);
		    GL11.glVertex3f(-0.189607605338f,0.420236110687f,1.45514404774f);
		    GL11.glTexCoord2f(0.814294278622f,0.105063699186f);
		    GL11.glVertex3f(-0.189607605338f,0.808834075928f,1.45514500141f);
		}
		GL11.glEnd();

		GL11.glBegin(GL11.GL_POLYGON);
		{
		    GL11.glNormal3f(0.0f,-1.0f,-7.69347991536e-07f);
		    GL11.glTexCoord2f(0.779480874538f,0.0504708215594f);
		    GL11.glVertex3f(0.13407279551f,0.420235991478f,1.58921694756f);
		    GL11.glTexCoord2f(0.779480874538f,0.0881276130676f);
		    GL11.glVertex3f(0.0f,0.420235991478f,1.6447520256f);
		    GL11.glTexCoord2f(0.752939403057f,0.114840596914f);
		    GL11.glVertex3f(-0.13407279551f,0.420235991478f,1.58921694756f);
		    GL11.glTexCoord2f(0.715403974056f,0.114961698651f);
		    GL11.glVertex3f(-0.189607605338f,0.420236110687f,1.45514404774f);
		    GL11.glTexCoord2f(0.688862621784f,0.088419906795f);
		    GL11.glVertex3f(-0.13407279551f,0.420236200094f,1.32107102871f);
		    GL11.glTexCoord2f(0.688862621784f,0.050763130188f);
		    GL11.glVertex3f(0.0f,0.420236200094f,1.26553702354f);
		    GL11.glTexCoord2f(0.715404093266f,0.0240501090884f);
		    GL11.glVertex3f(0.13407279551f,0.420236200094f,1.32107102871f);
		    GL11.glTexCoord2f(0.752939403057f,0.0239290501922f);
		    GL11.glVertex3f(0.189607605338f,0.420236110687f,1.45514404774f);
		}
		GL11.glEnd();

		GL11.glBegin(GL11.GL_QUADS);
		{
		    GL11.glNormal3f(-0.382683604956f,-5.6563300177e-07f,0.923879384995f);
		    GL11.glTexCoord2f(0.918734908104f,0.0041598668322f);
		    GL11.glVertex3f(-0.13407279551f,0.808834075928f,1.58921694756f);
		    GL11.glTexCoord2f(0.918734908104f,0.105063699186f);
		    GL11.glVertex3f(-0.13407279551f,0.420235991478f,1.58921694756f);
		    GL11.glTexCoord2f(0.883921384811f,0.105063699186f);
		    GL11.glVertex3f(0.0f,0.420235991478f,1.6447520256f);
		    GL11.glTexCoord2f(0.883921384811f,0.00415980815887f);
		    GL11.glVertex3f(0.0f,0.808834016323f,1.6447520256f);
		    GL11.glNormal3f(-0.923879504204f,-2.35336003129e-07f,0.382683485746f);
		    GL11.glTexCoord2f(0.814294278622f,0.105063699186f);
		    GL11.glVertex3f(-0.189607605338f,0.808834075928f,1.45514500141f);
		    GL11.glTexCoord2f(0.814294278622f,0.205967307091f);
		    GL11.glVertex3f(-0.189607605338f,0.420236110687f,1.45514404774f);
		    GL11.glTexCoord2f(0.779480874538f,0.205994293094f);
		    GL11.glVertex3f(-0.13407279551f,0.420235991478f,1.58921694756f);
		    GL11.glTexCoord2f(0.779480874538f,0.10509070009f);
		    GL11.glVertex3f(-0.13407279551f,0.808834075928f,1.58921694756f);
		}
		GL11.glEnd();

		GL11.glBegin(GL11.GL_POLYGON);
		{
		    GL11.glNormal3f(0.0f,1.0f,7.69347991536e-07f);
		    GL11.glTexCoord2f(0.779480874538f,0.179160192609f);
		    GL11.glVertex3f(0.0f,0.808834016323f,1.6447520256f);
		    GL11.glTexCoord2f(0.752939522266f,0.205873206258f);
		    GL11.glVertex3f(0.13407279551f,0.808834075928f,1.58921694756f);
		    GL11.glTexCoord2f(0.715404212475f,0.205994293094f);
		    GL11.glVertex3f(0.189607605338f,0.808834075928f,1.45514500141f);
		    GL11.glTexCoord2f(0.688862621784f,0.179452493787f);
		    GL11.glVertex3f(0.13407279551f,0.808834195137f,1.32107198238f);
		    GL11.glTexCoord2f(0.688862621784f,0.141795694828f);
		    GL11.glVertex3f(0.0f,0.808834314346f,1.26553702354f);
		    GL11.glTexCoord2f(0.715404093266f,0.115082703531f);
		    GL11.glVertex3f(-0.13407279551f,0.808834195137f,1.32107198238f);
		    GL11.glTexCoord2f(0.752939581871f,0.114961698651f);
		    GL11.glVertex3f(-0.189607605338f,0.808834075928f,1.45514500141f);
		    GL11.glTexCoord2f(0.779480874538f,0.14150339365f);
		    GL11.glVertex3f(-0.13407279551f,0.808834075928f,1.58921694756f);
		}
		GL11.glEnd();
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glEndList();
	}

	public void draw() {
		GL11.glCallList(list);
	}

}
