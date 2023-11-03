package telran.multithreading;

public class Printer extends Thread {
	String symbols;
	boolean running = true;
	public Printer(String symbols) {
		this.symbols = symbols;
	}
	public void finish() {
		running = false;
	}
	@Override
	public void run() {
		int nSymbols = symbols.length();
		char[] arrSymbols = symbols.toCharArray();
		int index = 0;
		while(running) {
			System.out.print(arrSymbols[index]+ " ");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				index++;
				if(index == nSymbols) {
					index = 0;
				}
			}
		}
	}
	
}
