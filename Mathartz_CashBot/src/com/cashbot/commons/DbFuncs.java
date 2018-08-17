package com.cashbot.commons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.pmw.tinylog.*;
import org.pmw.tinylog.writers.FileWriter;

import com.cashbot.collection.AmazeCentervalues;
import com.cashbot.collection.Amazevalues;
import com.cashbot.collection.BeastViewList;
import com.cashbot.collection.FormulaData;
import com.cashbot.collection.Scriptsdetail;
import com.cashbot.collection.SquadScripts;
import com.cashbot.collection.Tradeinfo;

import org.h2.jdbcx.JdbcDataSource;

public class DbFuncs {
	Connection conn = null; 
    Statement stmt = null;
    private String configprop=System.getProperty("user.dir")+File.separator+"conf"+File.separator+"feed.properties";
    public static String dbName;
    public static String USER, PASS;
    String logpath;
	

	public Connection CheckandConnectDB(Connection condb)
	{
		try
		{
			if ((condb == null) || (condb.isClosed()))
			{
				JdbcDataSource ds = new JdbcDataSource();
				//dbName = "jdbc:h2:C:\\Users\\findc06\\Desktop\\MathArtz_DBs/MATHARTZ_AMAZE_DB;AUTO_SERVER=TRUE";
				//dbName = "C:\\Users\\findc06\\Desktop\\Mathartz_BeastBot//Embedded_DB/BEAST_BOT_DB;AUTO_SERVER=TRUE";
		        ds.setURL("jdbc:h2:"+dbName);
		        condb = ds.getConnection(USER,PASS);
		        conn = condb;
			}
		}
		catch(Exception ex)
		{
			Logger.info(ex);
		}
		return condb;
	}
	
	public DbFuncs() {
		Properties prop = new Properties();
		InputStream input = null;
		try
		{
			input =new FileInputStream(configprop);
			prop.load(input);
			if  (prop.getProperty("SOFTWARE").toString().equalsIgnoreCase("NIFTY"))
			{
				dbName = prop.getProperty("DB_HOST_PATH").replace("/", File.separator);
			}
			else if (prop.getProperty("SOFTWARE").toString().equalsIgnoreCase("BANKNIFTY"))
			{
				dbName = prop.getProperty("DB_HOST_BANKNIFTY_PATH").replace("/", File.separator);
			}
			else if (prop.getProperty("SOFTWARE").toString().equalsIgnoreCase("CM"))
			{
				dbName = prop.getProperty("DB_HOST_CM_PATH").replace("/", File.separator);
			}
			USER = prop.getProperty("DB_USER");
			PASS = prop.getProperty("DB_PASS");
			logpath = configlogfile("BEASTBOT_LOG");
		}
		catch(Exception ex)
		{
			Logger.error(ex);
			System.out.println(ex.getMessage());
		}
		finally
		{
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int getRowCount(Connection conn,String Query)
	{
		int rowCount=0;
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement(); 
	         ResultSet rs = stmt.executeQuery(Query); 
	         rs.last();
	         rowCount = rs.getRow();
	         rs.close();
		}
		catch(Exception ex){
			Logger.error(ex);
			System.out.println(ex.getMessage() +"QUERY :"+Query);
			
		}
		finally {
			try { 
	            if(stmt!=null) stmt.close(); 
	         } catch(SQLException se2) { 
	         }		
		}
		return rowCount;
	}
	
	public List<BeastViewList> getBeastViewData(Connection conn, String Querystr)
	{
		List<BeastViewList> set=new ArrayList<BeastViewList>(); 
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 BeastViewList record = new BeastViewList(rs.getInt("id"),rs.getString("headdisplay"),rs.getDouble("F1Point"),rs.getDouble("F1PL"),rs.getDouble("F2Point"),rs.getDouble("F2PL"),rs.getDouble("F3Point"),rs.getDouble("F3PL"),
	        			 rs.getDouble("F4Point"),rs.getDouble("F4PL"),rs.getDouble("F5Point"),rs.getDouble("F5PL"),rs.getDouble("F6Point"),rs.getDouble("F6PL"));
	             set.add(record);
	          }
	         if (rs != null) {
	                rs.close();
	            }
	        
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return set;
	}
	public List<SquadScripts> getSquadScriptData(Connection conn, String Querystr)
	{
		List<SquadScripts> set=new ArrayList<SquadScripts>(); 
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 SquadScripts record = new SquadScripts(rs.getInt("id"),rs.getString("headid"),rs.getString("headdisplay"),rs.getString("headsymbol"),rs.getString("exchange"),rs.getString("instrument")
	        			 ,rs.getString("lotsize"),rs.getString("ticksize"),rs.getString("expdd"),rs.getString("expmonthyear"),rs.getString("opttype"),rs.getString("strike"));
	             set.add(record);
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
	        
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return set;
	}
	public List<FormulaData> getFormulaData(Connection conn,String Querystr)
	{
		
		List<FormulaData> set=new ArrayList<FormulaData>();  
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 FormulaData record = new FormulaData(rs.getInt("id"), rs.getString("fname"),rs.getDouble("X"),rs.getDouble("Y"),rs.getString("st"),rs.getString("mt"),rs.getString("et"),rs.getInt("lcount"),rs.getInt("round"),rs.getInt("qty"),rs.getBoolean("tradeswitch"),rs.getBoolean("isend"));
	             set.add(record);
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
	        
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return set;
	}
	
