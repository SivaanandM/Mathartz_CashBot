package com.cashbot.collection;

public class BeastViewList 
{
  int id;
  String headdisplay;
  double F1Point, F2Point, F3Point, F4Point, F5Point, F6Point;
  double F1PL, F2PL, F3PL, F4PL, F5PL, F6PL;
  
  public BeastViewList(int id, String headdisplay, double F1Point, double F1PL, double F2Point, double F2PL, 
		  double F3Point, double F3PL, double F4Point , double F4PL,double F5Point,double F5PL,double F6Point ,double F6PL)		  
  {
	  this.id = id;
	  this.headdisplay = headdisplay;
	  this.F1Point = F1Point;
	  this.F2Point = F2Point;
	  this.F3Point = F3Point;
	  this.F4Point = F4Point;
	  this.F5Point = F5Point;
	  this.F6Point = F6Point;
	  this.F1PL = F1PL;
	  this.F2PL = F2PL;
	  this.F3PL = F3PL;
	  this.F4PL = F4PL;
	  this.F5PL = F5PL;
	  this.F6PL = F6PL;
  }
 
  public int getid()
  {
		return id;
  }
  public String getHeadDisplay()
  {
		return headdisplay;
  }
  public double getF1Point()
  {
		return F1Point;
  }
  public double getF2Point()
  {
		return F2Point;
  }
  public double getF3Point()
  {
		return F3Point;
  }
  public double getF4Point()
  {
		return F4Point;
  }
  public double getF5Point()
  {
		return F5Point;
  }
  public double getF6Point()
  {
		return F6Point;
  }
  public void setF1Point(double f1p)
  {
		this.F1Point=f1p;
  }
  public void setF2Point(double f2p)
  {
		this.F2Point = f2p;
  }
  public void setF3Point(double f3p)
  {
		this.F3Point = f3p;
  }
  public void setF4Point(double f4p)
  {
		this.F4Point=f4p;
  }
  public void setF5Point(double f5p)
  {
		this.F5Point = f5p;
  }
  public void setF6Point(double f6p)
  {
		this.F6Point = f6p;
  }
  
  public double getF1PL()
  {
		return F1PL;
  }
  public double getF2PL()
  {
		return F2PL;
  }
  public double getF3PL()
  {
		return F3PL;
  }
  public double getF4PL()
  {
		return F4PL;
  }
  public double getF5PL()
  {
		return F5PL;
  }
  public double getF6PL()
  {
		return F6PL;
  }
  
  public void setF1PL(double f1pl)
  {
		this.F1PL = f1pl;
  }
  public void setF2PL(double f2pl)
  {
		this.F2PL = f2pl;
  }
  public void setF3PL(double f3pl)
  {
		this.F3PL = f3pl;
  }
  public void setF4PL(double f4pl)
  {
		this.F4PL = f4pl;
  }
  public void setF5PL(double f5pl)
  {
		this.F5PL = f5pl;
  }
  public void setF6PL(double f6pl)
  {
		this.F6PL = f6pl;
  }
  
  public String[] getData()
  {
    return new String[]{String.valueOf(id),headdisplay,String.valueOf(F1Point),String.valueOf(F1PL),String.valueOf(F2Point),String.valueOf(F2PL), String.valueOf(F3Point)
    		, String.valueOf(F3PL),String.valueOf(F4Point),String.valueOf(F4PL),String.valueOf(F5Point),String.valueOf(F5PL)
    		,String.valueOf(F6Point),String.valueOf(F6PL)};
    		
  }
  
  
}
