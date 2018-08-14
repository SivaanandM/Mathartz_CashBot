package com.cashbot.collection;

public class SquadScripts {
	
	int id;
	String headid,headdisplay,headsymbol,exchange,
	instrument,lotsize,ticksize,expdd,expmonthyear,opttype,strike;
	public SquadScripts(int id,String headid, String headdisplay, String headsymbol, String exchange,
			String instrument, String lotsize,String ticksize,String expdd,String expmonthyear,String opttype, String strike)
	{
		this.id = id;
		this.headid =headid ;
		this.headdisplay = headdisplay;
		this.headsymbol =headsymbol ;
		this.exchange = exchange ;
		this.instrument = instrument;
		this.lotsize = lotsize;
		this.ticksize = ticksize;
		this.expdd = expdd;
		this.expmonthyear= expmonthyear;
		this.opttype = opttype;
		this.strike = strike;
	}
	public int getid()
	{
		return id;
	}
	public String getheadsecid()
	{
		return headid;
	}
	public String getheadsymbol()
	{
		return headsymbol;
	}
	public String getheaddisplay()
	{
		return headdisplay;
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
