package telran.multithreading;

public class Counter extends Thread {
 private CounterResource counterResource1;
 private CounterResource counterResource2;

 private int nCounts;
 
 
 
public Counter(CounterResource counterResource1, CounterResource counterResource2, int nCounts) {
	this.counterResource1 = counterResource1;
	this.counterResource2 = counterResource2;
	this.nCounts = nCounts;
}

public  int getCounterResource1() {
	return counterResource1.getCounter();
}

public  int getCounterResource2() {
	return counterResource2.getCounter();
}

@Override
 public void run() {
	 for (int i = 0; i < nCounts; i++) {
		 count1();
		 count2();
	 }
 }
private void count2() {
		counterResource2.increment();
	
}
 private void count1() {
		counterResource1.increment();
	
}
}
