package Solution.entities;

import java.util.List;
import java.util.Random;

public abstract class Worker implements Runnable {
    private static final int _numOfAccesses = 100;
    private final Random _random;
    protected List<String> base;

    public Worker(List<String> base) {
        this.base = base;
        _random = new Random();
    }

    @Override
    public void run() {
        try {
            enterRegion();
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
            exitRegion();
        }
    }

    public abstract void enterRegion() throws InterruptedException;
    public abstract void work(int position);
    public abstract void exitRegion();
}
