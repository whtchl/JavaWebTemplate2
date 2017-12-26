 package ky.service.Impl;
 
 import java.lang.reflect.Field;
 import ky.service.BaseService;
 import ky.util.LogUtil;
 import org.springframework.stereotype.Service;
 
 @Service
 public class BaseServiceImpl
   implements BaseService
 {
   private LogUtil log = new LogUtil();
 
   private final String newLine = "\n";
 
   public void saveLog(String menu, String tableName, String operateType, String dataInfo)
   {
     String logMsg = "";
 
     String userName = "";
     logMsg = logMsg + "[UserName] " + userName + "\n";
     logMsg = logMsg + "[MenuName] " + menu + "\n";
     logMsg = logMsg + "[TableName] " + tableName + "\n";
     logMsg = logMsg + "[OperateType] " + operateType + "\n";
     logMsg = logMsg + "[DataInfo] " + dataInfo + "\n";
 
     this.log.savelogUtil(logMsg);
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
 
   public int returnParam(int param)
   {
     return 0;
   }
 }

/* Location:           C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\
 * Qualified Name:     ky.service.Impl.BaseServiceImpl
 * JD-Core Version:    0.6.0
 */