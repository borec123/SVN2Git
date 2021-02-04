package cz.agel.demo.datamodel;

import javax.swing.table.AbstractTableModel;

import cz.agel.demo.Constants;
import cz.agel.demo.helper.Helper;
import cz.agel.demo.primenumbers.PrimeNumberDetectorImpl;

public class ApplicationDataModelImpl extends AbstractTableModel implements ApplicationDataModel {

	private static final long serialVersionUID = 1L;
	
	private PrimeNumberDetectorImpl detector = PrimeNumberDetectorImpl.getInstance();

	public ApplicationDataModelImpl() {
		super();
		setStartingNumber(1);
	}

	private int[][] numbers = new int[Constants.COLUMN_COUNT][Constants.ROW_COUNT];

	@Override
	public int getColumnCount() {
		
		return Constants.COLUMN_COUNT;
	}

	@Override
	public int getRowCount() {
		
		return Constants.ROW_COUNT;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return numbers[arg0][arg1];
	}

	@Override
	public void setStartingNumber(int number) {
		Helper.checkRange(number, Helper.getMaxStartingNumber());
		for (int i = 0; i < Constants.COLUMN_COUNT; i++) {
			for (int j = 0; j < Constants.ROW_COUNT; j++) {
				numbers[i][j] = number++;
			}
		}
		fireTableDataChanged();
	}

	public int getStartingNumber() {

		return numbers[0][0];
	}

	@Override
	public boolean isPrimeNumber(int number) {
		return detector.isPrimeNumber(number);
	}


}
