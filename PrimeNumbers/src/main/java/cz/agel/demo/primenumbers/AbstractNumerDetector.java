package cz.agel.demo.primenumbers;

import cz.agel.demo.Constants;
import cz.agel.demo.helper.Helper;

public abstract class AbstractNumerDetector implements PrimeNumberDetector {

	protected boolean[] bitmap = new boolean[Constants.RANGE];

	@Override
	public boolean isPrimeNumber(final int number) {
		Helper.checkRange(number, Constants.RANGE);
		return !bitmap[number];
	}

	protected AbstractNumerDetector() {
		initializePrimeNumberBitmap();
	}

	protected abstract void initializePrimeNumberBitmap();
	

}
