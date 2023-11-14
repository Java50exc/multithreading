package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PrinterController {

	public static void main(String[] args) throws InterruptedException {
		Worker worker1 = new Worker();
		Worker worker2 = new Worker();
		worker1.start();
		worker2.start();

	}

}
