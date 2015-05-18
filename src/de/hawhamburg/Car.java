package de.hawhamburg;

import java.util.Random;

public class Car extends Thread implements Comparable {

    public int _id;
    public int _timeNeeded;

    public static final int MAX_ROUND_TIME = 100;
    public static final int MIN_ROUND_TIME = 0;

    public Car(int id) {
        _id = id;
        _timeNeeded = 0;
    }

    @Override
    public void run() {

        int roundCount = Main.ROUND_COUNT;

        // Jump out the for loop entirely when interrupted
        try {

            for (int i = 0; i < roundCount; i++) {

                int roundTime = new Random().nextInt(MAX_ROUND_TIME-MIN_ROUND_TIME);
                _timeNeeded += roundTime;

                sleep(roundTime);

            }
        } catch (InterruptedException e) {
            //
        }
    }

    public int getTime() {
        return _timeNeeded;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(_timeNeeded, ((Car) o).getTime());
    }
}
