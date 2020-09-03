package camp.nextstep.edu.rebellion.bowling.domain.game;

import java.util.concurrent.atomic.AtomicInteger;

public class Round {
    private final int MAX_ROUNDS = 10;

    private AtomicInteger current;

    private Round(){
        this.current = new AtomicInteger(0);
    }

    public static Round reset() {
        return new Round();
    }

    public void next() {
        this.current.incrementAndGet();
    }

    public boolean hasNext() {
        return MAX_ROUNDS > this.current.get();
    }

    public boolean meetLast() {
        return MAX_ROUNDS == this.current.get();
    }

    public int getCurrent() {
        return this.current.get();
    }
}
