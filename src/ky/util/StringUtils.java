package ky.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtils {
	/**
	 * 正则
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n|\f|\"|\'|\b");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static void main(String[] args) {
		System.out.println(StringUtils.replaceBlank("just do it!"));
		System.out.println(Encryption.MD5("53100001"));
	}
	/*-----------------------------------
	
	笨方法：String s = "你要去除的字符串";
	
	        1.去除空格：s = s.replace('\\s','');
	
	        2.去除回车：s = s.replace('\n','');
	
	这样也可以把空格和回车去掉，其他也可以照这样做。
	
	注：\n 回车(\u000a) 
	\t 水平制表符(\u0009) 
	\s 空格(\u0008) 
	\r 换行(\u000d)*/

	public static String lengthFix(String text, int length, char ch, boolean end) {
		if (text == null)
			text = "";
		int tempLength = text.getBytes().length;
		if (length == tempLength) {
			return text;
		}
		if (length > tempLength) {
			char[] fix = new char[length - tempLength];
			for (int i = 0; i < fix.length; i++) {
				fix[i] = ch;
			}
			StringBuffer buffer = new StringBuffer(text);
			if (end)
				buffer = buffer.append(fix);
			else {
				buffer = buffer.insert(0, fix);
			}
			return buffer.toString();
		}
		if (end) {
			return new String(text.getBytes(), 0, length);
		}
		return new String(text.getBytes(), tempLength - length, length);
	}

	public static String lengthFix(String text, int length, String ch, boolean end) {
		return lengthFix(text, length, ch.charAt(0), end);
	}

	public static String bytesToHexStr(byte[] b) {
		return bytesToHexStr(b, 0, b.length);
	}

	public static String bytesToHexStr(byte[] b, int start, int len) {
		StringBuffer str = new StringBuffer();
		for (int i = start; i < start + len; i++) {
			str.append(String.format("%02x", new Object[] { Byte.valueOf(b[i]) }));
		}
		return str.toString();
	}

	public static boolean isEmpty(String value) {
		if ("".equals(value) || value == null || value.length() == 0)
			return true;
		return false;
	}
	
	/**
	 * 是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static String hideCard(String cardno){
		String str = "";
		if(isNumeric(cardno)){
			cardno = cardno.trim();
			String tou = cardno.substring(0,4);
			String wei = cardno.substring(cardno.length()-4);
			str = tou + "*" + wei;
		}else
			str = cardno;
		
		return str;
    	
    }
}
