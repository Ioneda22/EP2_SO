package entities;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class Reader extends Worker {

    public Reader(List<String> base, Lock lock) {
        super(base, lock);
    }

    @Override
    public void work(int position) {
        String word = base.get(position);
    }
}
