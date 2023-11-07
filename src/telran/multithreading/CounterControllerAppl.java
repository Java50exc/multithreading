package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class CounterControllerAppl {

	private static final int N_COUNTERS = 1000;
	private static final int N_COUNTS = 10000;
    
	public static void main(String[] args)throws InterruptedException {
		Counter[] counters = new Counter[N_COUNTERS];
		CounterResource counterResource1 = new CounterResource();
		CounterResource counterResource2 = new CounterResource();
		Instant start = Instant.now();
		startCounters(counters, counterResource1, counterResource2);
		waitCounters(counters);
		displayResult(start, counterResource1, counterResource2);

	}

	private static void displayResult(Instant start, CounterResource counterResource1, CounterResource counterResource2) {
		System.out.printf("Number of counts: %d; Number of counters: %d\n "
				+ "counterResource1: %d; counterResource2: %d\n"
				+ "running time: %dMs\n ", N_COUNTS, N_COUNTERS,
				counterResource1.getCounter(), counterResource2.getCounter(),
				ChronoUnit.MILLIS.between(start, Instant.now()));
		
	}

	private static void waitCounters(Counter[] counters) throws InterruptedException {
		for(Counter counter: counters) {
			counter.join();
		}
		
	}

	private static void startCounters(Counter[] counters, CounterResource counterResource1, CounterResource counterResource2) {
		for(int i = 0; i < counters.length; i++) {
			counters[i] = new Counter( counterResource1, counterResource2, N_COUNTS);
			counters[i].start();
		}
		
	}

}
