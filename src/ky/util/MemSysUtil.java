package ky.util;

import java.beans.IntrospectionException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex; 

import ky.entity.TblOrganization;
import net.sf.json.JSONObject;

/**
 * 会员管理系统
 * @author born
 *
 */
public class MemSysUtil {

	public static final String URL = "http://192.168.18.108/interface/IMerchant/";
	public static final String METHOD  = "addMerchant";  
	
	public static Map<String,String>  syncMerchant (TblOrganization tot,Integer notifytype,String dynamictoken,String website,String isgroup,String riskct) throws Exception
	{
		Map<String,String> resp = new HashMap<String,String>();
		Map<String,String> params = new HashMap<String,String>();
		JSONObject responseObj = null; 
		if( null != tot && null != notifytype && null != dynamictoken)
		{
			params = convertBean(tot,notifytype,dynamictoken,website,isgroup,riskct);
			StringBuffer url = new StringBuffer(); 
			url.append(URL).append(METHOD);
			responseObj = HttpInterfaceClientUtil.getHttpResponseString2(url.toString(), params);
			
		}

		if("R000001".equals(responseObj.getString("result")))
		{
			Map resultMap = new HashMap<String,String>();
			resultMap = JSonUtils.readValue(responseObj.getString("content"), Map.class);
			resp.put("result", "SUCCESS");
			Integer memMerchantId = (Integer)resultMap.get("merchant_id");
			resp.put("content", memMerchantId.toString());  
		}
		else 
		{
			resp.put("result", "ERROR");
			resp.put("content", responseObj.getString("content")); 
		}
		  
		return resp;
	}
	
	 /** 
     * 将一个 JavaBean 对象转化为一个  Map 
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */  
    public static Map<String, String> convertBean(TblOrganization tot,Integer notifytype,String dynamictoken,String website,String isgroup,String riskct) throws NoSuchAlgorithmException, UnsupportedEncodingException {  
    	Map<String,String> returnMap = new HashMap<String,String>();
    	
    	if( null != tot && null != notifytype && null != dynamictoken && !StringUtils.isEmpty(isgroup) && !StringUtils.isEmpty(riskct))
		{
    		Long date = new Date().getTime(); 
    		
    		//returnMap.put("member_id", tot.getMemMerchantId().toString());
    		//商户ID
    		returnMap.put("merchant_code", tot.getUniqueid());
    		//商户名称
    		returnMap.put("merchant_name",tot.getName());
    		//时间戳
    		returnMap.put("t", date.toString()); 
    		//管理者手机
    		returnMap.put("user_mobile", tot.getMgtel());
    		//管理者邮箱
    		returnMap.put("user_email", tot.getEmail());
    		//管理者姓名
    		returnMap.put("user_name", tot.getMgname());
    		//消息通知方式  1 短信 0邮箱
    		returnMap.put("notify_type", notifytype.toString());
    		//用户令牌
    		returnMap.put("user_token", dynamictoken);
    		//会员系统角色 (2=>免费版, 3=>储值积分版(无商品), 4=>储值积分版(含商品), 5=>储值积分版(含计次、商品))
    		returnMap.put("version_id", getVersionId(isgroup));
    		//会员发行类型 (0 无卡;1实体卡;2虚拟卡;3虚拟/实体卡)
    		returnMap.put("riskct", riskct);
    		//网址
    		returnMap.put("website", website);
    		
    		StringBuffer sign = new StringBuffer();
    		sign.append(tot.getMgtel()).append(date).append(METHOD).append("@huitouke.cn");
    		//数字签名
    		returnMap.put("sign", getSign(sign.toString()));
    		
		}
    	
        return returnMap;  
    }  
	
	/*****************************
	 * 数字签名
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private static String getSign(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{

		MessageDigest messageDigest;
		messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
        String encdeStr = Hex.encodeHexString(hash);
        
        return encdeStr;
	}
	
	private static String getVersionId(String isGroup)
	{
		String version_id = "2";//默认为免费版
		
		//免费版
		if("88".equalsIgnoreCase(isGroup.trim()))
			return "2";
		
		//储值积分版(无商品)
		if("92".equalsIgnoreCase(isGroup.trim()))
			return "3";
		
		//储值积分版(含商品)
		if("95".equalsIgnoreCase(isGroup.trim()))
			return "4";
		
		//储值积分版(含计次、商品)
		if("98".equalsIgnoreCase(isGroup.trim()))
			return "5";
		
		return version_id;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//addMerchant(tot);
		//System.out.println(getSign("1233"+"1494838847713"+"addMerchant"+"@huitouke.cn"));
	}

}
