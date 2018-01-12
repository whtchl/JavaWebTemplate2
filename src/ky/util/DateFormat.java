package ky.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
	public static Date getDate(String str){
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}
	
	 public static Date parseByPattern(String dateStr, String pattern) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);

			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
				//throw new UnexpectedException(e);
				return null;
			}catch(Exception e) {
				e.printStackTrace();
				//throw new UnexpectedException(e);
				return null;
			}
		}
	 public static String format(Date date,String pattern) {
	        //return df.format(date);
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
	        return sdf.format(date);
	    }
	public static String getString(Date date){
		return sdf.format(date);
	}
	public static String getShortString(Date date){
		return sdf1.format(date);
	}
	public static void main(String[] args) {
		System.out.println(getString(new Date("2014/09/10")));
	}
}
