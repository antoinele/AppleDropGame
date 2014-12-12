package coursework.leajp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.ImageDataFactory;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.opengl.LoadableImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.ResourceLoader;

public class Helpers {
    /**
     * Loads a texture from a given image file. Note: when using this function,
     * prefer to use square textures whose width and height are both a power of 2;
     * otherwise, your graphics card may not support the texture, or it may affect
     * the performance of your animated scenes
     * 
     * <p> Texture loading code adapted from LWJGL documentation of the Slick-util library</p>
     * 
     * @param path The absolute or relative path of the image file to load as a texture
     * @return A Texture object
     */
//    public static final Texture loadTexture(String path) throws Exception {
//    	Texture tex = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream(path), true);
//    	return tex;
//    }

    /**
     * Loads a texture from a given image file. Note: when using this function,
     * prefer to use square textures whose width and height are both a power of 2;
     * otherwise, your graphics card may not support the texture, or it may affect
     * the performance of your animated scenes
     * 
     * <p> Texture loading code adapted from LWJGL documentation of the Slick-util library</p>
     * 
     * @param path The absolute or relative path of the image file to load as a texture
     * @param imageType The type of image, e.g. "BMP", "JPG", "PNG"
     * @return A Texture object
     */
    public static final Texture loadTexture(String path, String imageType) throws Exception {
    	Texture tex = TextureLoader.getTexture(imageType, ResourceLoader.getResourceAsStream(path), true);
    	return tex;
    }
    
    public static final Texture loadTextureTiled(String path, String imageType) throws Exception {
    	InputStream in = ResourceLoader.getResourceAsStream(path);
    	return getTexture(in, path, GL11.GL_TEXTURE_2D, GL11.GL_LINEAR, dstPixelFormat, holdTextureData, null);
    }
    
    /**
     * The following functions are adapted from the slick2d library
     */
    
    /** The destination pixel format */
    private static int dstPixelFormat = SGL.GL_RGBA;
    
    private static boolean holdTextureData;
    
    /**
    * Indicate where texture data should be held for reinitialising at a future
    * point.
    *
    * @param holdTextureData True if we should hold texture data
    */
    public void setHoldTextureData(boolean holdTextureData) {
    	this.holdTextureData = holdTextureData;
    }
    
    /**
    * Create a new texture ID
    *
    * @return A new texture ID
    */
    public static int createTextureID()
    {
    	return InternalTextureLoader.createTextureID();
    } 
    
    /**
    * Get the closest greater power of 2 to the fold number
    *
    * @param fold The target number
    * @return The power of 2
    */
    public static int get2Fold(int fold) {
	    return InternalTextureLoader.get2Fold(fold);
    } 
    
    /**
    * Get a texture from a image file
    *
    * @param in The stream from which we can load the image
    * @param resourceName The name to give this image in the internal cache
    * @param flipped True if we should flip the image on the y-axis while loading
    * @param target The texture target we're loading this texture into
    * @param minFilter The scaling down filter
    * @param magFilter The scaling up filter
    * @param transparent The colour to interpret as transparent or null if none
    * @return The texture loaded
    * @throws IOException Indicates a failure to load the image
    */
    private static TextureImpl getTexture(InputStream in,
    String resourceName,
    int target,
    int magFilter,
    int minFilter, boolean flipped, int[] transparent) throws IOException
    {
	    // create the texture ID for this texture
	    ByteBuffer textureBuffer;
	    LoadableImageData imageData = ImageDataFactory.getImageDataFor(resourceName);
	    textureBuffer = imageData.loadImage(new BufferedInputStream(in), flipped, transparent);
	    int textureID = createTextureID();
	    TextureImpl texture = new TextureImpl(resourceName, target, textureID);
	    // bind this texture
	    GL11.glBindTexture(target, textureID);
	    
	    // Enable tiling
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
	    
	    int width;
	    int height;
	    int texWidth;
	    int texHeight;
	    boolean hasAlpha;
	    width = imageData.getWidth();
	    height = imageData.getHeight();
	    hasAlpha = imageData.getDepth() == 32;
	    texture.setTextureWidth(imageData.getTexWidth());
	    texture.setTextureHeight(imageData.getTexHeight());
	    texWidth = texture.getTextureWidth();
	    texHeight = texture.getTextureHeight();
	    IntBuffer temp = BufferUtils.createIntBuffer(16);
	    GL11.glGetInteger(SGL.GL_MAX_TEXTURE_SIZE, temp);
	    int max = temp.get(0);
	    if ((texWidth > max) || (texHeight > max)) {
	    	throw new IOException("Attempt to allocate a texture to big for the current hardware");
	    }
	    int srcPixelFormat = hasAlpha ? SGL.GL_RGBA : SGL.GL_RGB;
	    int componentCount = hasAlpha ? 4 : 3;
	    texture.setWidth(width);
	    texture.setHeight(height);
	    texture.setAlpha(hasAlpha);
	    if (holdTextureData) {
	    	Method setTextureData;
			try {
				setTextureData = TextureImpl.class.getDeclaredMethod("setTextureData", Integer.class, Integer.class, Integer.class, Integer.class, ByteBuffer.class);
		    	setTextureData.setAccessible(true);
		    	setTextureData.invoke(texture, srcPixelFormat, componentCount, minFilter, magFilter, textureBuffer);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    GL11.glTexParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, minFilter);
	    GL11.glTexParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, magFilter);
	    // produce a texture from the byte buffer
	    GL11.glTexImage2D(target,
					    0,
					    dstPixelFormat,
					    get2Fold(width),
					    get2Fold(height),
					    0,
					    srcPixelFormat,
					    SGL.GL_UNSIGNED_BYTE,
					    textureBuffer);
	    return texture;
    } 
}
