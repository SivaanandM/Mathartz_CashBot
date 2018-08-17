package com.cashbot.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.cashbot.collection.TradeinfoTableModel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

public class ManualSquareoff {

	private JFrame frmsquaroff;
	JTable tblinsights;
	TradeinfoTableModel modelinsights;
	private static int HEADER_HEIGHT = 25;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManualSquareoff window = new ManualSquareoff();
					window.frmsquaroff.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManualSquareoff() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmsquaroff = new JFrame();
		frmsquaroff.setBounds(100, 100, 871, 637);
		frmsquaroff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmsquaroff.getContentPane().setBackground(new Color(51, 51, 51));
		frmsquaroff.getContentPane().setLayout(null);
		
		JLabel lblManualSquareoff = new JLabel("Manual SquareOff");
		lblManualSquareoff.setBounds(0, 0, 855, 36);
		lblManualSquareoff.setHorizontalAlignment(SwingConstants.CENTER);
		lblManualSquareoff.setForeground(new Color(255, 220, 135));
		lblManualSquareoff.setFont(new Font("Verdana", Font.BOLD, 18));
		frmsquaroff.getContentPane().add(lblManualSquareoff);
		
		JPanel innerpanel = new JPanel();
		innerpanel.setBackground(new Color(80,75,78));
		innerpanel.setBounds(25, 47, 803, 511);
		innerpanel.setLayout(null);
		
		tblinsights = new JTable();
		
		tblinsights.setBackground(new Color(51, 51, 51));
		tblinsights.setFillsViewportHeight(true);
		tblinsights.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader header = tblinsights.getTableHeader();
		header.setForeground(new Color(255, 220, 135));
		header.setBackground(new Color(51, 51, 51));
	    header.setFont(new Font("Tahoma", Font.BOLD, 14));
	    header.setPreferredSize(new Dimension(100, HEADER_HEIGHT));
	    frmsquaroff.getContentPane().setLayout(null);
	    TableColumnModel tcm = tblinsights.getColumnModel();
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    tcm.getColumn(0).setCellRenderer(renderer);
	    tcm.getColumn(1).setCellRenderer(renderer);
	    tcm.getColumn(2).setCellRenderer(renderer);
	    tcm.getColumn(3).setCellRenderer(renderer);
	    
		

		
		frmsquaroff.getContentPane().add(innerpanel);
		//modelinsights = new TradeinfoTableModel();
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
		
		JScrollPane scrollPane = new JScrollPane(tblinsights);
		scrollPane.setBounds(10, 11, 783, 489);
		innerpanel.add(scrollPane);
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.getViewport().setBackground(new Color(51, 51, 51));
		
		frmsquaroff.setVisible(true);
		
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	    Action escapeAction = new AbstractAction() {
	      public void actionPerformed(ActionEvent e) {
	    	  frmsquaroff.dispose();
	      }
	    };
	    frmsquaroff.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	        escapeKeyStroke, "ESCAPE");
	    frmsquaroff.getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}

}
