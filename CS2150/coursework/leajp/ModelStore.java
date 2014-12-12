package coursework.leajp;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.lwjgl.opengl.GL11;

import sun.net.www.protocol.file.FileURLConnection;

public class ModelStore {
	/**
	 * Reflection code from: https://stackoverflow.com/a/22462785/626946
	 */
	
	/**
	 * Private helper method
	 * 
	 * @param directory
	 *            The directory to start with
	 * @param pckgname
	 *            The package name to search for. Will be needed for getting the
	 *            Class object.
	 * @param classes
	 *            if a file isn't loaded but still is in the directory
	 * @throws ClassNotFoundException
	 */
	private static void checkDirectory(File directory, String pckgname,
	        ArrayList<Class<?>> classes) throws ClassNotFoundException {
	    File tmpDirectory;

	    if (directory.exists() && directory.isDirectory()) {
	        final String[] files = directory.list();

	        for (final String file : files) {
	            if (file.endsWith(".class")) {
	                try {
	                    classes.add(Class.forName(pckgname + '.'
	                            + file.substring(0, file.length() - 6)));
	                } catch (final NoClassDefFoundError e) {
	                    // do nothing. this class hasn't been found by the
	                    // loader, and we don't care.
	                }
	            } else if ((tmpDirectory = new File(directory, file))
	                    .isDirectory()) {
	                checkDirectory(tmpDirectory, pckgname + "." + file, classes);
	            }
	        }
	    }
	}

	/**
	 * Private helper method.
	 * 
	 * @param connection
	 *            the connection to the jar
	 * @param pckgname
	 *            the package name to search for
	 * @param classes
	 *            the current ArrayList of all classes. This method will simply
	 *            add new classes.
	 * @throws ClassNotFoundException
	 *             if a file isn't loaded but still is in the jar file
	 * @throws IOException
	 *             if it can't correctly read from the jar file.
	 */
	private static void checkJarFile(JarURLConnection connection,
	        String pckgname, ArrayList<Class<?>> classes)
	        throws ClassNotFoundException, IOException {
	    final JarFile jarFile = connection.getJarFile();
	    final Enumeration<JarEntry> entries = jarFile.entries();
	    String name;

	    for (JarEntry jarEntry = null; entries.hasMoreElements()
	            && ((jarEntry = entries.nextElement()) != null);) {
	        name = jarEntry.getName();

	        if (name.contains(".class")) {
	            name = name.substring(0, name.length() - 6).replace('/', '.');

	            if (name.contains(pckgname)) {
	                classes.add(Class.forName(name));
	            }
	        }
	    }
	}

	/**
	 * Attempts to list all the classes in the specified package as determined
	 * by the context class loader
	 * 
	 * @param pckgname
	 *            the package name to search
	 * @return a list of classes that exist within that package
	 * @throws ClassNotFoundException
	 *             if something went wrong
	 */
	public static ArrayList<Class<?>> getClassesForPackage(String pckgname)
	        throws ClassNotFoundException {
	    final ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

	    try {
	        final ClassLoader cld = Thread.currentThread()
	                .getContextClassLoader();

	        if (cld == null)
	            throw new ClassNotFoundException("Can't get class loader.");

	        final Enumeration<URL> resources = cld.getResources(pckgname
	                .replace('.', '/'));
	        URLConnection connection;

	        for (URL url = null; resources.hasMoreElements()
	                && ((url = resources.nextElement()) != null);) {
	            try {
	                connection = url.openConnection();

	                if (connection instanceof JarURLConnection) {
	                    checkJarFile((JarURLConnection) connection, pckgname,
	                            classes);
	                } else if (connection instanceof FileURLConnection) {
	                    try {
	                        checkDirectory(
	                                new File(URLDecoder.decode(url.getPath(),
	                                        "UTF-8")), pckgname, classes);
	                    } catch (final UnsupportedEncodingException ex) {
	                        throw new ClassNotFoundException(
	                                pckgname
	                                        + " does not appear to be a valid package (Unsupported encoding)",
	                                ex);
	                    }
	                } else
	                    throw new ClassNotFoundException(pckgname + " ("
	                            + url.getPath()
	                            + ") does not appear to be a valid package");
	            } catch (final IOException ioex) {
	                throw new ClassNotFoundException(
	                        "IOException was thrown when trying to get all resources for "
	                                + pckgname, ioex);
	            }
	        }
	    } catch (final NullPointerException ex) {
	        throw new ClassNotFoundException(
	                pckgname
	                        + " does not appear to be a valid package (Null pointer exception)",
	                ex);
	    } catch (final IOException ioex) {
	        throw new ClassNotFoundException(
	                "IOException was thrown when trying to get all resources for "
	                        + pckgname, ioex);
	    }

	    return classes;
	}
	/**
	 * End reflection code
	 */
	
	private static final class RenderableProxy
	{
		public final Renderable model;
		public boolean loaded;
		
		public RenderableProxy(Renderable model)
		{
			this.model = model;
			this.loaded = false;
		}
		
		public final void load(int id) throws Exception
		{
			if(!loaded)
			{
				model.load(id);
			}
		}
	}
	
	private Map<String, RenderableProxy> models;
	
	private static int lastListID = 0;
	
	private static ModelStore ms = null;
	
	private ModelStore()
	{
		models = new HashMap<String, RenderableProxy>();
		
		try {
			List<Class<?>> modelclasses = getClassesForPackage("coursework.leajp.models");
			
			for(Class<?> modelclass : modelclasses)
			{
				if(Renderable.class.isAssignableFrom(modelclass))
				{
					try
					{
						Renderable r = (Renderable) modelclass.newInstance();
						
						RenderableProxy rp = new RenderableProxy(r);
						
						models.put(r.getName(), rp);
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else
				{
					System.err.println(String.format("Somehow $1%s is not a renderable?", modelclass.getName()));
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Renderable getModel(String name)
	{
		if(ms == null)
		{
			ms = new ModelStore();
		}
		RenderableProxy rp = ms.models.get(name);
		
		if(rp != null)
		{
			if(!rp.loaded)
			{
				System.err.println(String.format("Loading model \"%s\"", name));
				
				try {
					rp.load(++lastListID);
					
					{	// Check for GL errors
						int err = GL11.glGetError();
						if(err != GL11.GL_NO_ERROR)
						{
							System.err.println(String.format("gl error (model: %2$s): %1$s", GLErrorCodes.getError(err), name));
						}
						else
						{
							System.err.println(String.format("model \"%1$s\" OK", name));
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			
			return rp.model;
		}
		
		return null;
	}
}
