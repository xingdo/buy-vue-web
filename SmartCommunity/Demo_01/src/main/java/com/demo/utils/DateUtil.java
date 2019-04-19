package com.demo.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String TIME_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT_SMS = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_FORMAT_CH = "yyyy年MM月dd日 HH:mm:ss";
	public static final String TIME_FORMAT_CH_12 = "yyyy年MM月dd日a hh:mm:ss";
	
	public static String Date2String(){
		return Date2String(new Date(),TIME_FORMAT);
	}
	
	public static String Date2String(Date d){
		return Date2String(d,TIME_FORMAT);
	}
	
	public static String Date2String(String format){
		return Date2String(new Date(),format);
	}
	
	public static String Date2String(Date d,String format){
		String time = "";
		
		if(d == null){
			d = new Date();
		}
		
		if(format == null || format.equals("")){
			format = TIME_FORMAT;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		time = sdf.format(d);
		
		return time;
	}
	
	
	public static Date String2Date(String time){
		
		
		return String2Date(time,null);
	}
	
	
	public static Date String2Date(String time, String format){
		
		Date d = new Date();
		
		if(format == null || format.equals("")){
			format = TIME_FORMAT;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
			d= sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
}
