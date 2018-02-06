/**
 * 
 */
package ky.util;

import java.math.BigDecimal;

/**
 * @author Administrator
 * 
 */
public class MathUtil{

	/**
	 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
	 */
	private static final int DEF_DIV_SCALE = 10; // 这个类不能实例化

	private MathUtil() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static String add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return String.valueOf(b1.add(b2));
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static String sub(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return String.valueOf(b1.subtract(b2));
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static String mul(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return String.valueOf(b1.multiply(b2));
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static String div(String v1, String v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static String div(String v1, String v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return String.valueOf(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP));
	}
	
	
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static String round(String v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return String.valueOf(b.divide(one, scale, BigDecimal.ROUND_HALF_UP));
	}
	
	public static String round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return String.valueOf(b.divide(one, scale, BigDecimal.ROUND_HALF_UP));
	}
	
	//金额验证  
    public static boolean isNumber(String str)   
    {   
		java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
		java.util.regex.Matcher match=pattern.matcher(str);   
		if(match.matches()==false)   
		   return false;   
		else   
		   return true;   
    } 
    
    /**
	 * 判断采取的方法 如果val==null，不需要返回0，直接返回null
	 * @param val
	 * @param settleWay
	 * @return 
	 */
    public static BigDecimal settleWayPoint(BigDecimal val, int settleWay) {
		if (val != null) {
			if (settleWay == 0) {
				val = retain0PointHalf(val);// 四舍五入
			} else {
				val = retain0PointUp(val);// 进一法
			}
		} else {
//			return BigDecimal.ZERO;
			return null;
		}
		return val;
	}
	
	/**
	 * 四舍五入
	 * 
	 * @param f
	 * @return
	 */
	public static BigDecimal retain0PointHalf(BigDecimal f) {
		return f.divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 进一法
	 * 
	 * @param f
	 * @return
	 */
	public static BigDecimal retain0PointUp(BigDecimal f) {
		return f.divide(new BigDecimal(1), 0, BigDecimal.ROUND_UP);
	}
	
	/**
	 * 传入金额分  整数
	 * @param f 整数
	 * @return
	 */
	public static BigDecimal toAmount(String f) {
		BigDecimal bd = new BigDecimal(0);
		if(StringUtils.isNumeric(f)){
			bd = new BigDecimal(f);
			bd = bd.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
		}
		return bd;
	}
	
	/**
	 * 四舍五入
	 * @param v
	 * @param scale
	 * @return
	 */
	public static BigDecimal round(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 金额处理
	 * 
	 * 先四舍五入 再进一
	 * @param f
	 * @return
	 */
	public static BigDecimal retainamt(BigDecimal f) {
		return round(f,2).divide(new BigDecimal(1), 0, BigDecimal.ROUND_UP);
	}
	
    
}
