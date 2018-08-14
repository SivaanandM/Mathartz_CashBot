package com.cashbot.commons;

import java.util.List;
import com.cashbot.collection.Amazevalues;
import com.cashbot.collection.BeastViewList;
import com.cashbot.collection.FormulaData;
import com.cashbot.collection.SquadScripts;
import com.cashbot.collection.Tradeinfo;
import com.cashbot.prestolib.presto_commons;


public class CommonObjects 
{
	public static presto_commons objpresto;
	
	public static Boolean isRunning = false;
	public static List<FormulaData> GlobalAmazeF1;
	public static List<BeastViewList> GlobalBeastViewList;
	public static List<SquadScripts> GlobalSquadScript;
	public static List<Amazevalues> GlobalAmazeValuesF1;
	public static List<Tradeinfo> GlobalTradeInfo;
	public static String[][] Globaluniqueheadid;
	public static String[][] Globaltradlinemap;
	public static String[][] GlobalBeastViewTotal;
	public static String GlobalSoftwarefor;
		
}
