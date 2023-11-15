package telran.multithreading;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class ListOperations extends Thread {
  ModelData modelData;
  List<Integer> list;
  Monitor monitor;
public ListOperations(ModelData modelData, List<Integer> list, Monitor monitor) {
	this.modelData = modelData;
	this.list = list;
	this.monitor = monitor;
}
  @Override
  public void run() {
	  int nRuns = modelData.nOperations();
	  int updateProb = modelData.updateProb();
	  for(int i = 0; i < nRuns; i++) {
		  if(chance() <= updateProb) {
			  update();
		  } else {
			  read();
		  }
	  }
  }
private void read() {
	
		try {
			tryLock(monitor.lockRead());
			int nReads = 100000;
			for (int i = 0; i < nReads; i++) {
				list.get(list.size() - 1);
			}
			} finally {
				monitor.lockRead().unlock();
			} 
	
	
}
private void update() {
	try  {
		tryLock(monitor.lockWrite());
		int nUpdates = 10;
		for (int i = 0; i < nUpdates; i++) {
			list.remove(0);
		}
		for (int i = 0; i < nUpdates; i++) {
			list.add(100);
		}
	}finally {
		monitor.lockWrite().unlock();
	}
	
}
private int chance() {
	
	return ThreadLocalRandom.current().nextInt(1, 100);
}
private void tryLock(Lock lock) {
	AtomicInteger locksCounter = monitor.locksCounter();
	while(!lock.tryLock()) {
		locksCounter.getAndIncrement();
	}
}
}
