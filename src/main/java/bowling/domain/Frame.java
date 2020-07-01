package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public abstract class Frame {
    protected final Map<Round, BowlingPins> record = new HashMap<>();
    protected boolean isFinished = false;

    abstract Frame execute(final BowlingPins bowlingPins);

    public abstract int getFrameNumber();

    public boolean isStrike() {
        return record.get(Round.FIRST_ROUND).isMax();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isSpare() {
        return record.get(Round.FIRST_ROUND).add(record.get(Round.SECOND_ROUND)).isMax();
    }

    public BowlingPins getPinsOf(final Round firstRound) {
        return record.get(firstRound);
    }
}
