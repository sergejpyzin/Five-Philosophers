package ru.serjeypyzin;

import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher implements Runnable {

    private final String philosopherNumber;

    private final Fork leftFork;

    private final Fork rightFork;

    private final AtomicInteger countEat;

    public Philosopher(String philosopherNumber, Fork leftFork, Fork rightFork) {
        this.philosopherNumber = philosopherNumber;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.countEat = new AtomicInteger(0);
    }

    @Override
    public String toString() {
        return String.format(" %s имеет правую %s и левую %s вилки", philosopherNumber, rightFork, leftFork);
    }

    @Override
    public void run() {
        while (countEat.get() < 3) {
            System.out.println(philosopherNumber + " думает");
            synchronized (leftFork) {
                if (rightFork.tryLock()) {
                    try {
                        System.out.println(philosopherNumber + " ест");
                        System.out.println(philosopherNumber + " кладет вилки обратно");
                        countEat.incrementAndGet();
                        System.out.println(philosopherNumber + " поел " + countEat + " раза.");
                    } finally {
                        rightFork.unlock();
                    }
                }
            }
        }



    }

}


