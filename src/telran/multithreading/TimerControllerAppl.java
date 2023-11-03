package telran.multithreading;

import java.time.format.DateTimeFormatter;

public class TimerControllerAppl {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer(DateTimeFormatter.ofPattern("HH:mm:ss"), 1000);
		
		timer.start();
		//applications runs something
		Thread.sleep(5000);
		timer.interrupt();
		Thread.sleep(5000);

	}

}
