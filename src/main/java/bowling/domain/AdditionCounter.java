package bowling.domain;

import java.util.Objects;
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

    public static AdditionCounter ofStrike() {
        return new AdditionCounter(2);
    }

    public static AdditionCounter ofSpare() {
        return new AdditionCounter(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionCounter counter = (AdditionCounter) o;
        return Objects.equals(count.get(), counter.count.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
