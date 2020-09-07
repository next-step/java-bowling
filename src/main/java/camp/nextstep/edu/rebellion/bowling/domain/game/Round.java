package camp.nextstep.edu.rebellion.bowling.domain.game;

import java.util.concurrent.atomic.AtomicInteger;

public class Round {
    protected final int FIRST_ROUND = 0;
    protected final int LAST_ROUND = 10;

    protected AtomicInteger current;

    protected Round(int initialValue){
        this.current = new AtomicInteger(initialValue);
    }

    public static Round reset() {
        return new Round(0);
    }

    public static Round currentOf(Round round) {
        return new Round(round.getCurrent());
    }

    public void next() {
        this.current.incrementAndGet();
    }

    public void prev() {
        this.current.decrementAndGet();
    }

    public boolean hasNext() {
        return LAST_ROUND > this.current.get();
    }

    public boolean hasPrev() {
        return FIRST_ROUND < this.current.get();
    }

    public boolean meetLast() {
        return LAST_ROUND == this.current.get();
    }

    public boolean meetFirst() {
        return FIRST_ROUND == this.current.get();
    }

    public int getCurrent() {
        return this.current.get();
    }
}
