package cz.agel.demo.primenumbers;

import cz.agel.demo.Constants;

public class PrimeNumberDetectorImpl extends AbstractNumerDetector {

	private static PrimeNumberDetectorImpl instance = null;



	protected void initializePrimeNumberBitmap() {
		initializePrimeNumberBitmap1();
	}


	private void initializePrimeNumberBitmap1() {
		int range = Constants.RANGE - 1;

		long sqrt = (long) (Math.sqrt(range));

		for (int j = 3; j < sqrt; j += 2) {
			if (bitmap[j] == false) {
				for (int k = j; k <= range / j; k += 2) {
					bitmap[k * j] = true;
				}

			}
		}
		for (int l = 4; l < range; l += 2) {
			bitmap[l] = true;
		}
		bitmap[0] = bitmap[1] = true;
	}

	public static PrimeNumberDetectorImpl getInstance() {
		if (instance == null) {
			instance = new PrimeNumberDetectorImpl();
		}
		return instance;
	}

}
