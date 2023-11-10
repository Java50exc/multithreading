package telran.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterResource {
 AtomicInteger counter = new AtomicInteger();
 public void increment() {
	 counter.getAndIncrement();
 }
 public int getCounter() {
	 return counter.get();
 }
}
