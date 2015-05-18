package de.hawhamburg;

import java.util.Random;

public class Accident extends Thread {

    public boolean accidentHappened = false;

    @Override
    public void run() {
        int randomAccidentTime = new Random().nextInt(Car.MAX_ROUND_TIME * Main.ROUND_COUNT);

        try {
            sleep(randomAccidentTime);
            accidentHappened = true;
        } catch (InterruptedException e) {
            //
        }

        this.interrupt();
    }
}
