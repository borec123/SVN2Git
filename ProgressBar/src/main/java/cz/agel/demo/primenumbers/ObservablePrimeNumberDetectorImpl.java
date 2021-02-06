package cz.agel.demo.primenumbers;

public class ObservablePrimeNumberDetectorImpl extends AbstractObservableNumberDetector {
	private static final ObservablePrimeNumberDetectorImpl instance = new ObservablePrimeNumberDetectorImpl();

	protected void initializePrimeNumberBitmap() {
		initializePrimeNumberBitmap1();
	}

	private ObservablePrimeNumberDetectorImpl() {
		
	}

	private void initializePrimeNumberBitmap1() {

		started = true;
		finished = false;
		interrupted = false;

		
		bitmap[0] = bitmap[1] = true; 
		
		int sqrt = (int) Math.sqrt(bitmap.length);
		
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
		}
	}

	public static ObservablePrimeNumberDetectorImpl getInstance() {

		return instance;
	}

}
