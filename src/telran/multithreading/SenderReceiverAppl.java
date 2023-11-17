package telran.multithreading;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.*;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBoxString();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		for(int i = 0; i < N_RECEIVERS; i++) {
			new Receiver(messageBox).start();
		}
		sender.join();
		Thread.sleep(100); //to give all receivers-daemons process all messages FIXME HW #46 should be another logic of stopping receivers

	}

}
