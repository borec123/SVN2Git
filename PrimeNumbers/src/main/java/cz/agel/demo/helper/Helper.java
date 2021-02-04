package cz.agel.demo.helper;

import cz.agel.demo.Constants;

public class Helper {
	
	/**
	 * Checks if a given number is inside of allowed range.
	 * @param number
	 * @param RANGE
	 */
	public static void checkRange(int number, int RANGE) {
		if (number > RANGE || number < 1) {
			throw new IndexOutOfBoundsException(
					String.format(
							"Given number: {%d} must be between 1 and max value: {%d}.",
							number, RANGE));
		}
	}
	
	public static int getMaxStartingNumber() {
		return Constants.RANGE - Constants.COLUMN_COUNT * Constants.ROW_COUNT;
	}

}
