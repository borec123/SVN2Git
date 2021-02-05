package cz.agel.demo.primenumbers;

public abstract class AbstractObservableNumberDetector extends AbstractNumerDetector {

	
	protected volatile boolean started = false;
	protected volatile boolean finished = false;
	protected volatile boolean interrupted = false;
	protected volatile int progressStatus = -1;
	
	private PrimeNumbersCountingThread primeNumbersCountingThread = null;
	
	@Override
	protected abstract void initializePrimeNumberBitmap() ;
	
	private class PrimeNumbersCountingThread extends Thread {
		
		public void run() {
			
			initializePrimeNumberBitmap() ;
			
		}
		
	}
	
	public void start() {
		interrupted = false;
		primeNumbersCountingThread = new PrimeNumbersCountingThread();
		primeNumbersCountingThread.start();
		
	}
	
	public void interrupt() {
		interrupted = true;
		started = false;
		//primeNumbersCountingThread.interrupt();
		//primeNumbersCountingThread.setDaemon(true);
	}

	public boolean isStarted() {
		return started;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isInterrupted() {
		return interrupted;
	}

	public int getProgressStatus() {
		return progressStatus;
	}
	

}
