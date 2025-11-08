package Solution.entities;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Reader extends Worker {
    private final Semaphore _wrt;
    private final Semaphore _mutex;
    private final AtomicInteger _readCount;

    public Reader(List<String> base, Semaphore wrt, Semaphore mutex, AtomicInteger readCount) {
        super(base);
        _wrt = wrt;
        _mutex = mutex;
        _readCount = readCount;
    }

    @Override
    public void enterRegion() throws InterruptedException {
        _mutex.acquire();

        if (_readCount.incrementAndGet() == 1) {
            _wrt.acquire();
        }

        _mutex.release();
    }

    @Override
    public void work(int position) {
        String word = base.get(position);
    }

    @Override
    public void exitRegion() {
        try {
            _mutex.acquire();

            if (_readCount.decrementAndGet() == 0) {
                _wrt.release();
            }

            _mutex.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrompido durante exitRegion", e);
        }
    }
}