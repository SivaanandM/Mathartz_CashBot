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

import com.cashbot.prestolib.*;
import com.cashbot.commons.CommonObjects;
import com.cashbot.commons.DbFuncs;
import com.cashbot.collection.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class ScriptSearch {

	private JFrame frmScriptSearch;
	private JTextField textSymbol;
	private JTable table;
	private Scriptsdetail records=null;
	//presto_commons objpresto;
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
			frmScriptSearch.getContentPane().setBackground(new Color(51,51,51));
			frmScriptSearch.getContentPane().setLayout(new BorderLayout(0, 0));
			frmScriptSearch.setBounds(100, 100, 945, 629);
			frmScriptSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmScriptSearch.setResizable(false);
			
			JLabel lblScriptsCrawler = new JLabel("SEC-ID STORAGE");
			lblScriptsCrawler.setHorizontalAlignment(SwingConstants.CENTER);
			lblScriptsCrawler.setForeground(new Color(255, 220, 135));
			lblScriptsCrawler.setFont(new Font("Verdana", Font.PLAIN, 24));
			frmScriptSearch.getContentPane().add(lblScriptsCrawler, BorderLayout.NORTH);
			
			JPanel pnlmiddle = new JPanel();
			frmScriptSearch.getContentPane().add(pnlmiddle, BorderLayout.CENTER);
			pnlmiddle.setLayout(null);
			pnlmiddle.setBackground(new Color(51,51,51));
			
			JPanel pnlsearchcontrol = new JPanel();
			pnlsearchcontrol.setBounds(183, 11, 601, 71);
			pnlsearchcontrol.setBackground(new Color(80,75,78));
			pnlmiddle.add(pnlsearchcontrol);
			pnlsearchcontrol.setLayout(null);
			
			JLabel lblSymbol = new JLabel("Symbol");
			lblSymbol.setBounds(52, 11, 82, 49);
			lblSymbol.setHorizontalAlignment(SwingConstants.LEFT);
			lblSymbol.setForeground(Color.WHITE);
			lblSymbol.setFont(new Font("Verdana", Font.PLAIN, 14));
			pnlsearchcontrol.add(lblSymbol);
			
			textSymbol = new JTextField();
			textSymbol.setBounds(116, 16, 171, 35);
			textSymbol.setHorizontalAlignment(SwingConstants.LEFT);
			textSymbol.setForeground(new Color(255, 220, 135));
			textSymbol.setFont(new Font("Verdana", Font.PLAIN, 20));
			textSymbol.setColumns(10);
			textSymbol.setCaretColor(Color.WHITE);
			textSymbol.setBackground(new Color(36, 34, 29));
			textSymbol.addKeyListener(new KeyAdapter() {

				  public void keyTyped(KeyEvent e) {
				    char keyChar = e.getKeyChar();
				    if (Character.isLowerCase(keyChar)) {
				      e.setKeyChar(Character.toUpperCase(keyChar));
				    }
				  }

				});
			pnlsearchcontrol.add(textSymbol);
			DateFormat df = new SimpleDateFormat("MMM-yy"); // Just the year, with 2 digits
			String formattedDate = df.format(Calendar.getInstance().getTime());
			
			JButton btnGetScriptList = new JButton("Add");
			btnGetScriptList.setFont(new Font("Tahoma", Font.BOLD, 12));
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
			btnGetScriptList.setBounds(311, 16, 235, 35);
			pnlsearchcontrol.add(btnGetScriptList);
			
			
			table = new JTable();
			table.setBounds(10, 559, 923, -345);
			table.setBackground(new Color(51, 51, 51));
			table.setFillsViewportHeight(true);
			table.setFont(new Font("Tahoma", Font.PLAIN, 15));
			table.setRowHeight(23);
			model = new DefaultTableModel(col,0);
			table = new JTable(model){
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
			        Component returnComp = super.prepareRenderer(renderer, row, column);
			        Color alternateColor = new Color(58,54,51);
			        Color whiteColor = new Color(79,75,72);
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
			header.setForeground(new Color(255, 220, 135));
			header.setBackground(new Color(51, 51, 51));
		    header.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    header.setPreferredSize(new Dimension(100, HEADER_HEIGHT));
		    JScrollPane scrollPane = new JScrollPane(table);
		    scrollPane.setBounds(22, 109, 897, 410);
		    pnlmiddle.add(scrollPane);
		    scrollPane.setEnabled(false);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setViewportBorder(null);
			scrollPane.getViewport().setBackground(new Color(51, 51, 51));
			
			
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
					objdb.executeNonQuery(h2con, "DELETE FROM TBL_MASTER_CONTRACTS;");
					String [][] values = objdb.getMultiColumnRecords(h2con, "SELECT SECID, SYMBOL, EXCHANGE, INSTRUMENT, LOTSIZE, TICKSIZE, EXPDD, EXPMONTHYEAR, OPTTYPE, STRIKE FROM TBL_MASTER_CONTRACTS ORDER BY SYMBOL;");
					model = new DefaultTableModel(values, col);
					table.setModel(model);
				}
			});
			btndeleteall.setFont(new Font("Tahoma", Font.BOLD, 12));
			btndeleteall.setBounds(370, 530, 240, 35);
			pnlmiddle.add(btndeleteall);
			
		}
		catch(Exception ex)
		{
			System.out.print(ex);
		
		}
		
	}
}
