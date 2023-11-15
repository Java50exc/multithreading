package telran.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListOperationsAppl {

	private static final int UPDATE_PROB = 0;
	private static final int N_OPERATIONS = 1000;
	private static final int N_NUMBERS = 1000;
	private static final int N_THREADS = 100;

	public static void main(String[] args) throws InterruptedException {
		ModelData modelData = new ModelData(UPDATE_PROB, N_OPERATIONS);
		ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		AtomicInteger locksCounter = new AtomicInteger(0);
		Monitor monitor = new Monitor(readWriteLock.readLock(), readWriteLock.writeLock(), locksCounter);
		Integer[] array = new Integer[N_NUMBERS];
		Arrays.fill(array, 100);
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
		ListOperations[] threads = new ListOperations[N_THREADS];
		startThreads(threads, modelData, list, monitor);
		joinThreads(threads);
		System.out.println("counter of locks: " + locksCounter);

	}

	private static void joinThreads(ListOperations[] threads) throws InterruptedException {
		for(ListOperations listOperations: threads) {
			listOperations.join();
		}
		
	}

	private static void startThreads(ListOperations[] threads, ModelData modelData, ArrayList<Integer> list, Monitor monitor) {
		for (int i = 0; i < N_THREADS; i++) {
			threads[i] = new ListOperations(modelData, list, monitor);
			threads[i].start();
		}
		
	}

}
