package coursework.leajp;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

public class GLErrorCodes {
	
	private final static Map<Integer, String> errorCodes = new HashMap<Integer, String>();
	
	{
		Field[] fields = GL11.class.getFields();
		
		for(Field f : fields)
		{
			if(Modifier.isStatic(f.getModifiers()))
			{
				int errcode;
				try {
					errcode = f.getInt(null);
					String name = f.getName();
					
					if(errorCodes.containsKey(errcode))
					{
						System.err.println(String.format("Key exists: %1$d (field name: $2$s)", errcode, name));
					}
					errorCodes.put(errcode, name);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private GLErrorCodes() {}
	
	public static String getError(int code)
	{
		return errorCodes.get(code);
	}
}
