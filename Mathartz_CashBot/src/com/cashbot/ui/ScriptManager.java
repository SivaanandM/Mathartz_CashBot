package com.cashbot.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.cashbot.commons.CommonObjects;
import com.cashbot.commons.DbFuncs;
import com.cashbot.collection.Scriptsdetail;
import javax.swing.JButton;

public class ScriptManager {

	private JFrame frmcrawler;
	private JTextField txtsymbol;
	private String operation;
	private int seedid;
	DbFuncs dbobj;
	Connection h2con=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScriptManager window = new ScriptManager("EDIT",1);
					window.frmcrawler.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScriptManager(String opera, int identity) 
	{
		operation = opera;
		seedid = identity;
		dbobj = new DbFuncs();
		h2con=dbobj.CheckandConnectDB(h2con);
		initialize();
		List<Scriptsdetail> data=null;
		if (operation.equalsIgnoreCase("EDIT"))
		{
			txtsymbol.setText(dbobj.getSingleCell(h2con, "SELECT HEADSYMBOL FROM TBL_TRADE_LINE WHERE ID="+seedid+";"));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmcrawler = new JFrame();
		frmcrawler.setTitle("Script Manager");
		frmcrawler.setBounds(100, 100, 375, 213);
		frmcrawler.getContentPane().setLayout(null);
		frmcrawler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmcrawler.getContentPane().setBackground(new Color(51, 51, 51));
		frmcrawler.setVisible(true);
		
		JPanel innerpanel = new JPanel();
		innerpanel.setBounds(10, 41, 338, 123);
		innerpanel.setBackground(new Color(80,75,78));
		frmcrawler.getContentPane().add(innerpanel);
		innerpanel.setLayout(null);
		
		JLabel lblSymbol = new JLabel("SYMBOL");
		lblSymbol.setHorizontalAlignment(SwingConstants.LEFT);
		lblSymbol.setForeground(Color.WHITE);
		lblSymbol.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSymbol.setBounds(54, 11, 82, 49);
		innerpanel.add(lblSymbol);
		
		txtsymbol = new JTextField("");
		txtsymbol.setHorizontalAlignment(SwingConstants.CENTER);
		txtsymbol.setForeground(new Color(255, 220, 135));
		txtsymbol.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtsymbol.setColumns(10);
		txtsymbol.setCaretColor(Color.WHITE);
		txtsymbol.setBackground(new Color(36, 34, 29));
		txtsymbol.setBounds(146, 13, 156, 42);
		txtsymbol.addKeyListener(new KeyAdapter() {

			  public void keyTyped(KeyEvent e) {
			    char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			  }

			});
		innerpanel.add(txtsymbol);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (operation.toUpperCase().equals("ADD"))
				{
					ScriptAdd();
				}
				else if (operation.toUpperCase().equals("EDIT"))
				{
					ScriptEdit(seedid);
				}
				//frmcrawler.dispose();
			}
		});
		btnSave.setBounds(20, 71, 297, 38);
		innerpanel.add(btnSave);
		
		JLabel lblcaption = new JLabel("SCRIPT MANAGER");
		lblcaption.setHorizontalAlignment(SwingConstants.CENTER);
		lblcaption.setForeground(new Color(255, 220, 135));
		lblcaption.setFont(new Font("Verdana", Font.BOLD, 18));
		lblcaption.setBounds(-1, 0, 360, 43);
		frmcrawler.getContentPane().add(lblcaption);
		
	}
	
	public void ScriptAdd()
	{
		try
		{
			List<Scriptsdetail> sd = dbobj.getContractdata(h2con, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+ txtsymbol.getText()+"';");
			if (sd != null)
			{
				int identity = dbobj.getIdentityforInsert(h2con, "INSERT INTO TBL_Trade_Line (HEADID, HEADDISPLAY,"+ 
						"HEADSYMBOL, EXCHANGE, INSTRUMENT,LOTSIZE, TICKSIZE, EXPDD ,EXPMONTHYEAR , OPTTYPE , STRIKE)"
						+ " VALUES ('"+sd.get(0).getSecid()+"','"+sd.get(0).getSymbol()+"','"+sd.get(0).getSymbol()+"',"
								+ "'"+sd.get(0).getExchange()+"', '"+sd.get(0).getInstrument()+"','"+sd.get(0).getLotsize()+"',"
										+ "'"+sd.get(0).getTicksize()+"', '"+sd.get(0).getExpdd()+"','"+sd.get(0).getExpmonthyear()+"',"
												+ "'"+sd.get(0).getOpttype()+"','"+sd.get(0).getStrike()+"')");
			dbobj.executeNonQuery(h2con, "INSERT INTO TBL_BEAST_VIEW (ID, HEADDISPLAY) VALUES ("+identity+",'"+sd.get(0).getSymbol()+"');");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	public void ScriptEdit(int id)
	{
		try
		{
			List<Scriptsdetail> sd = dbobj.getContractdata(h2con, "SELECT * FROM TBL_MASTER_CONTRACTS WHERE SYMBOL='"+ txtsymbol.getText()+"';");
			if (sd != null)
			{
				dbobj.executeNonQuery(h2con, "UPDATE TBL_Trade_Line SET HEADID = '"+sd.get(0).getSecid()+"', HEADDISPLAY='"+sd.get(0).getSymbol()+"'," + 
						"HEADSYMBOL ='"+sd.get(0).getSymbol()+"', EXCHANGE='"+sd.get(0).getExchange()+"', INSTRUMENT='"+sd.get(0).getInstrument()+"',LOTSIZE='"+sd.get(0).getLotsize()+"', "
						+ "TICKSIZE='"+sd.get(0).getTicksize()+"', EXPDD='"+sd.get(0).getExpdd()+"',EXPMONTHYEAR='"+sd.get(0).getExpmonthyear()+"' , OPTTYPE ='"+sd.get(0).getOpttype()+"', STRIKE='"+sd.get(0).getStrike()+"' WHERE ID="+id+";");
				dbobj.executeNonQuery(h2con, "DELETE FROM TBL_BEAST_VIEW WHERE ID="+id+" ;");
				dbobj.executeNonQuery(h2con, "INSERT INTO TBL_BEAST_VIEW (ID, HEADDISPLAY) VALUES ("+id+",'"+sd.get(0).getSymbol()+"');");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}
	
}
