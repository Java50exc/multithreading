package telran.multithreading;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.*;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 200;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBoxMyBlockingQueue();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		Receiver[] receivers = new Receiver[N_RECEIVERS];
		startReceivers(messageBox, receivers);
		sender.join();
		stopReceivers(receivers);

	}

	private static void stopReceivers(Receiver[] receivers) {
		for(Receiver receiver: receivers) {
			receiver.interrupt();
		}
		
	}

	private static void startReceivers(MessageBox messageBox, Receiver[] receivers) {
		for(int i = 0; i < N_RECEIVERS; i++) {
			receivers[i] = new Receiver(messageBox);
			receivers[i].start();
		}
	}

}
