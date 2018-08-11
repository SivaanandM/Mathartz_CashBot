package com.cashbot.prestolib;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.marketcetera.types.trade.OrderStatus;
import org.marketcetera.types.trade.OrderType;
import org.marketcetera.types.trade.Side;
import org.marketcetera.types.trade.TimeInForce;

import com.cashbot.collection.Scriptsdetail;
import com.symphonyfintech.gateway.EsbOrder;
import com.symphonyfintech.gateway.PositionBean;
import com.symphonyfintech.gateway.ReportHolder;
import com.symphonyfintech.gateway.SecurityType;
import com.symphonyfintech.gateway.SymbolDetail;

public class presto_commons {
	
	static List<SymbolDetail> symbolDetailExchange,
	symbolDetailExchangeInstrument, SYMBOLDETAIL;
	static ReportHolder allReport;
	static List<ReportHolder> allList;
	static SymbolDetail SYMBOLDETAILS;
	static String USERNAME, ESBUSER, PASSWORD, ORDERTYPE, ESBSECURITYID,
		SECURITYTYPE, ESBEXCHANGE, INSTRUMENTTYPE, ESBOPTIONTYPE,
		ESBSYMBOL, REMARK, ESBACCOUNT, SIDE, INPUT, ORIGINALORDERID,
		STRATEGYINSTANCEID, EXTERNALINSTANCEID, EXPDATE, clientOrderID,
		QUANTITY, PRICE, STOPPRICE, ESBSTRIKEPPRICE, INSTANCEIDCUSTOMFIELD,
		SEGMENT;
	static List<byte[]> allReportFormated, openOrderFormated,
	tradeHistoryFormated;
	static List<PositionBean> position = null;
	static boolean connectionStatus;
	static OrderStatus ESBORDERSTATUS;
	static byte[] clientLastTradeHistory, clientLatestReport, REPORT;
	static OrderType ESBORDERTYPE;
	static SecurityType ESBSECURITYTYPE;
	static Side ESBSIDE;
	static TimeInForce ESBTIMEINFORCE;
	static String TIMEINFORCE = "DAY";
	static BigDecimal ESBPRICE, ESBSTOPPRICE;
	static Integer ESBQUANTITY;
	
	static Date ESBEXPDATE;
	static GregorianCalendar gcal;
	static XMLGregorianCalendar xgcal = null;
	static Scanner IN = new Scanner(System.in);
	static EsbConnection esbConnect;
	static Iterator<byte[]> iter;
	ReportHandler reportHandler;
	
	static String FutOptExchange = "NSEFO", EquExchange = "NSECM", ComExchange = "NSEDC", McxExchange = "MCX";	
	static String FutSegment = "FUT", optSegment = "OPT", EquSegment = "CM";
	static String FutIdxInsttype = "FUTIDX", FutStkInsttype = "FUTSTK", OptIdxInsttype = "OPTIDX", 
			OptStkInsttype = "OPTSTK", EquInsttype = "Equities", FutComInsttype = "FUTCOM";
	
	
	 
	
	private String configprop=System.getProperty("user.dir")+File.separator+"resource"+File.separator+"config.properties";
	String tradelogpath;
	
