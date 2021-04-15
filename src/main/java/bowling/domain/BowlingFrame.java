package bowling.domain;

import bowling.dto.ScoreDto;

public abstract class BowlingFrame {

    private final Round round;

    public BowlingFrame(Round round) {
        this.round = round;
    }

    abstract BowlingFrame secondPitching(Point point);

    abstract BowlingFrame firstPitching(Point point);

    public BowlingFrame nextFrame() {
        if (round.isFinal()) {
            return BowlingFinalFrame.of(round.next());
        }
        return BowlingNormalFrame.of(round.next());
    }

    public Round getRound() {
        return round;
    }

    abstract BowlingRole isType();

    abstract ScoreDto toDto();
}
