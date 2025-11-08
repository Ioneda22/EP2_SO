package Solution.entities;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Writer extends Worker {
    private final Semaphore _wrt;

    public Writer(List<String> base, Semaphore wrt) {
        super(base);
        _wrt = wrt;
    }

    @Override
    public void enterRegion() throws InterruptedException {
        _wrt.acquire();
    }

    @Override
    public void work(int position) {
        base.set(position, "MODIFICADO");
    }

    @Override
    public void exitRegion() {
        _wrt.release();
    }
}
