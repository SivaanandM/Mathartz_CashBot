package com.cashbot.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.h2.engine.DbObject;

import com.cashbot.prestolib.*;
import com.cashbot.commons.CommonObjects;
import com.cashbot.commons.DbFuncs;
import com.cashbot.collection.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class ScriptSearch {

	private JFrame frmScriptSearch;
	private JTextField textSymbol;
	private JTable table;
	private JButton btnGetScriptList;
	private Scriptsdetail records=null;
	TableModel model;
	DbFuncs objdb;
	Connection h2con=null;
	private static int HEADER_HEIGHT = 25;
	String col[]= {"SEC-ID","SYMBOL","EXCHANGE","INSTRUMENTS","LOT-SIZE","TICK-SIZE","EXPIRY-DD","EXPIRY-MMMYY","OPT-TYPE","STRIKE"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScriptSearch window = new ScriptSearch(null);
					window.frmScriptSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScriptSearch(presto_commons obj) 
	{
		objdb = new DbFuncs();
		h2con=objdb.CheckandConnectDB(h2con);
		if (obj == null)
		{
			CommonObjects.objpresto = new presto_commons();
		}
		else
		{
			CommonObjects.objpresto = obj;
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		renderControl();
		
	}
	public void toCSV(JTable table, String filename){
	    try{
	        TableModel model = table.getModel();
	        FileWriter excel = new FileWriter("C:\\Users\\findc06\\Desktop\\"+filename+".csv");

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + ",");
	        }

	        excel.write("\n");

	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	                excel.write(model.getValueAt(i,j).toString()+",");
	            }
	            excel.write("\n");
	        }

	        excel.close();

	    }catch(IOException e){ System.out.println(e); }
	}
	
	private void renderControl()
	{
		try	
		{
			frmScriptSearch = new JFrame();
			frmScriptSearch.setVisible(true);
			frmScriptSearch.setTitle("Presto Contract Search");
			frmScriptSearch.getContentPane().setBackground(Color.BLACK);
			frmScriptSearch.getContentPane().setLayout(new BorderLayout(0, 0));
			frmScriptSearch.setBounds(100, 100, 945, 629);
			frmScriptSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmScriptSearch.setResizable(false);
			
			JLabel lblScriptsCrawler = new JLabel("SEC-ID STORAGE");
			lblScriptsCrawler.setHorizontalAlignment(SwingConstants.CENTER);
			lblScriptsCrawler.setForeground(new Color(255, 220, 135));
			lblScriptsCrawler.setFont(new Font("Ebrima", Font.PLAIN, 20));
			frmScriptSearch.getContentPane().add(lblScriptsCrawler, BorderLayout.NORTH);
			
			JPanel pnlmiddle = new JPanel();
			frmScriptSearch.getContentPane().add(pnlmiddle, BorderLayout.CENTER);
			pnlmiddle.setLayout(null);
			pnlmiddle.setBackground(Color.BLACK);
			
			JPanel pnlsearchcontrol = new JPanel();
			pnlsearchcontrol.setBounds(183, 11, 601, 71);
			pnlsearchcontrol.setBackground(new Color(33,33,33));
			pnlmiddle.add(pnlsearchcontrol);
			pnlsearchcontrol.setLayout(null);
			
			JLabel lblSymbol = new JLabel("SYMBOL");
			lblSymbol.setBounds(38, 10, 82, 49);
			lblSymbol.setHorizontalAlignment(SwingConstants.LEFT);
			lblSymbol.setForeground(Color.WHITE);
			lblSymbol.setFont(new Font("Ebrima", Font.PLAIN, 16));
			pnlsearchcontrol.add(lblSymbol);
			
			textSymbol = new JTextField();
			textSymbol.setBounds(116, 16, 236, 35);
			textSymbol.setHorizontalAlignment(SwingConstants.LEFT);
			textSymbol.setForeground(new Color(255, 220, 135));
			textSymbol.setFont(new Font("Ebrima", Font.PLAIN, 20));
			textSymbol.setColumns(10);
			textSymbol.setCaretColor(Color.WHITE);
			textSymbol.setBackground(Color.BLACK);
			textSymbol.addKeyListener(new KeyAdapter() {

				  public void keyTyped(KeyEvent e) {
				    char keyChar = e.getKeyChar();
				    if (Character.isLowerCase(keyChar)) {
				      e.setKeyChar(Character.toUpperCase(keyChar));
				    } 
				  }
				  public void keyPressed(KeyEvent key)
			      {

			      		if(key.getKeyChar() == KeyEvent.VK_ENTER)

			      			btnGetScriptList.doClick();

			      }

				});
			pnlsearchcontrol.add(textSymbol);
			DateFormat df = new SimpleDateFormat("MMM-yy"); // Just the year, with 2 digits
			String formattedDate = df.format(Calendar.getInstance().getTime());
			
			btnGetScriptList = new JButton("Add");
			btnGetScriptList.setFont(new Font("Ebrima", Font.BOLD, 12));
			btnGetScriptList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					records = CommonObjects.objpresto.get_CM_Details(textSymbol.getText());
					if (records != null)
					{
						objdb.executeNonQuery(h2con, "DELETE FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+textSymbol.getText()+"';");
						String query = "INSERT INTO TBL_MASTER_CONTRACTS (SECID, SYMBOL, EXCHANGE, INSTRUMENT, LOTSIZE, TICKSIZE, EXPDD, EXPMONTHYEAR, OPTTYPE, STRIKE) "
									+ "VALUES ('"+records.getSecid()+"','"+records.getSymbol()+"','"+records.getExchange()+"','"+records.getInstrument()+"','"+records.getLotsize()+"'"
											+ ",'"+records.getTicksize()+"','"+records.getExpdd()+"','"+records.getExpmonthyear()+"','"+records.getOpttype()+"','"+records.getStrike()+"');";
						
						objdb.executeNonQuery(h2con, query);
						String [][] values = objdb.getMultiColumnRecords(h2con, "SELECT SECID, SYMBOL, EXCHANGE, INSTRUMENT, LOTSIZE, TICKSIZE, EXPDD, EXPMONTHYEAR, OPTTYPE, STRIKE FROM TBL_MASTER_CONTRACTS ORDER BY SYMBOL;");
						model = new DefaultTableModel(values, col);
						table.setModel(model);
						textSymbol.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(frmScriptSearch, "No Matched Sec-Id Found for given symbol.", "INFO",JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
			});
			btnGetScriptList.setBounds(362, 16, 184, 35);
			pnlsearchcontrol.add(btnGetScriptList);
			
			
			table = new JTable();
			table.setBounds(10, 559, 923, -345);
			table.setBackground(Color.BLACK);
			table.setFillsViewportHeight(true);
			table.setFont(new Font("Ebrima", Font.PLAIN, 14));
			table.setRowHeight(23);
			table.setShowGrid(true);
			table.setShowHorizontalLines(false);
			table.setShowVerticalLines(true);
			model = new DefaultTableModel(col,0);
			table = new JTable(model){
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
			        Component returnComp = super.prepareRenderer(renderer, row, column);
			        Color alternateColor = Color.BLACK;
			        Color whiteColor = new Color(33,33,33);
			        if (!returnComp.getBackground().equals(getSelectionBackground())){
			            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
			            returnComp .setBackground(bg);
			            returnComp.setForeground(Color.WHITE);
			            bg = null;
			        }
			        return returnComp;
			    }
			    @Override
			    public boolean isCellEditable(int i, int i1) {
			        return false; //To change body of generated methods, choose Tools | Templates.
			    }   
			};
			
			table.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.isControlDown() && e.getKeyCode() == 68) 
					{
			  		  //CTRL+ D
							if (table.getSelectedRowCount() >0)
							{
								objdb.executeNonQuery(h2con, "DELETE FROM TBL_MASTER_CONTRACTS WHERE SECID='"+table.getValueAt(table.getSelectedRow(), 0)+"';");
								String [][] values = objdb.getMultiColumnRecords(h2con, "SELECT SECID, SYMBOL, EXCHANGE, INSTRUMENT, LOTSIZE, TICKSIZE, EXPDD, EXPMONTHYEAR, OPTTYPE, STRIKE FROM TBL_MASTER_CONTRACTS ORDER BY SYMBOL;");
								model = new DefaultTableModel(values, col);
								table.setModel(model);
							}
			        }
				}
				
			});
			
			JTableHeader header = table.getTableHeader();
			header.setForeground(Color.WHITE);
			header.setBackground(Color.BLACK);
		    header.setFont(new Font("Ebrima", Font.PLAIN, 12));
		    header.setPreferredSize(new Dimension(100, HEADER_HEIGHT));
		    JScrollPane scrollPane = new JScrollPane(table);
		    scrollPane.setBounds(22, 109, 897, 410);
		    pnlmiddle.add(scrollPane);
		    scrollPane.setEnabled(false);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setViewportBorder(null);
			scrollPane.getViewport().setBackground(Color.BLACK);
			
			
			JSeparator separator = new JSeparator();
			separator.setBounds(22, 96, 897, 11);
			pnlmiddle.add(separator);
			
			String [][] values = objdb.getMultiColumnRecords(h2con, "SELECT SECID, SYMBOL, EXCHANGE, INSTRUMENT, LOTSIZE, TICKSIZE, EXPDD, EXPMONTHYEAR, OPTTYPE, STRIKE FROM TBL_MASTER_CONTRACTS ORDER BY SYMBOL;");
			model = new DefaultTableModel(values, col);
			table.setModel(model);
			
			JButton btndeleteall = new JButton("Delete All");
			btndeleteall.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					int opcion = JOptionPane.showConfirmDialog(null, "Are you sure, Want to Clear ?\n It will delete all (Including formula input, trades, dashboard ,etc) ", "Reset", JOptionPane.YES_NO_OPTION);
					if (opcion == 0)
					{
					String exitcode ="0";
			        JFrame exitframe = new JFrame("Exit Check");
			        exitcode = JOptionPane.showInputDialog(exitframe, "Enter Secret Code to Delete All #");
			        if(exitcode.equalsIgnoreCase("7"))
			        {
			        	String [] batchstmts = new String[7];
			        	batchstmts[0] = "DELETE FROM TBL_MASTER_CONTRACTS;";
			        	batchstmts[1] = "DELETE FROM TBL_TRADE_LINE;";
			        	batchstmts[2] = "DELETE FROM TBL_TRADE_INFO;";
			        	batchstmts[3] = "DELETE FROM TBL_FORMULA_DATA;";
			        	batchstmts[4] = "DELETE FROM TBL_BEAST_VIEW;";
			        	batchstmts[5] = "ALTER TABLE TBL_TRADE_LINE ALTER COLUMN ID RESTART WITH 1;";
			        	batchstmts[6] = "ALTER TABLE TBL_MASTER_CONTRACTS  ALTER COLUMN ID RESTART WITH 1;";
			        	objdb.executeBatchStatement(h2con, batchstmts);
						String [][] values = objdb.getMultiColumnRecords(h2con, "SELECT SECID, SYMBOL, EXCHANGE, INSTRUMENT, LOTSIZE, TICKSIZE, EXPDD, EXPMONTHYEAR, OPTTYPE, STRIKE FROM TBL_MASTER_CONTRACTS ORDER BY SYMBOL;");
						model = new DefaultTableModel(values, col);
						table.setModel(model);
						
			        }
					}
				}
			});
			btndeleteall.setFont(new Font("Ebrima", Font.BOLD, 12));
			btndeleteall.setBounds(370, 530, 240, 35);
			pnlmiddle.add(btndeleteall);
			KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		    Action escapeAction = new AbstractAction() {
		      public void actionPerformed(ActionEvent e) {
		    	  frmScriptSearch.dispose();
		      }
		    };
		    frmScriptSearch.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		        escapeKeyStroke, "ESCAPE");
		    frmScriptSearch.getRootPane().getActionMap().put("ESCAPE", escapeAction);
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		
		}
		
	}
}
