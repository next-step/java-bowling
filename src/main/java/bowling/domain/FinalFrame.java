package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME = 10;
    private final Map<Round, BowlingPins> record = new HashMap<>();

    @Override
    public FinalFrame execute(final BowlingPins bowlingPins) {
        if (record.isEmpty()) {
            record.put(Round.FIRST_ROUND, bowlingPins);
            return this;
        }
        if (this.record.get(Round.FIRST_ROUND).cannotAdd(bowlingPins)) {
            throw new IllegalArgumentException("쓰러트리는 볼링핀의 수의 합은 10이하여야합니다");
        }
        record.put(Round.SECOND_ROUND, bowlingPins);
        return this;
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME;
    }

    @Override
    public boolean isFinalFrame() {
        return !record.isEmpty() && (isStrike() || isFinished());
    }

    public boolean isFinished() {
        return record.get(Round.SECOND_ROUND) != null || record.get(Round.FIRST_ROUND).isMax();
    }

    public boolean isStrike() {
        return record.get(Round.FIRST_ROUND).isMax();
    }

    public boolean isSpare() {
        return record.get(Round.FIRST_ROUND).add(record.get(Round.SECOND_ROUND)).isMax();
    }

    public BowlingPins getPinsOf(final Round firstRound) {
        return record.get(firstRound);
    }
}
