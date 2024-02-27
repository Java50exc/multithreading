package telran.multithreading.messaging;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
	int capacity;
	LinkedList<E> bk = new LinkedList<>();
	ReentrantLock lock = new ReentrantLock();
	Condition producerCondition = lock.newCondition();
	Condition consumerCondition = lock.newCondition();

	public MyLinkedBlockingQueue(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public boolean add(E obj) {
		if (!offer(obj)) {
			throw new IllegalStateException();
		}
		return true;
	}

	@Override
	public boolean offer(E obj) {
		boolean res = false;
		try {
			res = offer(obj, 0, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		}
		return res;

	}

	@Override
	public void put(E obj) throws InterruptedException {
		if (obj == null) {
			throw new NullPointerException();
		}

		try {
			lock.lock();

			while (bk.size() == capacity) {
				producerCondition.await();
			}
			bk.push(obj);
			consumerCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {
		if (obj == null) {
			throw new NullPointerException();
		}
		try {
			lock.lock();
			boolean isTimeout = true;

			while (bk.size() == capacity) {
				if (!isTimeout) {
					return false;
				}
				isTimeout = producerCondition.await(timeout, unit);
			}
			bk.push(obj);
			return true;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E remove() {
		E res;
		if ((res = poll()) == null) {
			throw new NoSuchElementException();
		}
		return res;
	}

	@Override
	public E poll() {
		E res = null;
		try {
			res = poll(0, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		}
		return res;
	}

	@Override
	public E take() throws InterruptedException {
		try {
			lock.lock();
			
			while (bk.size() == 0) {
				consumerCondition.await();
			}
			producerCondition.signal();
			return bk.poll();
		} finally {
			lock.unlock();
		}

	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		try {
			lock.lock();
			boolean isTimeout = true;

			while (bk.size() == 0) {
				if (!isTimeout) {
					return null;
				}
				isTimeout = consumerCondition.await(timeout, unit);
			}
			return bk.poll();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E element() {
		E res;

		if ((res = peek()) == null) {
			throw new NoSuchElementException();
		}
		return res;
	}

	@Override
	public E peek() {
		try {
			lock.lock();
			return bk.size() == 0 ? null : bk.peek();
		} finally {
			lock.unlock();
		}
	}

}
