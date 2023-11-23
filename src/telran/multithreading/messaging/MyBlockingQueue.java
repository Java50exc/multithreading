package telran.multithreading.messaging;

import java.util.concurrent.TimeUnit;

/**
 * description of all methods see https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/BlockingQueue.html
 */
public interface MyBlockingQueue<T> {
	boolean add(T obj);
	boolean offer(T obj);
	void put(T obj) throws InterruptedException;
	boolean offer(T obj, long timeout, TimeUnit unit) throws InterruptedException;
	T remove();
	T poll();
	T take() throws InterruptedException;
	T poll(long timeout, TimeUnit unit) throws InterruptedException;
	T element();
	T peek();
}
