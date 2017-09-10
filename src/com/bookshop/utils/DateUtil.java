package com.bookshop.utils;

import java.util.Date;

public class DateUtil {

	public static String convertDateToFormatString(Date date) {
//		String dateStr = String.format("%tF %<tT", date);
		String dateStr = String.format("%tF", date);
		System.out.println("dateStr-->" + dateStr);
		return dateStr;
	}
}
