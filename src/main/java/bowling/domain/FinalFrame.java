package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME = 10;
    private final Map<Round, BowlingPins> record = new HashMap<>();
    private boolean isFinished = false;

    public FinalFrame() {
    }

    @Override
    public FinalFrame execute(final BowlingPins bowlingPins) {
        if (record.isEmpty()) {
            record.put(Round.FIRST_ROUND, bowlingPins);
            if (bowlingPins.isMax()) {
                isFinished = true;
                return this;
            }
            isFinished = false;
        }
        if (this.record.get(Round.FIRST_ROUND).isNotAddable(bowlingPins)) {
            throw new IllegalArgumentException("쓰러트리는 볼링핀의 수의 합은 10이하여야합니다");
        }
        record.put(Round.SECOND_ROUND, bowlingPins);
        isFinished = true;
        return this;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME;
    }
}
