package cz.agel.demo.primenumbers;

import java.util.Arrays;

public class ObservablePrimeNumberDetectorImpl extends AbstractObservableNumberDetector {
	private static final ObservablePrimeNumberDetectorImpl instance = new ObservablePrimeNumberDetectorImpl();
	
	private volatile int sqrt = -1;
	private SQRTCalcThread thread = new SQRTCalcThread();

	protected void initializePrimeNumberBitmap() {
		initializePrimeNumberBitmap1();
	}

	private ObservablePrimeNumberDetectorImpl() {
		thread.start();
	}

	private class SQRTCalcThread extends Thread {
		
		public void run() {
			
			long start = System.nanoTime();
			sqrt = (int) Math.sqrt(bitmap.length);
			long end = System.nanoTime();
			
			System.out.println("SQRT calculation took " + (end - start) / 1000 + " ms.");
		}
		
	}
	
	private void initializePrimeNumberBitmap1() {

		long start = System.nanoTime();

		started = true;
		finished = false;
		interrupted = false;
		progressStatus = 0;
		
		if(sqrt == -1) {
			try {
				thread.join();
				System.out.println("Waiting for SQRT calculation...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Arrays.fill(bitmap, Boolean.FALSE);

		bitmap[0] = bitmap[1] = true; 
		
		
		for (int i = 2; i <= sqrt; i++) {
			if (bitmap[i] == true)
				continue;
			for (int j = 2 * i; j < bitmap.length; j += i) { 
				bitmap[j] = true; 
									
				if(interrupted) {
					break;
				}
			}
			
			if(interrupted) {
				break;
			}
			progressStatus = (int)(((float)i) / sqrt * 100);
		}
		
		if(!interrupted) {
			started = false;
			finished = true;
			long end = System.nanoTime();
			System.out.println("Primenumbers calculation took " + (double)(end - start) / 1000000000 + " s.");
		}

	}

	public static ObservablePrimeNumberDetectorImpl getInstance() {

		return instance;
	}

}
