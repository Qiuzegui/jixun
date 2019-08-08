package com.jluzh.jixun.util;

import java.util.Date;
import java.util.Calendar;

public class DateUtil {
	/**
     * 从Date类型的时间中提取时间部分
     */
	public static Date getDataTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	}
	
	/**
     * 从Date类型的时间中提取日期部分
     */
    public static Date getData(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
