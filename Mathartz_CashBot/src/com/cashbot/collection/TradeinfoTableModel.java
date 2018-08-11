package com.cashbot.collection;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TradeinfoTableModel extends AbstractTableModel
{
	private List<Tradeinfo> values ;
	private String[] columns ;
	public TradeinfoTableModel(List<Tradeinfo> infos)
	{
		super();
		values = infos;
		columns = new String [] {"ID","FNAME","SIDE","WAY","TIME","ORDER-ID","LTP"};
	}
	
	@Override
	public int getColumnCount() {
		return columns.length ;
	}

	@Override
	public int getRowCount() {
		return values.size();
	}
	public String getColumnName(int col) 
	{
		return columns[col] ;
	}
	//"ID","FNAME","SIDE","WAY","TIME","ORDER-ID","LTP"
	@Override
	public Object getValueAt(int row, int col) {
		Tradeinfo list = values.get(row);
	    switch(col) {
	      case 0: return list.getid();
	      case 1: return list.getFname();
	      case 2: return list.getOrdertype();
	      case 3: return list.getway();
	      case 4: return list.getfst();
	      case 5: return list.getclientorderid();
	      case 6: return list.getPrice();
	      default: return null;
	    }
	}
	 @Override
	public void fireTableDataChanged() 
	{
		super.fireTableDataChanged();
	}
}
