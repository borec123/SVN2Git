package cz.agel.demo.datamodel;

import javax.swing.table.TableModel;

import cz.agel.demo.primenumbers.PrimeNumberDetector;

/**
 * This interface represents data model layer. It provides services for GUI layer.
 */
public interface NumbersDataModel extends PrimeNumberDetector, TableModel {
	
	void setStartingNumber(int number);
	
	int getStartingNumber();
	
}