	public presto_commons()
	{
		try
		{
			USERNAME="shanmuga";
			PASSWORD = "sm@12345";
			reportHandler = new ReportHandler();
			esbConnect = new EsbConnection();
			esbConnect.initialize(reportHandler);
			logintopresto();
		}
		catch(Exception ex)
		{
			
		}
	}
	public boolean logintopresto()
	{
		boolean connected =false;
		try
		{
			esbConnect.setDealerName(USERNAME);
			esbConnect.setDealerPassword(PASSWORD);
			esbConnect.forceLogoutFromORS(USERNAME, PASSWORD);
			esbConnect.loginToOrs(USERNAME, PASSWORD);
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		connected = esbConnect.getConnectionStatus(USERNAME);
		return connected;
	}
	
	public boolean checkandLoginFinvasia()
	{
		Boolean connectionStatus = false;
		try
		{
			connectionStatus = esbConnect.getConnectionStatus(USERNAME);
			if (connectionStatus == false)
			{
				System.out.print("Is Connected to Broker : "+ logintopresto()); 
		    }
			connectionStatus = esbConnect.getConnectionStatus(USERNAME);
			return connectionStatus;
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		return connectionStatus;
	}
	
	public void forcelogout()
	{
		esbConnect.forceLogoutFromORS(USERNAME, PASSWORD);
	}

	public Scriptsdetail get_CM_Details(String symbol)
	{
		Scriptsdetail sciptsdetailobj=null;
		try
		 {
			 checkandLoginFinvasia();
			 ESBEXCHANGE = "NSECM";
			 ESBSYMBOL = symbol;
			 SEGMENT = "CM";
			 INSTRUMENTTYPE = "Equities";
			 SYMBOLDETAIL = esbConnect.getSymbolDetailsForInstrType(ESBEXCHANGE, ESBSYMBOL, INSTRUMENTTYPE, SEGMENT);
			 
			 if (SYMBOLDETAIL != null)
			 {
				 SymbolDetail sd = SYMBOLDETAIL.get(0);
				 sciptsdetailobj = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
			 }
		 }
		 catch(Exception ex)
		 {
			 System.out.println(ex.toString());
		 }
		 finally
		 {
			 	
		 }
		 return sciptsdetailobj;
	}
	
	public List<SymbolDetail> get_CM_FUT_Details(String exchange,String symbol,String segment, String instype )
	{
	 try
	 {
		 checkandLoginFinvasia();
		 ESBEXCHANGE = exchange;
		 ESBSYMBOL = symbol;
		 SEGMENT = segment;
		 INSTRUMENTTYPE = instype;
		 SYMBOLDETAIL = esbConnect.getSymbolDetailsForInstrType(ESBEXCHANGE, ESBSYMBOL, INSTRUMENTTYPE, SEGMENT);
	 }
	 catch(Exception ex)
	 {
		 System.out.println(exchange.toString());
	 }
	 finally
	 {
		 	
	 }
	 return SYMBOLDETAIL;
	}
	public List<SymbolDetail> get_OPT_Details(String exchange,String symbol,String expdate, String optType )
	{
		try
		{
			 checkandLoginFinvasia();
			 ESBEXCHANGE = exchange;
			 ESBSYMBOL = symbol;
			 EXPDATE = expdate;
			 ESBOPTIONTYPE = optType;
			 SYMBOLDETAIL = esbConnect.getSymbolDetailsDerivativeOPT(ESBEXCHANGE, ESBSYMBOL, EXPDATE, ESBOPTIONTYPE);
		}
		catch(Exception ex)
		{
			
		}
		return SYMBOLDETAIL;
	}
	
	
	public List<Scriptsdetail> getMatchedScripts(String strSymbol, String strexpMMMDD)
	{
		List<Scriptsdetail> sciptsdetailobj=new ArrayList<Scriptsdetail>(); 
		try
		{
			//Getting Details for NSECM, CM, Equites
			checkandLoginFinvasia();
			
			
			Scriptsdetail sc;
			SYMBOLDETAIL = get_CM_FUT_Details("NSECM", strSymbol, "CM", "Equities");
			if (SYMBOLDETAIL != null)
			{
				for (SymbolDetail sd : SYMBOLDETAIL) 
				{
					sc = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
					sciptsdetailobj.add(sc);
				}
			}
			SYMBOLDETAIL = get_CM_FUT_Details("NSEFO", strSymbol, "FO", "FUTIDX");
			if (SYMBOLDETAIL != null)
			{
				for (SymbolDetail sd : SYMBOLDETAIL) 
				{
					sc = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
					sciptsdetailobj.add(sc);
				}
			}
			SYMBOLDETAIL = get_OPT_Details("NSEFO", strSymbol, strexpMMMDD, "CE");
			if (SYMBOLDETAIL != null)
			{
				for (SymbolDetail sd : SYMBOLDETAIL) 
				{
					sc = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
					sciptsdetailobj.add(sc);
				}
			}
			SYMBOLDETAIL = get_OPT_Details("NSEFO", strSymbol, strexpMMMDD, "PE");
			if (SYMBOLDETAIL != null)
			{
				for (SymbolDetail sd : SYMBOLDETAIL) 
				{
					sc = new Scriptsdetail(sd.getSecID(), sd.getSymbol(), sd.getExchange(), sd.getInstrumenttype(),sd.getLotsize(), sd.getTicksize(), sd.getExpiryDay(), sd.getExpiryMonth(), sd.getOpType(), sd.getStrikePrice());
					sciptsdetailobj.add(sc);
				}
			}
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		return sciptsdetailobj;
	}

	public String userPlaceOrderNSE(String exchange,
			String securityType, String symbol, String securityId,
			String expDate, String account, String quantity, String price,
			String stopPrice, String optionType, String strikePrice,
			String orderType, String customField, String remark,
			String timeInForce, String side) {
		String clientID = null;
		try {
			EsbOrder esbOrder = new EsbOrder();

			esbConnect.setDealerName(USERNAME);
			esbOrder.setEsbuser(USERNAME);
			esbOrder.setEsbexchange(exchange);
			esbOrder.setEsbsymbol(symbol);
			esbOrder.setSecurityID(securityId);

			StringTokenizer date = new StringTokenizer(expDate, "-");
			String day = date.nextToken();
			String month = date.nextToken();
			String year = date.nextToken();

			
			
			ESBEXPDATE = new Date(day, month, year);
			gcal = (GregorianCalendar) GregorianCalendar.getInstance();

			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

			if (date != null) {
				xgcal.setDay(Integer.parseInt(ESBEXPDATE.getDay()));
				xgcal.setMonth(Integer.parseInt(ESBEXPDATE.getMonth()));
				xgcal.setYear(Integer.parseInt(ESBEXPDATE.getYear()));
				esbOrder.setEsbexpiry(xgcal);
			}

			esbOrder.setEsbaccount(account);
			esbOrder.setEsbquantity(new Integer(quantity));
			esbOrder.setEsbprice(new BigDecimal(price));
			esbOrder.setEsbstopPrice(new BigDecimal(stopPrice));
			esbOrder.setOptionType(optionType);
			esbOrder.setStrikePrice(strikePrice);
			esbOrder.setInstanceIdCustomField(customField);
			esbOrder.setRemark(remark);

			if (securityType.equalsIgnoreCase("CM")) {
				esbOrder.setEsbsecurityType(SecurityType.COMMON_STOCK);
				// esbOrder.setEsbexpiry(null);
				esbOrder.setOptionType("null");
				esbOrder.setStrikePrice("null");
			} else if (securityType.equalsIgnoreCase("FUT")) {
				esbOrder.setEsbsecurityType(SecurityType.FUTURE);
			} else if (securityType.equalsIgnoreCase("OPT")) {
				esbOrder.setEsbsecurityType(SecurityType.OPTION);
			}

			if (orderType.equalsIgnoreCase("limit")) {
				esbOrder.setEsborderType(OrderType.LIMIT);
				esbOrder.setEsbstopPrice(null);
			} else if (orderType.equalsIgnoreCase("market")) {
				esbOrder.setEsborderType(OrderType.MARKET);
				esbOrder.setEsbprice(null);
				esbOrder.setEsbstopPrice(null);
			} else if (orderType.equalsIgnoreCase("stop limit")) {
				esbOrder.setEsborderType(OrderType.STOP_LIMIT);
			} else if (orderType.equalsIgnoreCase("stop market")) {
				esbOrder.setEsborderType(OrderType.STOP);
				esbOrder.setEsbprice(null);
			}
			if (timeInForce.equalsIgnoreCase("day")) {
				esbOrder.setEsbtimeInForce(TimeInForce.DAY);
			}
			if (side.equalsIgnoreCase("buy")) {
				esbOrder.setEsbside(Side.BUY);
			} else if (side.equalsIgnoreCase("sell")) {
				esbOrder.setEsbside(Side.SELL);
			}

			clientID = esbConnect.placeOrder(USERNAME, esbOrder);
			System.err.println("CLIENT ID:######################### "
					+ clientID);

		} catch (DatatypeConfigurationException e2) {
			e2.printStackTrace();
		}
		return clientID;
	}
	
}
  
