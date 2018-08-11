package com.cashbot.collection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormulaData {

	int id;  
	String fname, st,mt,et;  
	double x,y;
	int lcount,round,qty;  
	Boolean tradeswitch, isend;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Date date = new Date();
	String cdate = sdf.format(date);
	public static SimpleDateFormat datefmt=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	
	public FormulaData(int id, String fname, double x, double y, String st,String mt,String et, int lcount,int round, int qty, Boolean tradeswitch, Boolean isend) {  
	    this.id = id;
	    this.fname = fname;
	    this.x = x;  
	    this.y = y;   
	    this.st = st;  
	    this.mt = mt;  
	    this.et = et;  
	    this.lcount = lcount;  
	    this.round = round;
	    this.qty = qty;
	    this.tradeswitch=tradeswitch;
	    this.isend = isend; 
	}
	
	public int getid() {
        return id;
    }
	public String getfname() {
        return fname;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getLcount() {
        return lcount;
    }
    public double getRound() {
        return round;
    }
    public String getST() {
        return st;
    }
    public String getMT() {
        return mt;
    }
    public String getET() {
        return et;
    }
    public Date getSTDate() throws ParseException {
       
        return datefmt.parse(cdate+" "+st+":00");
    }
    public Date getMTDate() throws ParseException {
    	return datefmt.parse(cdate+" "+mt+":00");
    }
    public Date getETDate() throws ParseException {
    	return datefmt.parse(cdate+" "+et+":00");
    }
    public int getQty() {
        return qty;
    }
    public Boolean getTradeswitch() {
        return tradeswitch;
    }
    public Boolean getIsend()
    {
    	return isend;
    }
    public void setIsend(Boolean end)
    {
    	this.isend = end;
    }

}
