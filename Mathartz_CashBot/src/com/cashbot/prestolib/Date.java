package com.cashbot.prestolib;

public class Date {
	private String day;
	private String month;
	private String year;
	public String getDay() {
	return day;
	}
	public Date(String day, String month, String year) {
	this.day = day;
	this.month = month;
	this.year = year;
	}
	public String getMonth() {
	return month;
	}
	public String getYear() {
	return year;
	}
	@Override
	public String toString() {
	return day + "-" + month + "-" + year;
	}
	public static String monthArr[] = {"", "JAN", "FEB", "MAR", "APR",
	"MAY", "JUN", "JUL",
	"AUG", "SEP", "OCT", "NOV", "DEC"};
	public String getCustomDate() {
		return day + "-" + monthArr[Integer.parseInt(month)] + "-" + year;
	}
	public static Date getDate(String date) {
	if (date == null)
	return null;
	return new Date(date.substring(0, 2), date.substring(3, 5), date
	.substring(6, 10));
	}
	public static Date getDate1(String date) {
	if (date == null)
	return null;
	return new Date(date.substring(6, 8), date.substring(4, 6), date
	.substring(0, 4));
	}
	public static String getmonthvalue(String mmm)
	{
		String v="";
		for (int i=1; i <= monthArr.length; i++) 
		{
			if (monthArr[i].equalsIgnoreCase(mmm))
			{
				if (i < 10)
				{
					return "0"+String.valueOf(i);
				}
				else
				{
					return String.valueOf(i);
				}
				
			}
		}
		return v;
	}
}
