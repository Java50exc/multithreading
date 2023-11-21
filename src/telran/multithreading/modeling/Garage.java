package telran.multithreading.modeling;

import java.time.Instant;
import java.util.concurrent.*;

import telran.multithreading.dto.Car;

public class Garage {
private static final int N_WORKERS = 20;
private static final int PARKING_PLACES = 20;
private static final int GARAGE_CAPACITY = N_WORKERS + PARKING_PLACES;
private static final int MODELLING_TIME = 10000;
private static final int MIN_TIME = 1;
private static final int MAX_TIME = 2;
private static final int CAR_COMMING_PROB = 2;
private static int rejectedCars = 0;
private static int nCars = 0;

public static void main(String[] args) throws InterruptedException {
	BlockingQueue<Car> cars = new LinkedBlockingQueue<>(GARAGE_CAPACITY);
	Worker[] workers = new Worker[N_WORKERS];
	startWorkers(workers, cars );
	for (int i = 0; i < MODELLING_TIME; i++) {
		Car car = generateCar();
		if (car != null) {
			if(!cars.offer(car)) {
				
				rejectedCars++;
			}
		}
		Thread.sleep(1);
	}
	stopWorkers(workers);
	System.out.printf("number of cars is %d; number of rejected cars is %d", nCars, rejectedCars);
}

private static void stopWorkers(Worker[] workers) {
	for(Worker worker: workers) {
		worker.interrupt();
	}
	
}

private static Car generateCar() {
	Car car = null;
	if(chance() < CAR_COMMING_PROB) {
		car = new Car(Instant.now(), getRandomNumber(MIN_TIME, MAX_TIME));
		nCars++;
	}
	return car;
}

private static long getRandomNumber(int minTime, int maxTime) {
	
	return ThreadLocalRandom.current().nextLong(minTime, maxTime);
}

private static long chance() {
	
	return getRandomNumber(1, 100);
}

private static void startWorkers(Worker[] workers, BlockingQueue<Car> cars) {
	for(int i = 0; i < workers.length; i++) {
		workers[i] = new Worker(cars);
	}
	
}
}
