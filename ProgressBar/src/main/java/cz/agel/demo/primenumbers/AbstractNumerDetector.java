package cz.agel.demo.primenumbers;

import java.util.Arrays;

import cz.agel.demo.Constants;
import cz.agel.demo.helper.Helper;

public abstract class AbstractNumerDetector implements PrimeNumberDetector {

	protected boolean[] bitmap = new boolean[Constants.RANGE];

	
	public boolean isPrimeNumber(final int number) {
		Helper.checkRange(number, Constants.RANGE);
		return !bitmap[number];
	}

	protected AbstractNumerDetector() {
		//initializePrimeNumberBitmap();
		Arrays.fill(bitmap, Boolean.TRUE);
	}

	protected abstract void initializePrimeNumberBitmap();
	

}
