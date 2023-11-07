package telran.multithreading;

public class Counter extends Thread {
 private static int counterResource1 = 0;
 private static int counterResource2 = 0;
 private int nCounts;
 
 public Counter(int nCounts) {
	this.nCounts = nCounts;
}
 
public static int getCounterResource1() {
	return counterResource1;
}

public static int getCounterResource2() {
	return counterResource2;
}

@Override
 public void run() {
	 for (int i = 0; i < nCounts; i++) {
		 count1();
		 count2();
	 }
 }
private void count2() {
	counterResource2++;
	
}
 private void count1() {
	counterResource1++;
	
}
}
