package ua.mytreo.java.soltest.IO;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @author mytreo 29.01.2016.
 * @version 1.0
 */
public class Saver implements Runnable {
    private final int SLEEPING_TIME;
    AtomicInteger outerCounter;
    int innerCounter;
    public Saver(AtomicInteger outerCounter, int sleepingTime){
        this.outerCounter=outerCounter;
        this.innerCounter=0;
        this.SLEEPING_TIME=sleepingTime;
    }
    @Override
    public void run() {
        while(true){
            if(outerCounter.intValue()>innerCounter){
                innerCounter=outerCounter.intValue();
                System.out.println(innerCounter);
                try {
                    sleep(SLEEPING_TIME);
                } catch (InterruptedException e) {
                    System.out.println("Interrupt sleeping saver ");
                }
            }

        }

    }
}
