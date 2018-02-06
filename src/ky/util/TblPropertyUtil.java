package ky.util;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ky.entity.TblProperty;
import ky.service.TblPropertyService;

/**
 * 绯荤粺鍙傛暟宸ュ叿绫�  的
 * @author cpy-hj
 *
 */
@Component
public class TblPropertyUtil {

	@Resource
	private TblPropertyService tblPropertyService;
	
	private static TblPropertyUtil util;
	
	@PostConstruct
	public void init() {

		util = this;

		util.tblPropertyService= this.tblPropertyService;
	}
	
	/**
	 * 
	 * 鏍规嵁鍙傛暟鑾峰彇鍙傛暟瀵硅薄
	 * @param paramCode
	 * @return
	 */
	public static TblProperty getProperty(String name,String uniqueid)
	{
		TblProperty param = new TblProperty();
		param.setName(name);
		param.setUniqueid(uniqueid);
		List<TblProperty> result = util.tblPropertyService.selectList( param);
		if(result.size() == 1)
			return (TblProperty)result.get(0);
		return null;
	}
	
	/**
	 * 鏍规嵁鍙傛暟鑾峰彇鍙傛暟鍊�
	 *
	 * @param paramCode
	 * @return
	 */
	public static String getPropertyValue(String name,String uniqueid)
	{
		
		TblProperty result = getProperty(name, uniqueid);
		if(result != null)
			return result.getValue();
		else
			return null;
	}


	public static String getPropertyValue(String name){
		return getPropertyValue(name, "system_-1_"+name);
	}

	public static String getPropertyValue2(String name, String defaultValue){
		String value = getPropertyValue(name);
		if(value == null || value.length() == 0){
			value = defaultValue;
		}
		return value;
	}
	
}
