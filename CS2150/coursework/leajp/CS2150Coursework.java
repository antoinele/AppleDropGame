/* CS2150Coursework.java
 * TODO: put your university username and full name here
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- world
 *  |   |
 *  |   +-- barn
 *  |   |
 *  |   +-- windmill
 *  |       |
 *  |       +-- windmillpivot
 *  |           |
 *  |           +-- windmillpropeller
 *  |
 *  +-- skybox 
 *
 *  TODO: Provide a scene graph for your submission
 */
package coursework.leajp;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Mouse;
import coursework.leajp.AppleDropGame.FallingApple;
import coursework.leajp.models.Skybox;
import GraphicsLab.*;

/**
 * TODO: Briefly describe your submission here
 *
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 * TODO: Add any additional controls for your sample to the list above
 *
 */
public class CS2150Coursework extends GraphicsLab
{
	private AppleDropGame game = new AppleDropGame();

	private final float usableWidth = 2 * 9f;
	
	private Camera camera = null;
	
	private Renderable apple;
	private Renderable basket;
	private Renderable world;
	private Skybox skybox;
	
	private FPSCounter fpsCounter;
	
	public CS2150Coursework()
	{
		fpsCounter = new FPSCounter();
	}
	
    //TODO: Feel free to change the window title and default animation scale here
    public static void main(String args[])
    {   new CS2150Coursework().run(WINDOWED,"Apple Drop Game",0.01f);
    }

    protected void initScene() throws Exception
    {//TODO: Initialise your resources here - might well call other methods you write.

    	float globalAmbient[] = {0.6f, 0.6f, 0.6f, 1f};
    	GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
    	
    	// the first light for the scene is white...
        float diffuse0[]  = { 0.8f,  0.8f, 0.8f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { -3.0f, 0.0f, 3.0f, 1.0f};

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);
    	
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glShadeModel(GL11.GL_SMOOTH);
    	
    	fpsCounter.init();
    	
    	world  = ModelStore.getModel("world");
    	skybox = (Skybox)ModelStore.getModel("skybox");

    	camera = new Camera(usableWidth);
    	
    	skybox.setCamera(camera);
    	
    	apple  = ModelStore.getModel("apple");
    	basket = ModelStore.getModel("basket");
    }
    protected void checkSceneInput()
    {
//    	float basketPos = 7f * ((Mouse.getX() / (float)displayMode.getWidth()) - 0.5f);
    	
    	float basketPos = (Mouse.getX() / (float)displayMode.getWidth()) * game.gameWidth;
    	
    	game.moveBasket(basketPos);
    }
    
    protected void updateScene()
    {
        //TODO: Update your scene variables here - remember to use the current animation scale value
        //        (obtained via a call to getAnimationScale()) in your modifications so that your animations
        //        can be made faster or slower depending on the machine you are working on
    	game.update();
    }
    protected void renderScene()
    {//TODO: Render your scene here - remember that a scene graph will help you write this method! 
     //      It will probably call a number of other methods you will write.
    	fpsCounter.count();
    	
    	skybox.draw();
    	
    	GL11.glPushMatrix();
    	{
    		GL11.glTranslatef(0f, 0f, -2f);
    		world.draw();
    	}
    	GL11.glPopMatrix();
    	
    	drawApples();
    	
    	drawBasket();
    	
//		fpsCounter.draw();
    }
    private void drawBasket() {

    	float basketPos = usableWidth * (game.getBasketPos() / game.gameWidth) - (usableWidth/2);
    	
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-2f, basketPos, -1f);
			GL11.glScalef(1.6f, 1.5f, 2f);
			GL11.glRotatef(90f, 0f, 0f, 1f);
			
			basket.draw();
		}
		GL11.glPopMatrix();
		
	}

	private void drawApples() {
    	
    	for(FallingApple fa : game.fallingApples)
    	{
	    	
    		float appleY = usableWidth * (fa.x / game.gameWidth) - (usableWidth / 2);
    		float appleZ = 10 * (fa.y / game.gameHeight);
    		
	    	GL11.glPushMatrix();
	    	{
		    	Colour.WHITE.submit();

		    	GL11.glTranslatef(-2f, appleY, appleZ);
		    	
		    	GL11.glScalef(0.25f, 0.25f, 0.25f);
		    	
		    	apple.draw();
	    	}
			GL11.glPopMatrix();
    	}
    	
	}

	protected void setSceneCamera()
    {
        // call the default behaviour defined in GraphicsLab. This will set a default perspective projection
        // and default camera settings ready for some custom camera positioning below...  
        super.setSceneCamera();
        
        if(camera != null)
        	camera.update();
        
//        float targetY = (Mouse.getX() / (float)displayMode.getWidth()) - 0.5f;
//        
//        targetY *= usableWidth;
//        
//		GLU.gluLookAt(18, 0, 3, 0, targetY, 3, 0, 0, 1); // Set Z to be the up axis, it's easier like that    	
   }

    protected void cleanupScene()
    {//TODO: Clean up your resources here
    }

}