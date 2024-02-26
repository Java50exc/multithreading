package telran.multithreading.deadlock;

public class MyDeadlock {
	
	public static void main(String[] args) throws InterruptedException {
		Object i1 = new Object();
		Object i2 = new Object();
		
		Thread t1 = new Thread(() -> {	
			synchronized (i1) {	
				try { Thread.sleep(100); } catch (InterruptedException e) {}
				synchronized (i2) {}	
			}
		});
		
		Thread t2 = new Thread(() -> {
			synchronized (i2) {	
				try { Thread.sleep(100); } catch (InterruptedException e) {}
				synchronized (i1) {}	
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		
	}

}
