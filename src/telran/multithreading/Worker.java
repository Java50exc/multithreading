package telran.multithreading;

public class Worker extends Thread {
	static final Object mutex1=new Object();
	static final Object mutex2=new Object();
	@Override
	public void run() {
		f1();
	}
	private void f1() {
		/**********Critical section with two shared resources*************************/
		synchronized (mutex1) {
			synchronized (mutex2) {
				//working with resources
			}
		}
		/************************************************/
		/************two critical sections with two separate resources******/
		synchronized (mutex1) {
			//critical section with resource1
		}
		synchronized (mutex2) {
			//critical section with resource2
		}
		/*******************************************************************/
		
	}
}
