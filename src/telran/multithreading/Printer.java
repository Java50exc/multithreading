package telran.multithreading;

public class Printer extends Thread {
	private char symbol;
	private int nRuns;
	public Printer(char symbol, int nRuns) {
		this.symbol = symbol;
		this.nRuns = nRuns;
	}
	@Override
	public void run() {
		for(int i = 0; i < nRuns; i++) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(symbol);
		}
	}
}
