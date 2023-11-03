package telran.multithreading;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
	private static final long DEFAULT_TIMEOUT = 1000;
	private static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
	private DateTimeFormatter dtf ;
	private long timeout ;
	public Timer(DateTimeFormatter dtf, long timeout) {
		this.dtf = dtf;
		this.timeout = timeout;
		setDaemon(true);
	}
	public Timer() {
		this(DEFAULT_TIME_FORMATTER, DEFAULT_TIMEOUT);
	}
	@Override
	public void run() {
		boolean running = true;
		while(running) {
			System.out.println(LocalTime.now().format(dtf));
			try {
				sleep(timeout);
			} catch (InterruptedException e) {
				System.out.println("timer finished"); 
				running = false;
			}
		}
	}
	
}
