package de.hawhamburg;

import java.util.*;

public class Main {

    // the amount of cars in the race
    public final static int CAR_COUNT = 10;
    // the count of the rounds in the race
    public final static int ROUND_COUNT = 10;

    public static void main(String[] args) {

        List<Car> cars = new ArrayList<>();

        // get the cars ready
        for (int i = 0; i < CAR_COUNT; i++) {
            cars.add(new Car(i));
        }

        // start the race
        cars.forEach(Car::start);

        // start the accident thread
        Accident accident = new Accident();
        accident.start();

        // wait for every car to finish the race
        // or break if accident happened
        for (Car car : cars) {
            try {
                if (accident.accidentHappened) {
                    break;
                }
                car.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!accident.accidentHappened) {
            // sort the listing based on time
            Collections.sort(cars);

            // when we arrive here, and the accident thread isAlive, we assume
            // that no accident occured and print the results.
            System.out.println("*** ENDSTAND ***");
            for (int i = 1; i <= CAR_COUNT; i++) {
                Car car = cars.get(i-1);
                System.out.println(i + ". Platz: Wagen " + car._id + " Zeit: " + car._timeNeeded + " ms");
            }
        } else {
            System.err.println("UNFALL!");
        }

    }
}
