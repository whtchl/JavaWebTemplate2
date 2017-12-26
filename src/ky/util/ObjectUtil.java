package ky.util;

import java.lang.reflect.Field;

/**
 * 
 * @author tchl
 *
 */
public class ObjectUtil {
	
	public static boolean isClassField(Class<?> clazz, String field){
		// method
		if(field==null || field.length()==0){
			return false;
		}
        Field[] fields = clazz.getDeclaredFields();
        
        Field _field = null;
        for (Field temp : fields) {
        	String fieldName = temp.getName();
        	if(fieldName.equals(field)){
        		_field = temp;
        	}
        }
        return _field != null;
	}
	
}
