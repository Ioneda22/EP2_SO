package noSolution.entities;

import java.util.concurrent.locks.Lock;
import java.util.List;
import java.util.Random;

public abstract class Worker implements Runnable {
    private static final int _numOfAccesses = 100;
    private final Random _random;
    private final Lock _lock;
    protected List<String> base;

    public Worker(List<String> base, Lock lock) {
        this.base = base;
        _lock = lock;
        _random = new Random();
    }

    @Override
    public void run() {
        _lock.lock();
        try {
            int size = base.size();
            for (int i = 0; i < _numOfAccesses; i++) {
                int randomPosition = _random.nextInt(0, size);
                work(randomPosition);
            }
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(e.getMessage());
        } finally {
            _lock.unlock();
        }
    }

    public abstract void work(int position);
}
