package bowling.domain;

import bowling.dto.ScoreDto;

public abstract class BowlingFrame {

    private final Round round;

    public BowlingFrame(Round round) {
        this.round = round;
    }

    abstract BowlingFrame secondPitching(Point point);

    abstract BowlingFrame firstPitching(Point point);

    abstract BowlingFrame bonusPitching(Point point);

    public BowlingFrame nextFrame() {
        if (round.equals(Round.of(9))) {
            return BowlingFinalFrame.first(FinalRound.of());
        }
        return BowlingNormalFrame.of(round.next());
    }

    public Round round() {
        return round;
    }

    abstract BowlingRole isType();

    abstract ScoreDto toDto();
}
