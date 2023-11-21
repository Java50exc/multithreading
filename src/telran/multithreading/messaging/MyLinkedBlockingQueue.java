package telran.multithreading.messaging;

import java.util.concurrent.TimeUnit;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
    //TODO fields
	public MyLinkedBlockingQueue (int capacity) {
		//TODO
	}
	@Override
	public boolean add(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void put(E obj) throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E take() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll(long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

}
