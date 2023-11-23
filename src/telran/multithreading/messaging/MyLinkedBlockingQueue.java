package telran.multithreading.messaging;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
    int capacity;
    Queue<E> queue = new LinkedList<>();
    Lock monitor = new ReentrantLock();
    Condition producerWaiting = monitor.newCondition();
    Condition consumerWaiting = monitor.newCondition();
	public MyLinkedBlockingQueue (int capacity) {
		this.capacity = capacity;
	}
	@Override
	public boolean add(E obj) {
		try {
			monitor.lock();
			if (queue.size() == capacity) {
				throw new IllegalStateException();
			}
			boolean res = queue.add(obj);
			consumerWaiting.signal();
			return res;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public boolean offer(E obj) {
		boolean res = true;
		try {
			monitor.lock();
			if (queue.size() == capacity) {
				res = false;
			} else {
				queue.add(obj);
				consumerWaiting.signal();
			}

			return res;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public void put(E obj) throws InterruptedException {
		try {
			monitor.lock();
			while (queue.size() == capacity) {
				producerWaiting.await();
			}
			queue.add(obj);
			consumerWaiting.signal();

		} finally {
			monitor.unlock();
		}

	}

	@Override
	public boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {
		boolean res = true;
		try {
			monitor.lock();
			while (queue.size() == capacity && res) {
				if (!producerWaiting.await(timeout, unit)) {
					res = false;
				}
			}
			if (res) {
				queue.add(obj);
				consumerWaiting.signal();
			}
			return res;

		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E remove() {
		try {
			monitor.lock();
			E result = queue.remove();
			producerWaiting.signal();
			return result;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E poll() {
		E result = null;
		try {
			monitor.lock();

			result = queue.poll();
			if (result != null) {
				producerWaiting.signal();
			}

			return result;
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E take() throws InterruptedException {
		try {
			monitor.lock();
			while (queue.isEmpty()) {
				consumerWaiting.await();
			}
			E res = queue.remove();
			producerWaiting.signal();
			return res;

		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		boolean running = true;
		E res = null;
		try {
			monitor.lock();
			while (queue.isEmpty() && running) {
				if (!consumerWaiting.await(timeout, unit)) {
					running = false;
				}
			}
			if (running) {
				res = queue.remove();
				producerWaiting.signal();
			}
			return res;

		} finally

		{
			monitor.unlock();
		}
	}

	@Override
	public E element() {
		try {
			monitor.lock();
			return queue.element();
		} finally {
			monitor.unlock();
		}
	}

	@Override
	public E peek() {
		E result = null;
		try {
			monitor.lock();
			if (queue.size() != 0) {
				result = queue.peek();

			}

			return result;
		} finally {
			monitor.unlock();
		}
	}

}
