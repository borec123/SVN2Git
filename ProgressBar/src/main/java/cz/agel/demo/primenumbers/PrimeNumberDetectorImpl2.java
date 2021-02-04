package cz.agel.demo.primenumbers;

public class PrimeNumberDetectorImpl2 extends AbstractNumerDetector {

	private static PrimeNumberDetectorImpl2 instance = null;

	@Override
	protected void initializePrimeNumberBitmap() {
		bitmap[0] = bitmap[1] = true; 
		
		int sqrt = (int) Math.sqrt(bitmap.length);
		
		for (int i = 2; i <= sqrt; i++) {
			if (bitmap[i] == true)
				continue;
			for (int j = 2 * i; j < bitmap.length; j += i) { 
				bitmap[j] = true; 
									
			}
		}
	}

	public static PrimeNumberDetectorImpl2 getInstance() {
		if (instance  == null) {
			instance = new PrimeNumberDetectorImpl2();
		}
		return instance;
	}

}
