package com.cashbot.collection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tradeinfo {
	int id;
	String fname, ordertype, way, clientorderid;
	Date fst;
	double price;
	
	public Tradeinfo(int id, String fname, String ordertype, String way, Date fst, String clientorderid, double price)
	{
		this.id = id;
		this.fname = fname;
		this.ordertype = ordertype;
		this.way= way;
		this.fst = fst;
		this.clientorderid = clientorderid;
		this.price = price ;
	}
	public int getid()
	{
		return id;
	}
	public String getFname()
	{
		return fname;
	}
	public String getOrdertype()
	{
		return ordertype;
	}
	public String getway()
	{
		return way;
	}
	public String getfst()
	{
		DateFormat format = new SimpleDateFormat("E MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
		SimpleDateFormat dtformat = new SimpleDateFormat("HH:mm:ss");
		try {
			return dtformat.format(format.parse(fst.toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getclientorderid()
	{
		return clientorderid;
	}
	public double getPrice()
	{
		return price;
	}
	public String[] getData()
	{
	    return new String[]{String.valueOf(id),fname,ordertype, way,fst.toString(),clientorderid ,String.valueOf(price)};
	}
}
