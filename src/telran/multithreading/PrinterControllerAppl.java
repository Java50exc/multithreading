package telran.multithreading;

import java.util.Scanner;

public class PrinterControllerAppl {

	public static void main(String[] args) {
		String symbols = "&*#?>.!";
		String line = "";
		Printer printer = new Printer(symbols);
		printer.start();
		Scanner scanner = new Scanner(System.in);
		while (!line.equals("q")) {
			line = scanner.nextLine();
			printer.interrupt();
		}
		printer.finish();
	}

}
