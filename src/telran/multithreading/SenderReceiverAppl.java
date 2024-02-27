package telran.multithreading;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.*;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 1000;
	private static final int N_RECEIVERS = 3;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBoxString();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		Receiver[] receivers = new Receiver[N_RECEIVERS];
		sender.start();
		for(int i = 0; i < N_RECEIVERS; i++) {
			receivers[i] = new Receiver(messageBox);
			receivers[i].start();
			receivers[i].join();
		}
		sender.join();
		
		for(int i = 0; i < N_RECEIVERS; i++) {
			receivers[i].interrupt();
		}
	}

}
