package cz.agel.demo.datamodel;

import javax.swing.table.AbstractTableModel;

import cz.agel.demo.Constants;
import cz.agel.demo.primenumbers.AbstractObservableNumberDetector;
import cz.agel.demo.primenumbers.BruteForcePrimeNumberDetectorImpl;
import cz.agel.demo.primenumbers.ObservablePrimeNumberDetectorImpl;

public class ProgressTableModel extends AbstractTableModel {

	private String status;
	//private BruteForcePrimeNumberDetectorImpl detector = BruteForcePrimeNumberDetectorImpl.getInstance();
	private ObservablePrimeNumberDetectorImpl detector = ObservablePrimeNumberDetectorImpl.getInstance();

	private class StatusReadingThread extends Thread {
		
		private AbstractObservableNumberDetector observable;

		public StatusReadingThread(AbstractObservableNumberDetector observable) {
			this.observable = observable;
		}
		
		public void run() {
			
			while(!observable.isFinished() && !observable.isInterrupted()) {
				if(observable.isStarted()) {
					status = "Stav: " + observable.getProgressStatus() + "%";
					fireTableDataChanged();
				}
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			if(observable.isFinished()) {
				status = "Hotovo.";
				fireTableDataChanged();
			}
			
		}
		
	}
	
	public int getRowCount() {
		return 2;
	}

	public int getColumnCount() {
		return 1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(rowIndex) {
		case 0:
			return String.format("Prob\u00EDh\u00E1 v\u00FDpo\u010Det prvo\u010D\u00EDsel v intervalu od 1 do %d.", Constants.RANGE);

		case 1:
			return status;

		}
		return "";
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
