/**
 * Project Name:posp
 * File Name:DesECBUtil.java
 * Package Name:com.chinamobile.posp.security
 * Date:2014年4月29日下午2:04:59
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.
 *
*/
/**
 * Project Name:posp
 * File Name:DesECBUtil.java
 * Package Name:com.chinamobile.posp.security
 * Date:2014年4月29日下午2:04:59
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.
 *
 */

package ky.util;
/**
 * ClassName:DesECBUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014年4月29日 下午2:04:59 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.gd.magic.encrypt.Des;
import com.gd.magic.encrypt.EncryptText;
import com.gd.magic.util.StringUtil;  


  
  
  

public class DesECBUtil {  
    /** 
     * 加密数据 
     * @param encryptString  注意：这里的数据长度只能为8的倍数 
     * @param encryptKey 
     * @return 
     * @throws Exception 
     */  
    public static String encryptDES(String hexString, String encryptKey) throws Exception {  
        SecretKeySpec key = new SecretKeySpec(getKey(encryptKey), "DES");  
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");  
        cipher.init(Cipher.ENCRYPT_MODE, key);  
        byte[] encryptedData = cipher.doFinal(CodeConverter.hexStringToByte(hexString) );  
        return CodeConverter.bytesToHexString(encryptedData);  
    }  
    
    public static String encryptDES2(String hexString, String hexKey) throws Exception {  
        SecretKeySpec key = new SecretKeySpec(getKey( new String(CodeConverter.hexStringToByte(hexKey))), "DES");  
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");  
        cipher.init(Cipher.ENCRYPT_MODE, key);  
        byte[] encryptedData = cipher.doFinal(CodeConverter.hexStringToByte(hexString) );  
        return CodeConverter.bytesToHexString(encryptedData);  
    }  
      
    /** 
     * 自定义一个key 
     * @param string  
     */  
    public static byte[] getKey(String keyRule) {  
        Key key = null;  
        byte[] keyByte = keyRule.getBytes();  
        // 创建一个空的八位数组,默认情况下为0  
        byte[] byteTemp = new byte[8];  
        // 将用户指定的规则转换成八位数组  
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {  
            byteTemp[i] = keyByte[i];  
        }  
        key = new SecretKeySpec(byteTemp, "DES");  
        return key.getEncoded();  
    }  
      
    /*** 
     * 解密数据 
     * @param decryptString 
     * @param decryptKey 
     * @return 
     * @throws Exception 
     */  
    public static String decryptDES(String hexString, String decryptKey) throws Exception {  
        SecretKeySpec key = new SecretKeySpec(getKey(decryptKey), "DES");  
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");  
        cipher.init(Cipher.DECRYPT_MODE, key);  
        byte decryptedData[] = cipher.doFinal(CodeConverter.hexStringToByte(hexString));  
        //return new String(decryptedData);  
        
        return CodeConverter.bytesToHexString(decryptedData);
    }  
    public static String decryptDES2(String hexString, String hexKey) throws Exception {  
        SecretKeySpec key = new SecretKeySpec(getKey(new String(CodeConverter.hexStringToByte(hexKey))), "DES");  
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");  
        cipher.init(Cipher.DECRYPT_MODE, key);  
        byte decryptedData[] = cipher.doFinal(CodeConverter.hexStringToByte(hexString));  
        //return new String(decryptedData);  
        
        return CodeConverter.bytesToHexString(decryptedData);
    }  
    
      
    public static void main(String[] args) throws Exception {  
    	byte[] x = Des.encrypt(CodeConverter.hexStringToByte(EncryptText.decrypt("9c1cf64582691deff2c9c4e1b6a624f7")), CodeConverter.hexStringToByte("64428023105964746442802310596474"));
    	
    	System.out.println(CodeConverter.bytesToHexString(x));
    	
    	//64428023105964746442802310596474
    	//dd19549501c6f9fedd19549501c6f9fe
    	
        String clearText = "76278642900734192445567449588390";  //这里的数据长度必须为8的倍数  
        clearText = CodeConverter.bytesToHexString(clearText.getBytes());
        
        String key = "93196759393418345053671891284486"; 
        key = CodeConverter.bytesToHexString(key.getBytes());
        
        System.out.println("明文："+clearText+"\n密钥："+key);  
        String encryptText = encryptDES2(clearText, key);  
        System.out.println("加密后："+encryptText);  
        String decryptText = decryptDES2(encryptText, key);  
        System.out.println("解密后："+decryptText);  
        
        
        String a = decryptDES2("3CCDAC05F79BF94DCC2DED929C7F67F0","FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        System.out.println("解密后a："+a);  
        
        
         a = decryptDES2("D157DC25A18E3E621B665EEC956841E4","22222222222222222222222222222222");
        System.out.println("解密后a："+a);  
        
        
    }  
}  

