package bowling.domain;

import bowling.dto.ScoreDto;

public abstract class BowlingFrame {

    private final Round round;
    private BowlingFrame nextFrame;

    public BowlingFrame(Round round) {
        this.round = round;
    }

    public BowlingFrame(Round round, BowlingFrame nextFrame) {
        this.round = round;
        this.nextFrame = nextFrame;
    }

    abstract BowlingFrame secondPitching(Point point);

    abstract BowlingFrame firstPitching(Point point);

    abstract BowlingFrame bonusPitching(Point point);

    public BowlingFrame nextFrame() {
        if (round.equals(Round.of(9))) {
            this.nextFrame = BowlingFinalFrame.first(FinalRound.of());
            return this.nextFrame;
        }
        this.nextFrame = BowlingNormalFrame.of(round.next());
        return this.nextFrame;
    }

    public Round round() {
        return round;
    }

    abstract BowlingRole isType();

    abstract ScoreDto toDto();

    public abstract int calculateOfScore();

    public abstract Score score();

    BowlingFrame getNextFrame() {
        return nextFrame;
    }
}
