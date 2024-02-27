package telran.multithreading.messaging;

import java.util.concurrent.*;

/**
 * Message box contains only one string
 */
public class MessageBoxMyBlockingQueue implements MessageBox{
	private MyBlockingQueue<String> messages = new MyLinkedBlockingQueue<>(1);
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
	public String pull() {
		
		
		return messages.poll();
	}

}
