package com.cashbot.collection;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BeastViewListTableModel extends AbstractTableModel
{
	  private List<BeastViewList> values ;
	  private String[] columns ;
	  public BeastViewListTableModel(List<BeastViewList> avalues)
	  {
		  	super();
		  	values = avalues ;
		    columns = new String[]{"ID","SCRIPT","F1 Point","F1 PL","F2 Point","F2 PL","F3 Point","F3 PL","F4 Point","F4 PL","F5 Point","F5 PL","F6 Point","F6 PL"};
	  }
	  // Number of column of your table
	  public int getColumnCount() {
	    return columns.length ;
	  }

	  // Number of row of your table
	  public int getRowsCount() {
	    return values.size();
	  }
	  @Override
	  public int getRowCount() {
		  return values.size();
	  }
	  // Optional, the name of your column
	  public String getColumnName(int col) {
	    return columns[col] ;
	  }
	  @Override
	  public Object getValueAt(int row, int col) {
		  //return values.get(row).
		  BeastViewList list = values.get(row);
		    switch(col) {
		      case 0: return list.getid();
		      case 1: return list.getHeadDisplay();
		      case 2: return list.getF1Point();
		      case 3: return list.getF1PL();
		      case 4: return list.getF2Point();
		      case 5: return list.getF2PL();
		      case 6: return list.getF3Point();
		      case 7: return list.getF3PL();
		      case 8: return list.getF4Point();
		      case 9: return list.getF4PL();
		      case 10: return list.getF5Point();
		      case 11: return list.getF5PL();
		      case 12: return list.getF6Point();
		      case 13: return list.getF6PL();
		      // to complete here...
		      default: return null;
		    }
	  }
	  @Override
	  public void fireTableDataChanged() {
	        super.fireTableDataChanged();
	    }
}