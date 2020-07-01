package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class NormalFrame implements Frame {
    private Map<Round, BowlingPins> record = new HashMap<>();
    private final int frameNumber;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = frameNumber;
    }

    @Override
    public Frame execute(final BowlingPins bowlingPins) {
        if (record.isEmpty()) {
            record.put(Round.FIRST_ROUND, bowlingPins);
            if (bowlingPins.isMax()) {
                if (this.frameNumber == 9) {
                    return new FinalFrame();
                }
                return this.next();
            }
            return this;
        }
        if (this.record.get(Round.FIRST_ROUND).isNotAddable(bowlingPins)) {
            throw new IllegalArgumentException("쓰러트리는 볼링핀의 수의 합은 10이하여야합니다");
        }
        record.put(Round.SECOND_ROUND, bowlingPins);
        return this.next();
    }

    @Override
    public int getFrameNumber() {
        return this.frameNumber;
    }

    private NormalFrame next() {
        return new NormalFrame(this.frameNumber + 1);
    }


    public boolean isFinished() {
        return record.get(Round.SECOND_ROUND) != null || record.get(Round.FIRST_ROUND).isMax();
    }

    @Override
    public boolean isFinalFrame() {
        return false;
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

    @Override
    public String toString() {
        return "NormalFrame{" +
                "record=" + record +
                ", frameNumber=" + frameNumber +
                '}';
    }
}
