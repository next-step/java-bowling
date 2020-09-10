package camp.nextstep.edu.rebellion.bowling.domain.game;

import java.util.concurrent.atomic.AtomicInteger;

public class Turn {
    private final static int CLEAR = 0;

    private final AtomicInteger current;
    private final int chances;

    private Turn(int chances) {
        this.current = new AtomicInteger(CLEAR);
        this.chances = chances;
    }

    public static Turn setup(int chances) {
        return new Turn(chances);
    }

    public int have() {
        return current.get();
    }

    public void handOver() {
        current.incrementAndGet();

        if (noChance()) {
            current.set(CLEAR);
        }
    }

    private boolean noChance() {
        return chances == current.get();
    }
}
