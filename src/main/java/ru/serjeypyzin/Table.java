package ru.serjeypyzin;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private final List<Fork> forks;

    private final List<Philosopher> philosophers;

    public Table(){
        this.forks = new ArrayList<>();
        this.philosophers = new ArrayList<>();
    }

    private void createTable() {
        createForks();
        createPhilosophers();

    }

    private void createForks() {
        for (int i = 0; i < 5; i++) {
            String forkNumber = String.format("Вилка %d", i + 1);
            forks.add(new Fork(forkNumber));
        }
    }

    private void createPhilosophers() {
        for (int i = 0; i < 5; i++) {
            String philosopherNumber = String.format("Философ %d", i + 1);
            philosophers.add(new Philosopher(philosopherNumber, forks.get((i + 1) % 5), forks.get(i)));

        }
    }

    public void eat() {
        createTable();
        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }
        pause();
    }

    private void pause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return String.format("За столом сидят: %s", philosophers);
    }


}