	public List<Tradeinfo> getTradeInfo(Connection conn,String Querystr)
	{
		
		List<Tradeinfo> set=new ArrayList<Tradeinfo>();  
		try {
			 //DateFormat format = new SimpleDateFormat("E MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
			 DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	
	        	 Tradeinfo record = new Tradeinfo(rs.getInt("ID"), rs.getString("FNAME"), rs.getString("ORDERTYPE"), rs.getString("WAY"), format.parse(rs.getString("FST")), rs.getString("ORDERID"),rs.getDouble("PRICE"));
	             set.add(record);
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
	        
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return set;
	}
	
	
	public String [] loadCentralizedDate(Connection conlink)
	{
		return getSingleCell(conlink, "SELECT DATE FROM TBL_CENTRAL_DATE WHERE ID = 1;").split("-");
		
	}
	
	
	public List<Scriptsdetail> getContractdata(Connection conn,String Querystr)
	{
		
		List<Scriptsdetail> set=new ArrayList<Scriptsdetail>();  
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 Scriptsdetail record = new Scriptsdetail(rs.getString("SECID"),rs.getString("SYMBOL"),rs.getString("EXCHANGE"),rs.getString("INSTRUMENT")
	        			 ,rs.getString("LOTSIZE"),rs.getString("TICKSIZE"),rs.getString("EXPDD"),rs.getString("EXPMONTHYEAR"),rs.getString("OPTTYPE"),rs.getString("STRIKE"));
	             set.add(record);
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
	        
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return set;
	}
	
	
	
	public ArrayList<String> getSingleColumnRecords(Connection conn, String Querystr)
	{
		ArrayList<String> data = new ArrayList<String>();
		try
		{
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 
	        	 data.add(rs.getString(1));
	        
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return data;
	}
	
	public String getSingleCell(Connection conn, String Querystr)
	{
		String data ="";
		try
		{
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 
	        	 data = rs.getString(1);
	        	 break;
	        
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return data;
	}
	
	public String[][] getuniquetransposeId(Connection conn)
	{
		String [][] hyperids = new String[CommonObjects.Globaluniqueheadid.length][2];
		try
		{
			 conn = CheckandConnectDB(conn);
	         
	         for(int i=0; i<CommonObjects.Globaluniqueheadid.length; i++)
	         {
	        	 String ids = "";
	        	 stmt = conn.createStatement();
	        	 stmt.execute("SELECT ID FROM TBL_TRADE_LINE  WHERE HEADID='"+CommonObjects.Globaluniqueheadid[i][0]+"';");
	        	 ResultSet rs =stmt.getResultSet(); 
		         while (rs.next()) {
		        	ids  = ids+":"+rs.getString(1);
		         }
		         hyperids[i][0] = CommonObjects.Globaluniqueheadid[i][0];
		         hyperids[i][1] = ids.replaceFirst(":", "");
		         if (rs != null) {
		                rs.close();
		            }
	         }
	         
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return hyperids;
	}
	
	public List<Amazevalues> getInitialAmazevalues(Connection con)
	{
		List<Amazevalues> avs=new ArrayList<Amazevalues>();
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute("SELECT * FROM TBL_TRADE_LINE;");
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 Amazevalues record = new Amazevalues(rs.getInt("ID"),rs.getString("HEADID"),
	        			 0,0,0,0.0,0.0,0.0,0.0,0.0,"FT",0.0);
	        	 avs.add(record);
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
	        
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return avs;
	}
	
	public List<AmazeCentervalues> getInitialAmazecentervalues(Connection con)
	{
		List<AmazeCentervalues> avs=new ArrayList<AmazeCentervalues>();
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute("SELECT * FROM TBL_TRADE_LINE;");
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 AmazeCentervalues record = new AmazeCentervalues(rs.getInt("ID"),rs.getString("HEADID"),
	        			 0,0,0,0.0,0.0,0.0,0.0,0.0,"FT",0.0,0.0);
	        	 avs.add(record);
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
	        
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return avs;
	}
	
	public String[][] getMultiColumnRecords(Connection conn, String Querystr)
	{
		String [][] data=null;
		
		try
		{
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement();
	         stmt.execute(Querystr);
	         ResultSet rs =stmt.getResultSet(); 
	         rs.last();
	         int rowSize = rs.getRow();
	         //rs.first();
	         ResultSetMetaData rsmd = rs.getMetaData();
	         int columnSize = rsmd.getColumnCount();
	         int i =0;
	         data = new String[rowSize][columnSize];
	         stmt.execute(Querystr);
	         rs =stmt.getResultSet(); 
	         while(rs.next() && i != rowSize)
	         {
	             for(int j=0;j<columnSize;j++){
	            	 data[i][j] = rs.getString(j+1);
	             }
	             i++;                    
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage() +"QUERY :"+Querystr);
			Logger.error(ex);
		}
		finally {
			try {
	            
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            Logger.error("Ignored", e);
	        }	
		}
		return data;
	}
	
	public int getIdentityforInsert(Connection conn, String Query)
	{
		int identity=0 ;
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement(); 
	         stmt.execute(Query); 
	         stmt.execute("CALL SCOPE_IDENTITY();");
	         ResultSet rs =stmt.getResultSet(); 
	         while (rs.next()) {
	        	 
	        	 identity = rs.getInt(1);
	        	 break;
	        
	         }
	         
	         if (rs != null) {
	                rs.close();
	            }
		}
		catch(Exception ex)
		{
			identity = 0;
			Logger.error(ex);
			System.out.println(ex.getMessage() +"QUERY :"+Query);
		}
		finally
		{
			try { 
	            if(stmt!=null) stmt.close(); 
	         } 
			catch(SQLException se2) { 
	         } 
		}
		return identity;
	}
	
	public boolean executeNonQuery(Connection conn, String Query)
	{
		boolean isSucess = true;
		try {
			 conn = CheckandConnectDB(conn);
	         stmt = conn.createStatement(); 
	         stmt.execute(Query); 
		}
		catch(Exception ex)
		{
			isSucess = false;
			Logger.error(ex);
			System.out.println(ex.getMessage() +"QUERY :"+Query);
		}
		finally
		{
			try { 
	            if(stmt!=null) stmt.close(); 
	         } 
			catch(SQLException se2) { 
	         } 
		}
		return isSucess;
	}
	public int executeBatchStatement(Connection conn, String [] statements){
       
        Statement stmt = null;
        int stmtscount=0;
        try {
         
        	 conn = CheckandConnectDB(conn);
            stmt = conn.createStatement();
            for(int i=0; i<statements.length; i++)
            {
            		stmt.addBatch(statements[i]);
            }          
            int[] countWithoutException = stmt.executeBatch();
            System.out.println("executed = " + countWithoutException.length);
            conn.commit();
            stmtscount = countWithoutException.length;
        } catch (BatchUpdateException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage() +"QUERY :"+statements);
				e.printStackTrace();
			}
            
        }
        return stmtscount;
    }
	
	private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
        		JdbcDataSource ds = new JdbcDataSource();
	        ds.setURL("jdbc:h2:"+dbName);
	        dbConnection = ds.getConnection(USER,PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return dbConnection;
    }
	
	public String readconfigprop(String propkey)
	{
		String strResult= null;
		Properties prop = new Properties();
		InputStream input = null;
		String path = null;
		try
		{
			input =new FileInputStream(configprop);
			prop.load(input);
			strResult=prop.getProperty(propkey);
		}
		catch(Exception ex)
		{
			Logger.error(ex.toString());
		}
		finally
		{
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return strResult;
	}
    /*
	* According to the log name and path defined in config.propertise file, it will configure tiny log and return the file path
	*/
	public String configlogfile(String logname)
	{
		String path = null;
		try 
		{
			path = System.getProperty("user.dir") + readconfigprop(logname).replace("/", File.separator);
			Configurator.defaultConfig().writer(new FileWriter(path,false,true)).activate();
			//Logger.info("Log Initiated --> "+path);
		
		}
		catch(Exception ex)
		{
			Logger.error(ex.toString());
		}
		finally 
		{
			
		}
		return path;
	}
	public void dCSV(String [][] recs,  String filename)
    {
	    	String dir = System.getProperty("user.dir");
			String Sep= System.getProperty("file.separator");
	        BufferedWriter bWrite1 = null;
	        String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss", Locale.ENGLISH).format(new Date());
	        File myFile = new File(dir+Sep+filename+"_"+timeStamp+".csv"); 
			try
			{
				 if(!(myFile.exists()))
				 { 
			            myFile.createNewFile(); 
			            System.out.println("New File created...."); 
			     }
				 else
				 {
					 myFile.delete();
					 myFile.createNewFile();
				 }
				 bWrite1 = new BufferedWriter(new java.io.FileWriter(myFile));
				 if (filename.equalsIgnoreCase("dashboard"))
				 {
					 for(int i=0; i < recs.length; i++)
					 {
						 if (i==0)
						 {
							 bWrite1.write("ID, SCRIPT , F1 POINT, F1 PL, F2 POINT, F2 PL, F3 POINT, F3 PL, F4 POINT, F4 PL, F5 POINT, F5 PL, F6 POINT, F6 PL ");
							 bWrite1.newLine();
						 }
						 bWrite1.write(recs[i][0]+","+ recs[i][1]+","+recs[i][2]+","+recs[i][3]+","+recs[i][4]+","+recs[i][5]+","+recs[i][6]+","+recs[i][7]+","+recs[i][8]+","+recs[i][9]+","+recs[i][10]+","+recs[i][11]+","+recs[i][12]+","+recs[i][13]);
						 bWrite1.newLine();
					 }
				 }
				 else if (filename.equalsIgnoreCase("tradeinfo"))
				 {
					 for(int i=0; i < recs.length; i++)
					 {
						 if (i==0)
						 {
							 bWrite1.write("ID, FNAME , ORDER TYPE, WAY, FST, ORDER ID, PRICE");
							 bWrite1.newLine();
						 }
						 bWrite1.write(recs[i][0]+","+ recs[i][1]+","+recs[i][2]+","+recs[i][3]+","+recs[i][4]+","+recs[i][5]+","+recs[i][6]);
						 bWrite1.newLine();
					 }
				 }
				 bWrite1.flush();
				 
			}
			catch(Exception ex)
			{
				if (bWrite1 != null) try {
					bWrite1.close();
					
				 } catch (IOException ioe2) {
				    // just ignore it
				 }
			}
			finally
			{
				
			}
    }

	
}