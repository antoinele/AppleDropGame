package coursework.leajp;

import org.lwjgl.opengl.Display;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private float usableWidth;
	
	private Vector3f cameraPos;
	
	public Camera(float usableWidth)
	{
		this.usableWidth = usableWidth;
	}
	
	public void update()
	{
        float targetY = (Mouse.getX() / (float)Display.getWidth()) - 0.5f;
        
        targetY /= 1.5f;
        
//        targetY = (float) Math.log(Math.abs(targetY)+1) * targetY;

        targetY *= usableWidth;
        
        cameraPos = new Vector3f(18, targetY / -2f, 3);
        
		GLU.gluLookAt(18, 0, 3, 0, targetY, 3, 0, 0, 1); // Set Z to be the up axis, it's easier like that
	}
	
	public Vector3f getCameraPos()
	{
		return cameraPos;
	}
}
