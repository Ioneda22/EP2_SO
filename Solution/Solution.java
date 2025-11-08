package Solution;

import Solution.entities.Reader;
import Solution.entities.Writer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    private final int _numOfReaders;
    private final int _numOfWriters;
    private final List<String> _base;
    private final Semaphore _wrt;
    private final Semaphore _mutex;
    private final AtomicInteger _readCount;

    public Solution(int numOfReaders, int numOfWriters, List<String> base) {
        _numOfReaders = numOfReaders;
        _numOfWriters = numOfWriters;
        _base = base;
        _wrt = new Semaphore(1);
        _mutex = new Semaphore(1);
        _readCount = new AtomicInteger(0);
    }

    public long solve() {
        List<Runnable> runnables = new ArrayList<>();

        for (int i = 0; i < _numOfReaders; i++) {
            runnables.add(new Reader(_base, _wrt, _mutex, _readCount));
        }
        for (int i = 0; i < _numOfWriters; i++) {
            runnables.add(new Writer(_base, _wrt));
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
