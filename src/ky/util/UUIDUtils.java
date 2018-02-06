package ky.util;

import java.util.Random;
import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author 国惠
 *
 */
public class UUIDUtils {
	/**
	 * 获得随机的字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获得随机的短信激活码
	 * @return
	 */
	public static String getNumUUID(){
		//各位随机数字字母生成的范围
//		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String words = "1234567890";
		Random random = new Random();// 生成随机数
		// 定义StringBuffer
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			// 生成一个随机数字
			int index = random.nextInt(words.length()); // 生成随机数 0 到 length - 1
			// 获得字母数字
			char c = words.charAt(index);
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		String str = getNumUUID();
		System.out.println(str);
	}
}
