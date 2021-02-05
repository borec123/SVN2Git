package cz.agel.demo.primenumbers;

public class BruteForcePrimeNumberDetectorImpl extends AbstractObservableNumberDetector {

	private static BruteForcePrimeNumberDetectorImpl instance = null;

	@Override
	protected void initializePrimeNumberBitmap() {
		
		started = true;
		finished = false;
		interrupted = false;
		
		bitmap[0] = bitmap[1] = bitmap[2] = bitmap[3] = false; 
		
		//int sqrt = (int) Math.sqrt(bitmap.length);
		
		for (int i = 4; i < bitmap.length; i++) {
			
			bitmap[i] = false;

			for (int j = 2; j < i; j++) {
				
				if(i % j == 0) {
					bitmap[i] = true;
					break;
				}
				if(interrupted) {
					break;
				}
			}
			
			if(interrupted) {
				break;
			}
			progressStatus = (int)(((float)i) / bitmap.length * 100);
			/*
			 * try { Thread.sleep(1); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			
		}	
	
		if(!interrupted) {
			started = false;
			finished = true;
		}
	}

	public static BruteForcePrimeNumberDetectorImpl getInstance() {
		
		if(instance == null) {
			instance = new BruteForcePrimeNumberDetectorImpl();
		}
		
		return instance;
	}



}
