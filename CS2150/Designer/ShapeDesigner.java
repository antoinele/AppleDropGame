package Designer;

import org.lwjgl.opengl.GL11;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * The shape designer is a utility class which assits you with the design of 
 * a new 3D object. Replace the content of the drawUnitShape() method with
 * your own code to creates vertices and draw the faces of your object.
 * 
 * You can use the following keys to change the view:
 *   - TAB		switch between vertex, wireframe and full polygon modes
 *   - UP		move the shape away from the viewer
 *   - DOWN     move the shape closer to the viewer
 *   - X        rotate the camera around the x-axis (clockwise)
 *   - Y or C   rotate the camera around the y-axis (clockwise)
 *   - Z        rotate the camera around the z-axis (clockwise)
 *   - SHIFT    keep pressed when rotating to spin anti-clockwise
 *   - A 		Toggle colour (only if using submitNextColour() to specify colour)
 *   - SPACE	reset the view to its initial settings
 *  
 * @author Remi Barillec
 *
 */
public class ShapeDesigner extends AbstractDesigner {
	
	/** Main method **/
	public static void main(String args[])
    {   
		new ShapeDesigner().run( WINDOWED, "Designer", 0.01f);
    }
	
	/** Draw the shape **/
    protected void drawUnitShape()
    {
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
    	
    	Colour.WHITE.submit();
    	
    	GL11.glBegin(GL11.GL_QUADS);
    	for(int i=0; i < quadfaces.length; i+=4)
    	{
    		for(int j=0; j<4; j++)
    		{
    			int v = (quadfaces[i+j] - 1) * 3;
    			
//    			System.out.println(String.format("vertex %4$d (%1$f,%2$f,%3$f)", verticesf[v], verticesf[v+1], verticesf[v+2], i+j));
    			
    			GL11.glVertex3f(verticesf[v], verticesf[v+1], verticesf[v+2]);
    		}
    	}
    	GL11.glEnd();
    }
}
