 package ky.util;
 
 import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ky.entity.PmsDictionary;
import ky.service.PmsDictionaryService;

@Component
 public class LogUtil
 {
	 
	 @Resource
	private PmsDictionaryService pmsDictionarySer;
	
	private static LogUtil logutil;
	
	@PostConstruct
	public void init() {

	logutil = this;

	logutil.pmsDictionarySer= this.pmsDictionarySer;
	}
	 
   public boolean savelogUtil(String msg)
   {
     FileOutputStream fos = null;
     OutputStreamWriter osw = null;
     BufferedWriter bw = null;
     try {
       String path = LogUtil.class.getResource("/").getPath();
       String logUrl = path.substring(1, path.length()) + "Log/" + formatdate(2) + ".txt";
       File file = new File(logUrl);
       fos = new FileOutputStream(file, true);
       osw = new OutputStreamWriter(fos, "gbk");
       bw = new BufferedWriter(osw);
       bw.write("[Time] " + formatdate(1) + "\n" + msg);
       bw.newLine();
       bw.newLine();
     } catch (FileNotFoundException e1) {
       e1.printStackTrace();
       try
       {
         if (bw != null) {
           bw.flush();
           bw.close();
         }
         if (osw != null) {
           osw.close();
         }
         if (fos != null)
           fos.close();
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     catch (IOException e2)
     {
       e2.printStackTrace();
       try
       {
         if (bw != null) {
           bw.flush();
           bw.close();
         }
         if (osw != null) {
           osw.close();
         }
         if (fos != null)
           fos.close();
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     finally
     {
       try
       {
         if (bw != null) {
           bw.flush();
           bw.close();
         }
         if (osw != null) {
           osw.close();
         }
         if (fos != null)
           fos.close();
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return false;
   }
 
   public String formatdate(int style)
   {
     String type = "yyyyMMdd";
     if (style == 1)
       type = "yyyy-MM-dd HH:mm:ss";
     if (style == 2)
       type = "yyyyMMdd";
     if (style == 3) {
       type = "yyyyMMddhhmmss";
     }
     SimpleDateFormat sdf = new SimpleDateFormat(type);
     String date = sdf.format(new Date());
     return date;
   }
 
   public String getPath()
   {
     String[] addr = new File(LogUtil.class.getName()).getAbsolutePath().replace("%20", " ").split("\\\\");
     String address = "";
     for (int i = 0; i < addr.length - 1; i++) {
       address = address + addr[i] + "\\";
     }
     return address + "src\\Log\\";
   }
   
   
   //获取商户图片显示基础路径
	public static String getPicPath(String Type, String Key){
		PmsDictionary pmsDictionary = new PmsDictionary();
		pmsDictionary.setType(Type);
		pmsDictionary.setKey(Key);
		List<PmsDictionary> pmsDictionaries = logutil.pmsDictionarySer.selectList(pmsDictionary);
		return pmsDictionaries.get(0).getValue();
	}
   
   
   
 }

/* Location:           C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\
 * Qualified Name:     ky.util.LogUtil
 * JD-Core Version:    0.6.0
 */