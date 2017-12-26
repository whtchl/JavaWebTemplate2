package ky.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.struts2.ServletActionContext;

//
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import ky.util.LogUtil;
import ky.util.ObjectUtil;
import ky.util.PageView;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
 public class BaseAction<T> extends ActionSupport
   implements ModelDriven<T>
 {
   private LogUtil log = new LogUtil();
   private final String newLine = "\n";
   protected T model;
   HttpServletRequest request = ServletActionContext.getRequest();
   HttpServletResponse response = ServletActionContext.getResponse();
   HttpSession session = this.request.getSession();
   protected final int timeout = 120000;
 
   public BaseAction() {
     ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
     Class clazz = (Class)pt.getActualTypeArguments()[0];
     try {
      model=(T) clazz.newInstance(); //通过一个反射机制  获得当前实体的实例化对象
     } catch (InstantiationException e) {
       e.printStackTrace();
     } catch (IllegalAccessException e) {
       e.printStackTrace();
     }
   }
 
   public T getModel() {
     return this.model;
   }
 
   public String getRequest(String paramName)
     throws UnsupportedEncodingException
   {
     HttpServletRequest request = ServletActionContext.getRequest();
     return URLDecoder.decode(request.getParameter(paramName), "UTF-8");
   }
 
   public String getParameter(String paramName) {
     HttpServletRequest request = ServletActionContext.getRequest();
     return request.getParameter(paramName);
   }
 
   public void setAttribute(String key, Object value)
   {
     ServletActionContext.getRequest().setAttribute(key, value);
   }
 
   public void jsonArray(List list)
   {
     try {
       this.response.setContentType("text/html;charset=utf-8");
       JSONArray json = JSONArray.fromObject(list);
       this.response.setCharacterEncoding("utf-8");
       PrintWriter out = this.response.getWriter();
       this.response.setContentType("text/html");
       out.print(json);
       out.flush();
       out.close();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void jsonObject(Object obj) {
     try {
       this.response.setContentType("text/html;charset=utf-8");
       JSONObject json = JSONObject.fromObject(obj);
       this.response.setCharacterEncoding("utf-8");
       PrintWriter out = this.response.getWriter();
       this.response.setContentType("text/html");
       out.print(json);
       out.flush();
       out.close();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public JSONObject getJSON(String url)
   {
     HttpClient client = new HttpClient();
     PostMethod post = new PostMethod(url);
     String respStr = "";
     post.getParams().setContentCharset("utf-8");
     try
     {
       HttpConnectionManagerParams managerParams = client
         .getHttpConnectionManager().getParams();
 
       managerParams.setConnectionTimeout(30000);
 
       managerParams.setSoTimeout(120000);
 
       client.executeMethod(post);
       respStr = post.getResponseBodyAsString();
     } catch (Exception e) {
       e.printStackTrace();
     }
     finally {
       if (post != null) {
         post.releaseConnection();
       }
     }
     return JSONObject.fromObject(respStr);
   }
 
   public String getTime(int time)
   {
     TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
     TimeZone.setDefault(tz);
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String times = format.format(new Date(time * 1000L));
     return times;
   }
 
   public PageView pageParam(String page, String rows, String order, String sort, T model)
   {
	   /**
	    * 防止注入漏洞
	    */
	   String orderValue = getParameter(order);
	   String sortValue = getParameter(sort);
	   if(orderValue!=null && orderValue.length()>0 && !ObjectUtil.isClassField(model.getClass(), orderValue)){
		   throw new RuntimeException();
	   }
	   if(sortValue!=null && sortValue.length()>0 && !sortValue.equalsIgnoreCase("DESC") && !sortValue.equalsIgnoreCase("ASC")){
		   throw new RuntimeException();
	   }
     return new PageView(Integer.parseInt(getParameter(page)), Integer.parseInt(getParameter(rows)), getParameter(order), getParameter(sort), model);
   }
 
   public void returnPageInfo(PageView pageView)
   {
     Map jsonMap = new HashMap();
     jsonMap.put("total", Integer.valueOf(pageView.getRecordCount()));
     jsonMap.put("rows", pageView.getRecordList());
     jsonObject(jsonMap);
   }
   
   public void returnPageList(List list)
   {
     Map jsonMap = new HashMap();
     jsonMap.put("rows", list);
     jsonObject(jsonMap);
   }
 
   public void returnU_D_S_info(int param)
   {
     List list = new ArrayList();
     list.add(Integer.valueOf(param));
     jsonArray(list);
   }
   public void returnU_String_info(String param)
   {
     List list = new ArrayList();
     list.add(param);
     jsonArray(list);
   }
 
   public String getDateInfo(Object obj)
   {
     String dataInfo = "";
     Field[] fields = obj.getClass().getDeclaredFields();
     for (int j = 0; j < fields.length; j++) {
       fields[j].setAccessible(true);
 
       String name = fields[j].getName();
       String dataVal = "";
 
       if (fields[j].getType().getName().equals(String.class.getName())) {
         try {
           dataVal = fields[j].get(obj).toString();
           if ((dataVal == null) || (dataVal.equals(""))) continue;
           dataInfo = dataInfo + "[" + name + "]-" + dataVal + ","; } catch (Exception localException) {
         }
       } else {
         if ((!fields[j].getType().getName().equals(Integer.class.getName())) && (!fields[j].getType().getName().equals("int"))) continue;
         try {
           dataVal=fields[j].getInt(obj)+"";  
           if ((dataVal != null) && (!dataVal.equals("")))
             dataInfo = dataInfo + "[" + name + "] " + dataVal + ",";
         } catch (Exception localException1) {
         }
       }
     }
     return dataInfo;
   }
   
   /**
    * 获得项目根目录 带斜杠
    *
    * @return
    */
   public String getRootPath()
   {
	 String rootPath = request.getSession().getServletContext().getRealPath("/");
     if( !(rootPath.endsWith("/")||rootPath.endsWith("\\")))
     {
    	 rootPath += File.separator;
     }
     
     return rootPath;
   }

   protected void returnFile(File file){
   		try {
   			if(!file.exists()){
   				return;
   			}
   		    response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
	    	response.setCharacterEncoding("utf-8");
	        response.setContentType("multipart/form-data");
	    	OutputStream out = response.getOutputStream();
//	        FileUtil.writeFile2OutputStream(file, out);
	        int byteread = 0;
	        InputStream in = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            in.close();
	        out.flush();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
 }

