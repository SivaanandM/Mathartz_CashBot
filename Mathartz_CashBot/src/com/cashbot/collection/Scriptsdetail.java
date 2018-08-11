package com.cashbot.collection;

public class Scriptsdetail 
{
	String secid;  
	String symbol,exchange,instrument,lotsize, ticksize,expdd,expmonthyear,opttype,strike ;  
	public Scriptsdetail(String feedid, String symbol, String exchange, String instrument, String lotsize, String ticksize, String expdd, String expmonthyear, String opttype, String strike ) {  
	    this.secid = feedid;  
	    this.symbol = symbol;  
	    this.exchange = exchange;  
	    this.instrument = instrument;  
	    this.lotsize = lotsize;  
	    this.ticksize = ticksize;
	    this.expdd = expdd;
	    this.expmonthyear = expmonthyear;  
	    this.opttype = opttype;  
	    this.strike = strike;  
	}  
	public String getSecid()
	{
		return secid;
	}
	public String getSymbol()
	{
		return symbol;
	}
	public String getExchange()
	{
		return exchange;
	}
	public String getInstrument()
	{
		return instrument;
	}
	public String getLotsize()
	{
		return lotsize;
	}
	public String getTicksize()
	{
		return ticksize;
	}
	public String getExpdd()
	{
		return expdd;
	}
	public String getExpmonthyear()
	{
		return expmonthyear;
	}
	public String getOpttype()
	{
		return opttype;
	}
	public String getStrike()
	{
		return strike;
	}

}
