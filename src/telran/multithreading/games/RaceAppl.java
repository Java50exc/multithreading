package telran.multithreading.games;


import telran.view.*;

public class RaceAppl {

	private static final int MAX_THREADS = 10;
	private static final int MIN_THREADS = 3;
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 3500;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;
	public static void main(String[] args) {
		InputOutput io = new SystemInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Start new game", RaceAppl::startGame),
				Item.exit()
		};
		return res;
	}
	static void startGame(InputOutput io) {
		int nThreads = io.readInt("Enter number of the runners","Wrong number of the runners",
				MIN_THREADS, MAX_THREADS);
		int distance = io.readInt("Enter distance", "Wrong Distance",MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
		Runner[] runners = new Runner[nThreads];
		startRunners(runners, race);
		joinRunners(runners);
		displayWinner(race);
	}

	private static void displayWinner(Race race) {
		System.out.println("Congratulations to runner " + race.getWinner());
		
	}

	private static void joinRunners(Runner[] runners)  {
		for(int i = 0; i < runners.length; i++) {
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				
			}
		}
		
		
	}

	private static void startRunners(Runner[] runners, Race race) {
		for(int i = 0; i < runners.length; i++) {
			runners[i] = new Runner(race, i + 1);
			runners[i].start();
		}
		
		
	}

}
