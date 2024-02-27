package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;

	public Receiver(MessageBox messageBox) {
		this.messageBox = messageBox;
	}

	@Override
	public void run() {
		while (true) {
			String message = null;
			try {
				message = messageBox.take();
			} catch (InterruptedException e) {
				message = messageBox.pull();
				break;
			} finally {
				if (message != null) {
					System.out.printf("thread id: %d, message: %s\n", getId(), message);							
				}
			}
		}
	}
}
