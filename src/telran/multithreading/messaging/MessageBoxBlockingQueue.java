package telran.multithreading.messaging;

import java.util.concurrent.*;

/**
 * Message box contains only one string
 */
public class MessageBoxBlockingQueue implements MessageBox{
	private BlockingQueue<String> messages = new LinkedBlockingQueue<>(1);
	@Override
	 public void put(String message)  {
		try {
			messages.put(message);
		} catch (InterruptedException e) {
			
		}
		
	}

	@Override
	 public String take() throws InterruptedException {
		return messages.take();
	}

	@Override
	synchronized public String pull() {
		
		
		return messages.poll();
	}

}
