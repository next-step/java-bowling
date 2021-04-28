package bowling.domain.frame;

import bowling.domain.BowlingRole;
import bowling.domain.frame.round.FinalRound;
import bowling.domain.frame.round.Round;
import bowling.domain.score.FramePoint;
import bowling.domain.score.Point;
import bowling.domain.score.Score;
import bowling.dto.ScoreDto;

public abstract class BowlingFrame {

    private static final int PREV_FINAL_ROUND = 9;

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

    public abstract BowlingFrame secondPitching(Point point);

    public abstract BowlingFrame firstPitching(Point point);

    public abstract BowlingFrame bonusPitching(Point point);

    public BowlingFrame nextFrame() {
        if (round.equals(Round.of(PREV_FINAL_ROUND))) {
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

    public abstract BowlingRole isType();

    public abstract ScoreDto toDto();

    public abstract FramePoint calculateOfScore();

    public abstract Score score();

    BowlingFrame getNextFrame() {
        return nextFrame;
    }
}
