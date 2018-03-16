package com.jaken.reminder.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {

	//test pass
	public static synchronized String getDbUUid() {
		UUID uuid=UUID.randomUUID();
		return uuid.toString().substring(0, 32);
	}
	
	//test pass
	public static synchronized Date dateAdd(Date date,Integer days) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}
	
	//test pass
	public static synchronized String formatDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	//test pass
	public static synchronized Date getCurrentDate() {
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static void main(String[] args)  throws Exception{
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
//		System.out.println(formatDate(cal.getTime()));
//		System.out.println(formatDate(dateAdd(cal.getTime(),2)));
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	}
}
