package noSolution;

import noSolution.entities.Reader;
import noSolution.entities.Writer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NoSolution {
    private final int _numOfReaders;
    private final int _numOfWriters;
    private final List<String> _base;
    private final Lock _lock;

    public NoSolution(int numOfReaders, int numOfWriters, List<String> base) {
        _numOfReaders = numOfReaders;
        _numOfWriters = numOfWriters;
        _base = base;
        _lock = new ReentrantLock();
    }

    public long solve() {
        List<Runnable> runnables = new ArrayList<>();

        for (int i = 0; i < _numOfReaders; i++) {
            runnables.add(new Reader(_base, _lock));
        }
        for (int i = 0; i < _numOfWriters; i++) {
            runnables.add(new Writer(_base, _lock));
        }

        Collections.shuffle(runnables);
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(runnables.get(i));
        }

        long startTime = System.currentTimeMillis();

        for (Thread t : threads) {
            t.start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread principal interrompida.");
        }

        long endTime = System.currentTimeMillis();

        return (endTime - startTime);
    }
}
