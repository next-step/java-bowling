package bowling.domain;

import bowling.dto.ScoreDto;

public abstract class BowlingFrame {

    private final Round round;
    private BowlingFrame nextFrame;
    private final FramePoint framePoint;

    public BowlingFrame(Round round, FramePoint framePoint) {
        this.round = round;
        this.framePoint = framePoint;
    }

    public BowlingFrame(Round round, BowlingFrame nextFrame, FramePoint framePoint) {
        this.round = round;
        this.nextFrame = nextFrame;
        this.framePoint = framePoint;
    }

    abstract BowlingFrame secondPitching(Point point);

    abstract BowlingFrame firstPitching(Point point);

    abstract BowlingFrame bonusPitching(Point point);

    public BowlingFrame nextFrame() {
        if (round.equals(Round.of(9))) {
            this.nextFrame = BowlingFinalFrame.first(FinalRound.of(), FramePoint.of(calculateOfScore().toInt()));
            return this.nextFrame;
        }
        this.nextFrame = BowlingNormalFrame.of(round.next(), FramePoint.of(calculateOfScore().toInt()));
        return this.nextFrame;
    }

    public Round round() {
        return round;
    }

    public FramePoint framePoint() {
        return framePoint;
    }

    abstract BowlingRole isType();

    abstract ScoreDto toDto();

    public abstract FramePoint calculateOfScore();

    public abstract Score score();

    BowlingFrame getNextFrame() {
        return nextFrame;
    }
}
