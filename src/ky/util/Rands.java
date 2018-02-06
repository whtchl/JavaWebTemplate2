package ky.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Rands {
	public String Rands(int start, int stop) {
		Date date = new Date();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String number = df1.format(date);
		Long l = null;
		try {
			l = Long.valueOf(df1.parse(number).getTime());
			int i = 0;
			int c = (int) (Math.random() * 10.0D);
			for (int j = 0; j < 10; j++) {
				i = (int) (Math.random() * 900.0D) + 100;
				l = Long.valueOf(l.longValue()
						+ (i + (int) (Math.random() * 12233.0D))
						* (int) (Math.random() * 23.0D));
				l = Long.valueOf(l.longValue()
						- (i + (int) (Math.random() * 899.0D))
						* (int) (Math.random() * 999.0D) << (int) (Math
						.random() * 10.0D));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String a = l.toString();
		String n = a.substring(start, stop);
		return n;
	}
}

/*
 * Location: C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\ Qualified
 * Name: ky.util.Rands JD-Core Version: 0.6.0
 */