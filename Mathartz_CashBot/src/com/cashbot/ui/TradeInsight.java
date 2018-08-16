package com.cashbot.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.cashbot.commons.CommonObjects;
import com.cashbot.commons.DbFuncs;
import com.cashbot.collection.BeastViewListTableModel;
import com.cashbot.collection.Tradeinfo;
import com.cashbot.collection.TradeinfoTableModel;
import com.cashbot.collection.getListcommon;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class TradeInsight {

	private JFrame frmtradeinsight;
	String col[]= {"ID","FNAME","SIDE","WAY","TIME","ORDER-ID","LTP"};
	private JTable tblinsights;
	TableModel modelinsights;
	String headname, playername, fname;
	int identity;
	private static int HEADER_HEIGHT = 25;
	DbFuncs dbobj;
	Connection h2con=null;
	getListcommon listcom = new getListcommon();
	List<Tradeinfo> tf;
	TableModel infomodel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TradeInsight window = new TradeInsight("F1", "?", 1);
					window.frmtradeinsight.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TradeInsight(String formula, String Heeddisplay, int id) {
		fname = formula;
		headname=Heeddisplay;
		identity = id;
		if (CommonObjects.isRunning == false)
		{
			dbobj =  new DbFuncs();
			tf = dbobj.getTradeInfo(h2con, "SELECT * FROM TBL_TRADE_INFO WHERE ID="+id+" AND FNAME='"+formula+"';");
		}
		else
		{
			tf= listcom.getTradeinfoByIDandFname(id, formula, CommonObjects.GlobalTradeInfo);
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmtradeinsight = new JFrame();
		frmtradeinsight.setTitle("Trade Insight - "+fname);
		frmtradeinsight.setBounds(100, 100, 519, 515);
		frmtradeinsight.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmtradeinsight.getContentPane().setBackground(new Color(51,51,51));
		frmtradeinsight.setVisible(true);
		
		tblinsights = new JTable();
		tblinsights.setBounds(10, 559, 923, -345);
		tblinsights.setBackground(new Color(51, 51, 51));
		tblinsights.setFillsViewportHeight(true);
		tblinsights.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modelinsights = new TradeinfoTableModel(tf);
		tblinsights = new JTable(modelinsights){
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
		tblinsights.setBorder(null);
		tblinsights.setRowHeight(20);
		
		JTableHeader header = tblinsights.getTableHeader();
		header.setForeground(new Color(255, 220, 135));
		header.setBackground(new Color(51, 51, 51));
	    header.setFont(new Font("Tahoma", Font.BOLD, 14));
	    header.setPreferredSize(new Dimension(100, HEADER_HEIGHT));
	    frmtradeinsight.getContentPane().setLayout(null);
	    JScrollPane scrollPane = new JScrollPane(tblinsights);
	    scrollPane.setBounds(10, 53, 483, 413);
	    frmtradeinsight.getContentPane().add(scrollPane);
	    scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.getViewport().setBackground(new Color(51, 51, 51));
		TableColumnModel tcm = tblinsights.getColumnModel();
		tcm.removeColumn( tcm.getColumn(0) );
		tcm.removeColumn( tcm.getColumn(0) );
		tcm.removeColumn( tcm.getColumn(3) );
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    tcm.getColumn(0).setCellRenderer(renderer);
	    tcm.getColumn(1).setCellRenderer(renderer);
	    tcm.getColumn(2).setCellRenderer(renderer);
	    tcm.getColumn(3).setCellRenderer(renderer);
	    
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBounds(250, 11, 10, 31);
		frmtradeinsight.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("HEAD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(30, 11, 191, 18);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		frmtradeinsight.getContentPane().add(lblNewLabel);
		
		JLabel lblhead = new JLabel(headname);
		lblhead.setHorizontalAlignment(SwingConstants.CENTER);
		lblhead.setForeground(new Color(255, 220, 135));
		lblhead.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblhead.setBounds(282, 13, 191, 18);
		frmtradeinsight.getContentPane().add(lblhead);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(20, 40, 473, 16);
		frmtradeinsight.getContentPane().add(separator_1);

	}
}
