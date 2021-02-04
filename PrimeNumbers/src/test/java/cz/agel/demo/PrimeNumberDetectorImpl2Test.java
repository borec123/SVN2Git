package cz.agel.demo;

import cz.agel.demo.primenumbers.AbstractNumerDetector;
import cz.agel.demo.primenumbers.PrimeNumberDetectorImpl;

public class PrimeNumberDetectorImpl2Test extends
		AbstractPrimeNumberDetectorTest {

	@Override
	protected AbstractNumerDetector getInstance() {
		return PrimeNumberDetectorImpl.getInstance();
	}

}
