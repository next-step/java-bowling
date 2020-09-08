package camp.nextstep.edu.rebellion.bowling.domain.game;

import java.util.concurrent.atomic.AtomicInteger;

public class Round {
    private static final int FIRST_ROUND = 0;
    private static final int FINAL_ROUND = 10;
    private static final int BONUS_ROUND = 1;

    private AtomicInteger current;
    private int lastRound;

    private Round(int initialValue, int lastRound){
        checkLastRound(lastRound);
        this.current = new AtomicInteger(initialValue);
        this.lastRound = lastRound;
    }

    public static Round reset() {
        return new Round(0, FINAL_ROUND);
    }

    public static Round currentOf(Round round) {
        return new Round(round.getCurrent(), FINAL_ROUND);
    }

    public void next() {
        if (hasNext()) {
            this.current.incrementAndGet();
        }
    }

    public void prev() {
        if (hasPrev()) {
            this.current.decrementAndGet();
        }
    }

    public boolean hasNext() {
        return lastRound > this.current.get();
    }

    public boolean hasPrev() {
        return FIRST_ROUND < this.current.get();
    }

    public boolean meetFinal() {
        return lastRound == this.current.get();
    }

    public boolean meetFirst() {
        return FIRST_ROUND == this.current.get();
    }

    public int getCurrent() {
        return this.current.get();
    }

    public void addLastRound() {
        this.lastRound++;
        checkLastRound(lastRound);
    }

    private void checkLastRound(int lastRound) {
        if (FINAL_ROUND + BONUS_ROUND < lastRound) {
            throw new IllegalArgumentException("최대 라운드는 허용 범위를 벗어 났습니다 (최대 11) " + lastRound);
        }
    }
}
