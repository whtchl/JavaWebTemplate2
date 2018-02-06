package ky.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpInterfaceClientUtil {
	
	static Logger logger = Logger.getLogger(HttpInterfaceClientUtil.class);
	
	public static  final String HTTP_CODE = "HttpCode";
	public static  final String HTTP_MESSAGE = "HttpMessage";
	
	public static void main(String[] args)
	{
		try {
			File file = new File("D://123.txt");
			String aa = FileUtil.readTextFile(file);
			Map<String, String> param = new HashMap<String, String>();
//			param.put("Phone", "13095740055");
			param.put("cgb_data",aa);
//			aa = "?json=123";
			getHttpResponseString2("http://127.0.0.1:9528/CGBClient/BankAction",param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 *
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 * 2015-4-9  qiuxingsheng
	 */
	public static JSONObject getHttpResponseString(String url, Map<String,String> param){
		
		JSONObject json = new JSONObject();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("Http Interface start");
			sb.append("URL:").append(url).append(" ");
			// 建立一个NameValuePair数组，用于存储欲传送的参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if(param!=null)
			{
				Set<String> keySet = param.keySet();
				// 添加参数
				for (String key : keySet) {
					params.add(new BasicNameValuePair(key, param.get(key)));
					sb.append("key:").append(key).append(",value:").append(param.get(key));
				}
			}
			logger.debug(sb);
			
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(url);//HTTP Get请求(POST雷同)
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));//加入参数
			
//			//接口请求超时时间
//			int connectTimeout = Integer.parseInt(SysParamsUtil.getTSysParamsValue("CONNECT_TIMEOUT"));
//			//接口响应超时时间
//			int socketTimeout = Integer.parseInt(SysParamsUtil.getTSysParamsValue("SOCKET_TIMEOUT"));
			//接口请求超时时间
//			int connectTimeout = Integer.parseInt(SysParamsUtil.getTSysParamsValue("CONNECT_TIMEOUT"));
			int connectTimeout = 30000;
			//接口响应超时时间
//			int socketTimeout = Integer.parseInt(SysParamsUtil.getTSysParamsValue("SOCKET_TIMEOUT"));
			int socketTimeout = 30000;
			//设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpPost);//执行请求
			
			json.put("code", String.valueOf(response.getStatusLine().getStatusCode()));
			
			logger.error("getHttpResponseString : code :"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200)
			{
				json.putAll( JSONObject.fromObject(EntityUtils.toString(response.getEntity())));
			}
			else
			{
				json.put("message", EntityUtils.toString(response.getEntity()));
			}
			
		} catch (Exception e) {
			json.put("code", "ERROR");
			json.put("message", e.getMessage());
			e.printStackTrace();
		} 
		logger.error("getHttpResponseString : result :"+json);
		
		return json;
		
	}
		

	/**
	 * 修改Http的响应Code，以及Message
	 *
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 * 2015-10-13  zhaojin
	 */
	public static JSONObject getHttpResponseString2(String url, Map<String,String> param){
		
		JSONObject json = new JSONObject();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("Http Interface start");
			sb.append("URL:").append(url).append(" ");
			// 建立一个NameValuePair数组，用于存储欲传送的参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if(param!=null)
			{
				Set<String> keySet = param.keySet();
				System.out.println("======================================");
				// 添加参数
				for (String key : keySet) {
					params.add(new BasicNameValuePair(key, param.get(key)));
					sb.append("key:").append(key).append(",value:").append(param.get(key));
					System.out.println("key:"+key);
					System.out.println("value:"+param.get(key));
				}
				System.out.println("======================================");
			}
			logger.debug(sb);
			
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(url);//HTTP Get请求(POST雷同)
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));//加入参数
			
			//接口请求超时时间
			int connectTimeout = 25000;
			//接口响应超时时间
			int socketTimeout = 25000;
			//设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpPost);//执行请求
			
			json.put(HTTP_CODE, String.valueOf(response.getStatusLine().getStatusCode()));
			
			logger.info("getHttpResponseString : code :"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200)
			{
				json.putAll( JSONObject.fromObject(EntityUtils.toString(response.getEntity())));
			}
			else
			{
				json.put(HTTP_MESSAGE, EntityUtils.toString(response.getEntity()));
			}
			
		} catch (Exception e) {
			json.put(HTTP_CODE, "ERROR");
			json.put(HTTP_MESSAGE, e.getMessage());
			e.printStackTrace();
		} 
		logger.info("getHttpResponseString : result :"+json);
		
		return json;
		
	}
	
	/**
	 * 修改Http的响应Code，以及Message
	 *
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 * 2015-10-13  zhaojin
	 */
	public static Map getHttpResponseMap(String url, Map<String,String> param){
		
		Map result = new HashMap();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("Http Interface start");
			sb.append("URL:").append(url).append(" ");
			// 建立一个NameValuePair数组，用于存储欲传送的参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if(param!=null)
			{
				Set<String> keySet = param.keySet();
				// 添加参数
				for (String key : keySet) {
					params.add(new BasicNameValuePair(key, param.get(key)));
					sb.append("key:").append(key).append(",value:").append(param.get(key));
				}
			}
			logger.info(sb);
			
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(url);//HTTP Get请求(POST雷同)
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));//加入参数
			
			//接口请求超时时间
			int connectTimeout = 50000;
			//接口响应超时时间
			int socketTimeout = 50000;
			//设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpPost);//执行请求
			
			logger.error("getHttpResponseString : code :"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200)
			{
				result.put(HTTP_MESSAGE, EntityUtils.toString(response.getEntity()));
			}
			else
			{
				result.put(HTTP_CODE, response.getStatusLine().getStatusCode());
				result.put(HTTP_MESSAGE, EntityUtils.toString(response.getEntity()));
			}
			
			
		} catch (Exception e) {
			result.put(HTTP_CODE, "ERROR");
			result.put(HTTP_MESSAGE, e.getMessage());
			e.printStackTrace();
		} 
		logger.info("getHttpResponseString : result :"+result);
		
		return result;
		
	}
}
