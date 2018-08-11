package com.cashbot.collection;

public class SquadScripts {
	
	int id;
	String headid,headdisplay,headsymbol,symbol,exchange,
	instrument,lotsize,ticksize,expdd,expmonthyear,opttype,strike;
	public SquadScripts(int id,String headid, String headdisplay, String headsymbol, String symbol, String exchange,
			String instrument, String lotsize,String ticksize,String expdd,String expmonthyear,String opttype, String strike)
	{
		this.id = id;
		this.headid =headid ;
		this.headdisplay = headdisplay;
		this.headsymbol =headsymbol ;
		this.symbol = symbol;
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
	public String getPSymbol()
	{
		return symbol;
	}
	public String getPExchange()
	{
		return exchange;
	}
	public String getPInstrument()
	{
		return instrument;
	}
	public String getPLotsize()
	{
		return lotsize;
	}
	public String getPTicksize()
	{
		return ticksize;
	}
	public String getPExpdd()
	{
		return expdd;
	}
	public String getPExpmonthyear()
	{
		return expmonthyear;
	}
	public String getPOpttype()
	{
		return opttype;
	}
	public String getPStrike()
	{
		return strike;
	}
} 
