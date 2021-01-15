package bowling.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class AdditionCounter {

    private final AtomicInteger count;

    private AdditionCounter(int count) {
        this.count = new AtomicInteger(count);
    }

    public static AdditionCounter of(int count) {
        return new AdditionCounter(count);
    }

    public boolean isDone() {
        return count.get() == 0;
    }

    public void count(){
        count.decrementAndGet();
    }

}
