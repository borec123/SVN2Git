package cz.agel.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import cz.agel.demo.primenumbers.PrimeNumberDetector;

public abstract class AbstractPrimeNumberDetectorTest {
	protected static final int PRIME_NUMBER_COUNT = 1240;
	
	protected PrimeNumberDetector detector = getInstance(); 

	protected abstract PrimeNumberDetector getInstance();
	
	@Test
	public void test() {
		
		assertTrue(detector.isPrimeNumber(2));
		assertTrue(detector.isPrimeNumber(3));
		assertTrue(detector.isPrimeNumber(5));
		assertTrue(detector.isPrimeNumber(7));
		assertTrue(detector.isPrimeNumber(11));
		
		//assertFalse(detector.isPrimeNumber(0));
		assertFalse(detector.isPrimeNumber(1));
		assertFalse(detector.isPrimeNumber(6));
		assertFalse(detector.isPrimeNumber(9));
		assertFalse(detector.isPrimeNumber(12));
		
		try {
			detector.isPrimeNumber(Constants.RANGE + 100);
			fail("Exception should be thrown.");
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("OK");
		}
	}

	@Test
	public void testCount() {
		
		int count = 0;
		
		for (int i = 1; i < Constants.RANGE; i++) {
			if(detector.isPrimeNumber(i)) {
				count ++;
			}
		}
		
		System.out.println(count);
		assertTrue(count == PRIME_NUMBER_COUNT);
	}


}
