package com.cashbot.collection;

import java.util.ArrayList;
import java.util.List;

public class getListcommon {
	
	public Amazevalues getAmazeValuesByID(int id, List<Amazevalues> av)
	{
		 for(Amazevalues value : av) {
		       if(value.getid() == id)
		          return value;
		    }
		return null;
	}
	 public BeastViewList getBeastViewListByID(int id, List<BeastViewList> av)
	 {
			 for(BeastViewList value : av) {
			       if(value.getid() == id)
			          return value;
			    }
			return null;
	 }
	 public FormulaData getFormulaDataByID(int id, List<FormulaData> f)
	 {
			 for(FormulaData data : f) {
			       if(data.getid() == id)
			          return data;
			    }
			 return null;
	 }
	 public Scriptsdetail getScriptsdetailByID(String secid, List<Scriptsdetail> f)
	 {
			 for(Scriptsdetail data : f) {
			       if(data.getSecid().equals(secid))
			          return data;
			    }
			 return null;
	 }
	 
	 public SquadScripts getSquadScriptsByID(int id, List<SquadScripts> f)
	 {
			 for(SquadScripts data : f) {
			       if(data.getid() == id)
			          return data;
			    }
			 return null;
	 }
	 public List<Tradeinfo> getTradeinfoByIDandFname(int id, String fname, List<Tradeinfo> f)
	 {
		 List<Tradeinfo> tf = new ArrayList<Tradeinfo>();
			 for(Tradeinfo data : f) {
			       if((data.getid() == id) && data.getFname().equalsIgnoreCase(fname))
			           tf.add(data);
			    }
			 return tf;
	 }

}
