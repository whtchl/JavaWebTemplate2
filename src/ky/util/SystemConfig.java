package ky.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class SystemConfig {
	
	private static String fileName = "system-config.properties";
	private static Map<String, String> map = new Hashtable<String, String>();
	
	static{
		loadConfig();
	}
	
	@SuppressWarnings("rawtypes")
	public static void loadConfig(){
		Properties p = new Properties();
		try {
			InputStream is = new FileInputStream(SystemConfig.class.getClassLoader().getResource(fileName).getFile());
			p.load(new InputStreamReader(is, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Set set = p.keySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			Object o = it.next();
			map.put(o.toString(), p.get(o).toString());
		}
	}

	public static String getValue(String key){
		return getValue(key, null);
	}
	
	public static String getValue(String key, String defaultValue){
		return (map.get(key)==null) ? defaultValue : map.get(key);
	}

}
