package camp.nextstep.edu.rebellion.bowling.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Round {
    private final int MAX_ROUNDS = 10;

    private AtomicInteger current;

    private Round(){
        current = new AtomicInteger(0);
    }

    public static Round reset() {
        return new Round();
    }

    public void next() {
        current.incrementAndGet();
    }

    public boolean hasNext() {
        return MAX_ROUNDS > current.get();
    }

    public boolean meetLast() {
        return MAX_ROUNDS == current.get();
    }

    public int getCurrent() {
        return this.current.get();
    }
}
