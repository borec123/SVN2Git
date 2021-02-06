package cz.agel.demo.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import cz.agel.demo.datamodel.NumbersDataModel;

public class PrimeNumberCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	private NumbersDataModel tableModel = null;

	public PrimeNumberCellRenderer(NumbersDataModel tableModel) {
		super();
		setHorizontalAlignment(JLabel.RIGHT);
		this.tableModel = tableModel;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Integer cellNumber = (Integer) value;
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		if(tableModel.isPrimeNumber(cellNumber)) {
			c.setForeground(Color.RED);
			c.setFont(c.getFont().deriveFont(Font.BOLD));
		}
		else {
			c.setForeground(Color.BLACK);
		}
		
		return c;
	}
}
