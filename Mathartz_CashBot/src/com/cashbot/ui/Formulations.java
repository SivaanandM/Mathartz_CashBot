package com.cashbot.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.cashbot.commons.DbFuncs;
import com.cashbot.collection.FormulaData;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Formulations {

	private JFrame frmformula;
	private JTextField txtx;
	private JTextField txty;
	private JTextField txtsthh;
	private JTextField txtmthh;
	private JTextField txtethh;
	private JTextField txtstmm;
	private JTextField txtmtmm;
	private JTextField txtetmm;
	private JTextField txtlcount;
	private JTextField txtround;
	private JTextField txtqty;
	JButton btnsave;
	String Fname , HeadName;
	int Identity;
	DbFuncs objdb;
	Connection h2con=null;
	JCheckBox chckbxTradeSwitch;
	JLabel lbloff,lblon;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulations window = new Formulations("F1","?",1);
					window.frmformula.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Formulations(String Formula, String Headdisplay, int id) {
		Fname =Formula;
		HeadName = Headdisplay;
		Identity = id;
		objdb = new DbFuncs();
		h2con = objdb.CheckandConnectDB(h2con);
		initialize();
		
		//Load Existing Data
		List<FormulaData> fd = objdb.getFormulaData(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='"+Fname+"' and ID="+Identity+";");
		if (fd.size() > 0)
		{
			txtx.setText(String.valueOf(fd.get(0).getX()));
			txty.setText(String.valueOf(fd.get(0).getY()));
			txtsthh.setText(String.valueOf(fd.get(0).getST().split(":")[0]));
			txtstmm.setText(String.valueOf(fd.get(0).getST().split(":")[1]));
			txtmthh.setText(String.valueOf(fd.get(0).getMT().split(":")[0]));
			txtmtmm.setText(String.valueOf(fd.get(0).getMT().split(":")[1]));
			txtethh.setText(String.valueOf(fd.get(0).getET().split(":")[0]));
			txtetmm.setText(String.valueOf(fd.get(0).getET().split(":")[1]));
			txtlcount.setText(String.valueOf(fd.get(0).getLcount()));
			txtround.setText(String.valueOf(fd.get(0).getRound()));
			txtqty.setText(String.valueOf(fd.get(0).getQty()));
			chckbxTradeSwitch.setSelected(Boolean.parseBoolean(String.valueOf(fd.get(0).getTradeswitch())));
			
			if (chckbxTradeSwitch.isSelected())
	        {
	        	lblon.setVisible(true);
	        	lbloff.setVisible(false);
	        }
	        else
	        {
	        	lblon.setVisible(false);
	        	lbloff.setVisible(true);
	        }
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Formula Pane Format
		InputHandler keyhand = new InputHandler();
		frmformula = new JFrame("Formula Inputs - "+ Fname);
		frmformula.getContentPane().setBackground(new Color(51, 51, 51));
		frmformula.setVisible(true);
		frmformula.setBounds(100, 100, 305, 623);
		frmformula.setBackground(new Color(36,34,29));
		frmformula.getContentPane().setLayout(null);
		frmformula.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmformula.setResizable(false);
		frmformula.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(80, 75, 78));
		panel.setBounds(10, 43, 279, 541);
		frmformula.getContentPane().add(panel);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.LEFT);
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblX.setBounds(118, 6, 50, 49);
		panel.add(lblX);
		
		JLabel lblY = new JLabel("Y");
		lblY.setHorizontalAlignment(SwingConstants.LEFT);
		lblY.setForeground(Color.WHITE);
		lblY.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblY.setBounds(120, 56, 65, 49);
		panel.add(lblY);
		
		JLabel lblSt = new JLabel("ST");
		lblSt.setHorizontalAlignment(SwingConstants.LEFT);
		lblSt.setForeground(Color.WHITE);
		lblSt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblSt.setBounds(104, 109, 39, 49);
		panel.add(lblSt);
		
		JLabel lblMt = new JLabel("MT");
		lblMt.setHorizontalAlignment(SwingConstants.LEFT);
		lblMt.setForeground(Color.WHITE);
		lblMt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblMt.setBounds(104, 157, 64, 45);
		panel.add(lblMt);
		
		JLabel lblEt = new JLabel("ET");
		lblEt.setHorizontalAlignment(SwingConstants.LEFT);
		lblEt.setForeground(Color.WHITE);
		lblEt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEt.setBounds(104, 206, 81, 49);
		panel.add(lblEt);
		
		JLabel lblLossCnt = new JLabel("LCOUNT");
		lblLossCnt.setHorizontalAlignment(SwingConstants.LEFT);
		lblLossCnt.setForeground(Color.WHITE);
		lblLossCnt.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblLossCnt.setBounds(52, 257, 129, 49);
		panel.add(lblLossCnt);
		
		JLabel lblRound = new JLabel("ROUND");
		lblRound.setHorizontalAlignment(SwingConstants.LEFT);
		lblRound.setForeground(Color.WHITE);
		lblRound.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblRound.setBounds(62, 312, 129, 49);
		panel.add(lblRound);
		
		txtx = new JTextField();
		txtx.setHorizontalAlignment(SwingConstants.RIGHT);
		txtx.setForeground(new Color(255, 220, 135));
		txtx.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtx.setColumns(10);
		txtx.setCaretColor(Color.WHITE);
		txtx.setBackground(new Color(36, 34, 29));
		txtx.setBounds(143, 10, 81, 41);
		panel.add(txtx);
		
		txty = new JTextField();
		txty.setHorizontalAlignment(SwingConstants.RIGHT);
		txty.setForeground(new Color(255, 220, 135));
		txty.setFont(new Font("Verdana", Font.PLAIN, 20));
		txty.setColumns(10);
		txty.setCaretColor(Color.WHITE);
		txty.setBackground(new Color(36, 34, 29));
		txty.setBounds(143, 61, 81, 39);
		panel.add(txty);
		
		txtsthh = new JTextField();
		txtsthh.setHorizontalAlignment(SwingConstants.CENTER);
		txtsthh.setForeground(new Color(255, 220, 135));
		txtsthh.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtsthh.setColumns(10);
		txtsthh.setCaretColor(Color.WHITE);
		txtsthh.setBackground(new Color(36, 34, 29));
		txtsthh.setBounds(143, 109, 48, 42);
		panel.add(txtsthh);
		
		txtmthh = new JTextField();
		txtmthh.setText("15");
		txtmthh.setHorizontalAlignment(SwingConstants.CENTER);
		txtmthh.setForeground(new Color(255, 220, 135));
		txtmthh.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtmthh.setColumns(10);
		txtmthh.setCaretColor(Color.WHITE);
		txtmthh.setBackground(new Color(36, 34, 29));
		txtmthh.setBounds(143, 158, 48, 42);
		panel.add(txtmthh);
		
		txtethh = new JTextField();
		txtethh.setText("15");
		txtethh.setHorizontalAlignment(SwingConstants.CENTER);
		txtethh.setForeground(new Color(255, 220, 135));
		txtethh.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtethh.setColumns(10);
		txtethh.setCaretColor(Color.WHITE);
		txtethh.setBackground(new Color(36, 34, 29));
		txtethh.setBounds(143, 209, 48, 42);
		panel.add(txtethh);
		
		JLabel label_9 = new JLabel(":");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_9.setBounds(192, 103, 7, 49);
		panel.add(label_9);
		
		txtstmm = new JTextField();
		txtstmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtstmm.setForeground(new Color(255, 220, 135));
		txtstmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtstmm.setColumns(10);
		txtstmm.setCaretColor(Color.WHITE);
		txtstmm.setBackground(new Color(36, 34, 29));
		txtstmm.setBounds(199, 109, 48, 42);
		panel.add(txtstmm);
		
		JLabel label_11 = new JLabel(":");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_11.setBounds(192, 152, 7, 49);
		panel.add(label_11);
		
		txtmtmm = new JTextField();
		txtmtmm.setText("00");
		txtmtmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtmtmm.setForeground(new Color(255, 220, 135));
		txtmtmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtmtmm.setColumns(10);
		txtmtmm.setCaretColor(Color.WHITE);
		txtmtmm.setBackground(new Color(36, 34, 29));
		txtmtmm.setBounds(199, 158, 48, 42);
		panel.add(txtmtmm);
		
		JLabel label_13 = new JLabel(":");
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Dialog", Font.PLAIN, 22));
		label_13.setBounds(192, 206, 7, 49);
		panel.add(label_13);
		
		txtetmm = new JTextField();
		txtetmm.setText("18");
		txtetmm.setHorizontalAlignment(SwingConstants.CENTER);
		txtetmm.setForeground(new Color(255, 220, 135));
		txtetmm.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtetmm.setColumns(10);
		txtetmm.setCaretColor(Color.WHITE);
		txtetmm.setBackground(new Color(36, 34, 29));
		txtetmm.setBounds(201, 209, 48, 42);
		panel.add(txtetmm);
		
		txtlcount = new JTextField();
		txtlcount.setText("2");
		txtlcount.setHorizontalAlignment(SwingConstants.RIGHT);
		txtlcount.setForeground(new Color(255, 220, 135));
		txtlcount.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtlcount.setColumns(10);
		txtlcount.setCaretColor(Color.WHITE);
		txtlcount.setBackground(new Color(36, 34, 29));
		txtlcount.setBounds(143, 257, 104, 49);
		panel.add(txtlcount);
		
		txtround = new JTextField();
		txtround.setText("3");
		txtround.setHorizontalAlignment(SwingConstants.RIGHT);
		txtround.setForeground(new Color(255, 220, 135));
		txtround.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtround.setColumns(10);
		txtround.setCaretColor(Color.WHITE);
		txtround.setBackground(new Color(36, 34, 29));
		txtround.setBounds(143, 312, 104, 49);
		panel.add(txtround);
		
		JLabel label_17 = new JLabel("%");
		label_17.setHorizontalAlignment(SwingConstants.LEFT);
		label_17.setForeground(Color.WHITE);
		label_17.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_17.setBounds(228, 8, 24, 44);
		panel.add(label_17);
		
		JLabel label_18 = new JLabel("%");
		label_18.setHorizontalAlignment(SwingConstants.LEFT);
		label_18.setForeground(Color.WHITE);
		label_18.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_18.setBounds(226, 55, 24, 49);
		panel.add(label_18);
		
		btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (objdb.getRowCount(h2con, "SELECT * FROM TBL_FORMULA_DATA WHERE FNAME='"+Fname+"' and ID="+Identity+";") == 0)
				{
					String insert = "INSERT INTO TBL_FORMULA_DATA (ID,FNAME,X,Y,ST,MT,ET,LCOUNT,ROUND,QTY,TRADESWITCH, ISEND) VALUES"
							+ " ("+Identity+",'"+Fname+"',"+txtx.getText()+","+txty.getText()+",'"+txtsthh.getText()+":"+txtstmm.getText()+"'"
									+ ", '"+txtmthh.getText()+":"+txtmtmm.getText()+"','"+txtethh.getText()+":"+txtetmm.getText()+"',"+txtlcount.getText()+","
											+ txtround.getText() +","+txtqty.getText()+","+chckbxTradeSwitch.isSelected()+",false);";
					objdb.executeNonQuery(h2con, insert);
				}
				else
				{
					String update ="UPDATE TBL_FORMULA_DATA SET X="+txtx.getText()+", Y="+txty.getText()+",ST='"+txtsthh.getText()+":"+txtstmm.getText()+"',"
							+ " MT='"+txtmthh.getText()+":"+txtmtmm.getText()+"', ET='"+txtethh.getText()+":"+txtetmm.getText()+"',"
									+ "LCOUNT="+txtlcount.getText()+", ROUND="+txtround.getText()+", QTY="+txtqty.getText()+", TRADESWITCH="+chckbxTradeSwitch.isSelected()+" "
											+ ", ISEND=false WHERE FNAME='"+Fname+"' and ID="+Identity+";";
					objdb.executeNonQuery(h2con, update);
				}
				frmformula.dispose();
			}
		});
		btnsave.setBounds(10, 484, 256, 45);
		panel.add(btnsave);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBounds(10, 418, 259, 55);
		panel.add(panel_1);
		
		JLabel label_20 = new JLabel("BUY & SELL");
		label_20.setFont(new Font("Verdana", Font.PLAIN, 18));
		label_20.setBackground(Color.WHITE);
		label_20.setBounds(0, 0, 0, 0);
		panel_1.add(label_20);
		
		JLabel lblTradeSwitch = new JLabel("Trade");
		lblTradeSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblTradeSwitch.setForeground(new Color(255, 220, 135));
		lblTradeSwitch.setFont(new Font("Verdana", Font.PLAIN, 22));
		lblTradeSwitch.setBounds(37, 11, 90, 28);
		panel_1.add(lblTradeSwitch);
		
		lbloff = new JLabel("OFF");
		lbloff.setHorizontalAlignment(SwingConstants.CENTER);
		lbloff.setForeground(Color.GREEN);
		lbloff.setFont(new Font("Verdana", Font.PLAIN, 18));
		lbloff.setBounds(160, 10, 60, 32);
		lbloff.setVisible(true);
		panel_1.add(lbloff);
		
		chckbxTradeSwitch = new JCheckBox("");
		chckbxTradeSwitch.setBackground(new Color(51,51,51));
		chckbxTradeSwitch.setForeground(new Color(255, 220, 135));
		chckbxTradeSwitch.setFont(new Font("Verdana", Font.PLAIN, 22));
		chckbxTradeSwitch.setBounds(133, 11, 21, 32);
		panel_1.add(chckbxTradeSwitch);
		
		lblon = new JLabel("ON");
		lblon.setBounds(160, 10, 60, 32);
		panel_1.add(lblon);
		lblon.setHorizontalAlignment(SwingConstants.CENTER);
		lblon.setForeground(Color.RED);
		lblon.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblon.setVisible(false);
		chckbxTradeSwitch.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent e) 
		      {
		        System.out.println("Checked? " + chckbxTradeSwitch.isSelected());
		        if (chckbxTradeSwitch.isSelected())
		        {
		        	lblon.setVisible(true);
		        	lbloff.setVisible(false);
		        }
		        else
		        {
		        	lblon.setVisible(false);
		        	lbloff.setVisible(true);
		        }
		      }
		    });
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblQty.setForeground(Color.WHITE);
		lblQty.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblQty.setBounds(92, 365, 99, 42);
		panel.add(lblQty);
		
		txtqty = new JTextField();
		txtqty.setText("2");
		txtqty.setHorizontalAlignment(SwingConstants.RIGHT);
		txtqty.setForeground(new Color(255, 220, 135));
		txtqty.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtqty.setColumns(10);
		txtqty.setCaretColor(Color.WHITE);
		txtqty.setBackground(new Color(36, 34, 29));
		txtqty.setBounds(143, 366, 104, 41);
		panel.add(txtqty);
		
		JLabel lblScript = new JLabel("SCRIPT - ");
		lblScript.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScript.setForeground(Color.WHITE);
		lblScript.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblScript.setBounds(0, 11, 145, 29);
		frmformula.getContentPane().add(lblScript);
		
		JLabel lblheadname = new JLabel(HeadName);
		lblheadname.setHorizontalAlignment(SwingConstants.LEFT);
		lblheadname.setForeground(new Color(255, 220, 135));
		lblheadname.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblheadname.setBounds(155, 11, 144, 29);
		frmformula.getContentPane().add(lblheadname);
		frmformula.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmformula.addKeyListener(keyhand);
	}
	
	// Key input handlers
	class InputHandler implements KeyListener
	{
        public void keyTyped(KeyEvent kt)
        {
        }
        public void keyPressed(java.awt.event.KeyEvent evt)
        {
    		if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
    		{
        			frmformula.dispose();
            }
        }
        public void keyReleased(KeyEvent kr)
        {
        }
	}
}
