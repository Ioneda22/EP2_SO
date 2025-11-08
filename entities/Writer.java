package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class Writer extends Worker {

    public Writer(List<String> base, Lock lock) {
        super(base, lock);
    }

    @Override
    public void work(int position) {
        base.set(position, "MODIFICADO");
    }
}
