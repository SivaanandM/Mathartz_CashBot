package com.cashbot.collection;

public class AmazevaluesZ {
	
	int id;
	String headsecid, way;
	int C, LC, R;
	double points, low, high, nextline, baseline;
	double tpl,z;
	
	public AmazevaluesZ(int id, String headsecid, int c, int lc, int r, double points, double low, double high, double nextline, double baseline, String way, double tpl, double z)
	{
		this.id = id;
		this.headsecid = headsecid;
		this.C = c;
		this.LC = lc;
		this.R = r;
		this.points = points;
		this.low = low;
		this.high = high;
		this.nextline = nextline;
		this.baseline = baseline;
		this.way = way;
		this.tpl = tpl;
		this.z = z;
	}
	public int getid()
	{
		return id;
	}
	public String getheadsecid()
	{
		return headsecid;
	}
	public int getc()
	{
		return C;
	}
	public int getlc()
	{
		return LC;
	}
	public int getr()
	{
		return R;
	}
	public double getpoints()
	{
		return points;
	}
	public double getlow()
	{
		return low;
	}
	public double gethigh()
	{
		return high;
	}
	public double getnextline()
	{
		return nextline;
	}
	public double getbaseline()
	{
		return baseline;
	}
	public String getway()
	{
		return way;
	}
	public Double gettpl()
	{
		return tpl;
	}
	public Double getz()
	{
		return z;
	}
	public String[] getData()
	{
	    return new String[]{String.valueOf(id), headsecid, String.valueOf(LC), String.valueOf(R), 
	    		String.valueOf(points), String.valueOf(low), String.valueOf(high), String.valueOf(nextline), String.valueOf(baseline), way, String.valueOf(tpl), String.valueOf(z)};
	}
	
	public void setc(int cval)
	{
		this.C = cval;
	}
	public void setlc(int lcval)
	{
		this.LC = lcval;
	}
	public void setr(int rval)
	{
		this.R = rval;
	}
	public void setpoints(double pointsval)
	{
		this.points=pointsval;
	}
	public void setlow(double lowval)
	{
		this.low=lowval;
	}
	public void sethigh(double highval)
	{
		this.high = highval;
	}
	public void setnextline(double nextlineval)
	{
		this.nextline=nextlineval;
	}
	public void setbaseline(double baselineval)
	{
		this.baseline=baselineval;
	}
	public void setway(String wayval)
	{
		this.way=wayval;
	}
	public void settpl(double tpl)
	{
		this.tpl = tpl;
	}
	public void setz(double z)
	{
		this.z = z;
	}
	
	public void SetAmazevalues(int c, int lc, int r, double points, double low, double high, double nextline, double baseline, String way, double tpl, double z)
	{
		this.C = c;
		this.LC = lc;
		this.R = r;
		this.points = points;
		this.low = low;
		this.high = high;
		this.nextline = nextline;
		this.baseline = baseline;
		this.way = way;
		this.tpl = tpl;
		this.z = z;
	}
	

}
