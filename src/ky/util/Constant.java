package ky.util;

public class Constant {

	/**
	 * 跟posp平台交互数据时的通道码
	 */
	//public static String CHANNEL_CODE = "C001";
	
	
	/**
	 * 接口返回成功标识
	 */
	public static String INTERFACE_SUCCESS_CODE = "00000000";
	
	/**
	 * 万付通道编码
	 */
	public static String WF_CHANNEL_CODE = "370003";
	
	/**
	 * 商户报件根据商户费率 到PmsBusinessInfoParam表中获取最高消费额 失败次数等值
	 * 如果在表中查不到对应费率的信息  使用该参数所定费率的规则
	 */
	public static String DEFAULT_BUSINESS_INFO_PARAM_FEE = "-1";
	
	/**
	 * 默认在PmsMerchantFee配置封顶的最大值
	 */
	public static double DEFAULT_MAX_FEE_AMT = 99999999999.99;
	
	/**
	 * 访问代理平台地址
	 */
	public static String KY_AGENT_URL ="http://119.90.39.85:8082/ky_agent/";   // "http://127.0.0.1:8080/ky_agent/";  "http://119.90.39.85:8082/ky_agent/"; 
	
	/**
	 * 访问代理平台地址
	 */
	public static String KY_AGENT_IMAGE_URL = "http://119.90.39.85:8082/ky_agent/upload/"; // "http://127.0.0.1:8080/ky_agent/upload/";    "http://119.90.39.85:8082/ky_agent/upload/"; 
	
	
}
