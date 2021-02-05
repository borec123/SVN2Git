package cz.agel.demo.datamodel;

import javax.swing.table.AbstractTableModel;

import cz.agel.demo.Constants;
import cz.agel.demo.helper.Helper;
import cz.agel.demo.primenumbers.AbstractObservableNumberDetector;
import cz.agel.demo.primenumbers.BruteForcePrimeNumberDetectorImpl;

public class ApplicationDataModelImpl extends AbstractTableModel implements ApplicationDataModel {

	private static final long serialVersionUID = 1L;
	
	private BruteForcePrimeNumberDetectorImpl detector = BruteForcePrimeNumberDetectorImpl.getInstance();

	public ApplicationDataModelImpl() {
		super();
		setStartingNumber(1);
	}

	private int[][] numbers = new int[Constants.COLUMN_COUNT][Constants.ROW_COUNT];

	
	private class StatusReadingThread extends Thread {
		
		private AbstractObservableNumberDetector observable;

		public StatusReadingThread(AbstractObservableNumberDetector observable) {
			this.observable = observable;
		}
		
		public void run() {
			
			while(!observable.isFinished() && !observable.isInterrupted()) {
				if(observable.isStarted()) {
					System.out.println("Status: " + observable.getProgressStatus() + "%");
				}
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			if(observable.isFinished()) {
				System.out.println("Status: 100%");
			}
			
		}
		
	}
	
	public int getColumnCount() {
		
		return Constants.COLUMN_COUNT;
	}

	
	public int getRowCount() {
		
		return Constants.ROW_COUNT;
	}

	
	public Object getValueAt(int arg0, int arg1) {
		return numbers[arg0][arg1];
	}

	
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

	
	public boolean isPrimeNumber(int number) {
		return detector.isPrimeNumber(number);
	}


	public void startCountingPrimeNumbers() {
		StatusReadingThread reader = new StatusReadingThread(detector);
		detector.start();
		reader.start();
		
	}


	public void interruptCountingPrimeNumbers() {
		detector.interrupt();
		
	}


}
