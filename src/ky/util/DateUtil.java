package ky.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_PATTERN = "yyyyMMddHHmmss";
    protected static DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);
	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getyyyymmddStringDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringLongDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringxiaLongDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 */

	public static Date getNextDay(Date nowdate, String delay) {

		try {
			long myTime = (nowdate.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			nowdate.setTime(myTime * 1000);
		} catch (Exception e) {
		}
		return nowdate;
	}

	public static Date addDays(Date _date, int _dayNum) {
		Calendar c = Calendar.getInstance();
		c.setTime(_date);
		c.add(Calendar.DAY_OF_YEAR, _dayNum);
		return c.getTime();
	}

	/**
	 * 
	 * 得到二个日期间的间隔天数
	 * 
	 */

	public static long getTwoDay(Date date1, Date date2) {
		long day = 0;
		try {
			day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {

		}
		return day;

	}
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd",
	// Locale.US);
	// //Date date = simpleDateFormat.parse(selectQuery.getString("SIMEYMD"));
	// Calendar calender = Calendar.getInstance();
	// calender.setTime(date);
	// calender.add(Calendar.MONTH, 1);
	// simpleDateFormat.format(calender.getTime());
	// argConditionData.setSimeymd(simpleDateFormat.format(calender.getTime()).toString());
	//
	//// --------------------------

	public static Date GetSysDate(Date StrDate, int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		// SimpleDateFormat sFmt = new SimpleDateFormat(format);
		// cal.setTime(sFmt.parse( (StrDate), new ParsePosition(0)));
		cal.setTime(StrDate);

		if (day != 0) {
			cal.add(cal.DATE, day);
		}
		if (month != 0) {
			cal.add(cal.MONTH, month);
		}
		if (year != 0) {
			cal.add(cal.YEAR, year);

		}
		return cal.getTime();
	}

	public static String format(Date date, String pattern) {
		// return df.format(date);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		return sdf.format(date);
	}

	

    /**
     * Format a Date object to a String. Now use the Medium format of Locale
     * CHINA. The format is YYYY-MM-DD
     */
    public static String format(Date date) {
        //return df.format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(date);
    }


    public static String formatTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(time);
    }

    public static String formatDefaultTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN, Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * Format a Date object to a String. Now use the Medium format of Locale
     * CHINA. The format is YYYYMMDD
     */
    public static String formatWithoutSlash(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        return sdf.format(date);
    }

    public static String formatWithoutSlash2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * Format a Date object to a String. Now use the Medium format of Locale
     * CHINA. The format is YYYYMMDD
     */
    public static String formatShortWithoutSlash(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * Parse a Date object from a String. Now use the Medium format of Locale
     * CHINA. The format is YYYY-MM-DD
     *
     */
    public static Date parse(String str) {
        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Date parseDateTime(String date, String time) {
        try {
            Calendar c = Calendar.getInstance(Locale.CHINA);

            Calendar c1 = Calendar.getInstance(Locale.CHINA);
            c1.setTime(java.sql.Time.valueOf(time));
            Date dt = parse(date);
            c.setTime(dt);
//
//        	c.set(Calendar.HOUR ,c1.get(Calendar.HOUR));
//        	c.set(Calendar.MINUTE ,c1.get(Calendar.MINUTE));
//        	c.set(Calendar.SECOND ,c1.get(Calendar.SECOND));
            c1.set(Calendar.YEAR, c.get(Calendar.YEAR));
            c1.set(Calendar.MONTH, c.get(Calendar.MONTH));
            c1.set(Calendar.DATE, c.get(Calendar.DATE));
//        	System.out.println(c);
//        	System.out.println(c1.getTime());        	
            return c1.getTime();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * Add specified number of MONTH to a date.
     */
    public static Date addMonths(Date _date, int _monthNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(_date);
        c.add(Calendar.MONTH, _monthNum);
        return c.getTime();
    }

    public static Date addMinutes(Date _date, int _minuteNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(_date);
        c.add(Calendar.MINUTE, _minuteNum);
        return c.getTime();
    }

    public static Date addSeconds(Date _date, int _secondNum) {
        Calendar c = Calendar.getInstance();
        c.setTime(_date);
        c.add(Calendar.SECOND, _secondNum);
        return c.getTime();
    }

    /**
     * Set hour, minute, second, millisecond of _c to 0.
     */
    public static Calendar roundCalendar(Calendar _c) {
        _c.set(Calendar.HOUR_OF_DAY, 0);
        _c.set(Calendar.MINUTE, 0);
        _c.set(Calendar.SECOND, 0);
        _c.set(Calendar.MILLISECOND, 0);
        return _c;
    }

    /**
     * Set hour, minute, second, millisecond of _c to 0.
     */
    public static Date roundDate(Date _d) {
        Calendar c = Calendar.getInstance();
        c.setTime(_d);
        return roundCalendar(c).getTime();
    }

    /**
     * second, millisecond  to 0.
     * @param _d
     * @return
     */
    public static Date roundMinute(Date _d) {
        Calendar c = Calendar.getInstance();
        c.setTime(_d);

        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date roundHour(Date _d) {
        Calendar c = Calendar.getInstance();
        c.setTime(_d);

        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * Set hour, minute, second, millisecond of _c to 0.
     */
    public static Date roundDateToMonth(Date _d) {
        Date day = roundDate(_d);
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static int getWeek(Date _d) {
        Calendar c = Calendar.getInstance();
        c.setTime(_d);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getMinute(Date _d) {
        Calendar c = Calendar.getInstance();
        if (_d != null)
            c.setTime(_d);
        return c.get(Calendar.MINUTE);
    }

    public static int getMinute() {
        return getMinute(null);
    }

    /**
     * Get date2 - date1, the result measurement is day
     *
     * @param date1 The first date
     * @param date2 The second date
     * @return The days between date1 and date2,
     *         if date2 is after date1 the result is positive,
     *         if date2 is before date1 the result is negative.
     *         if date2 and date1 are euqal, the result is 0;
     */
    public static double daysBetween(Date date1, Date date2) {
        long date1Time = date1.getTime();
        long date2Time = date2.getTime();
        return ((double) (date2Time - date1Time)) / (1000 * 3600 * 24);
    }


    public static java.sql.Date getLastMonthFirstDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        int d = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, d);
        return new java.sql.Date(c.getTime().getTime());
    }

    public static java.sql.Date getLastMonthEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        int d = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, d);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return new java.sql.Date(c.getTime().getTime());
    }

    public static Date getMonthFirstDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //c.add(Calendar.MONTH,-1);
        int d = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, d);
        return new Date(c.getTime().getTime());
    }

    public static java.sql.Date getMonthEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //c.add(Calendar.MONTH,-1);
        int d = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, d);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return new java.sql.Date(c.getTime().getTime());
    }

    public static java.sql.Date getLastWeekFirstDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEDNESDAY, -1);
        int d = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_WEEK, d);
        return new java.sql.Date(c.getTime().getTime());
    }

    public static java.sql.Date getLastWeekEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEDNESDAY, -1);
        int d = c.getActualMaximum(Calendar.DAY_OF_WEEK);
        c.set(Calendar.DAY_OF_WEEK, d);

        return new java.sql.Date(c.getTime().getTime());
    }


    public static Date parseDateTime(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 格式化时间，错误返回null
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseByPattern(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);

        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            //throw new UnexpectedException(e);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            //throw new UnexpectedException(e);
            return null;
        }
    }
}
