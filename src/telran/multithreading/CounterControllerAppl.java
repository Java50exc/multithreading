package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class CounterControllerAppl {

	private static final int N_COUNTERS = 1000;
	private static final int N_COUNTS = 100000;

	public static void main(String[] args)throws InterruptedException {
		Counter[] counters = new Counter[N_COUNTERS];
		Instant start = Instant.now();
		startCounters(counters);
		waitCounters(counters);
		displayResult(start);

	}

	private static void displayResult(Instant start) {
		System.out.printf("Number of counts: %d; Number of counters: %d\n "
				+ "counterResource1: %d; counterResource2: %d\n"
				+ "running time: %dMs\n ", N_COUNTS, N_COUNTERS,
				Counter.getCounterResource1(), Counter.getCounterResource2(),
				ChronoUnit.MILLIS.between(start, Instant.now()));
		
	}

	private static void waitCounters(Counter[] counters) throws InterruptedException {
		for(Counter counter: counters) {
			counter.join();
		}
		
	}

	private static void startCounters(Counter[] counters) {
		for(int i = 0; i < counters.length; i++) {
			counters[i] = new Counter(N_COUNTS);
			counters[i].start();
		}
		
	}

}
