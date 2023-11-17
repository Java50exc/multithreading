package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;

	public Receiver(MessageBox messageBox) {
		this.messageBox = messageBox;
		//FIXME HW #46 fix setting daemon
		setDaemon(true); //HW #46 remove it
	}
	@Override
	public void run() {
		while(true) {
			String message = null;
			try {
				message = messageBox.take();
			} catch (InterruptedException e) {
				// TODO 
			}
			System.out.printf("thread id: %d, message: %s\n", getId(),message );
		}
	}
}
