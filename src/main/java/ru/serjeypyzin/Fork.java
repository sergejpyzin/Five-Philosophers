package ru.serjeypyzin;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private final String forkNumber;
    private final Lock lock;

    public Fork(String forkNumber) {
        this.forkNumber = forkNumber;
        this.lock = new ReentrantLock();
    }


    @Override
    public String toString() {
        return String.format("%s", forkNumber);
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void unlock() {
        lock.unlock();
    }
}

