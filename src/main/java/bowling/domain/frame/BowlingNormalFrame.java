package bowling.domain.frame;

import bowling.domain.BowlingRole;
import bowling.domain.frame.round.Round;
import bowling.domain.score.FramePoint;
import bowling.domain.score.Point;
import bowling.domain.score.Score;
import bowling.dto.ScoreDto;

public class BowlingNormalFrame extends BowlingFrame {

    private Score score;


    private BowlingNormalFrame(Round round, Score score, FramePoint framePoint) {
        super(round, framePoint);
        this.score = score;
    }

    private BowlingNormalFrame(Round round, Score score, BowlingFrame nextFrame, FramePoint framePoint) {
        super(round, nextFrame, framePoint);
        this.score = score;
    }

    public static BowlingNormalFrame of(int round, Point point) {
        return new BowlingNormalFrame(Round.of(round), Score.first(point), FramePoint.first());
    }

    public static BowlingNormalFrame of(Round round, FramePoint framePoint) {
        return new BowlingNormalFrame(round, Score.initScore(), framePoint);
    }

    public static BowlingNormalFrame first(Round round) {
        return new BowlingNormalFrame(round, Score.of(Point.of(0), Point.of(0)), FramePoint.first());
    }

    public static BowlingNormalFrame of(Round round, Score score) {
        return new BowlingNormalFrame(round, score, FramePoint.first());
    }

    public static BowlingNormalFrame of(Round round, Score score, FramePoint framePoint) {
        return new BowlingNormalFrame(round, score, framePoint);
    }


    @Override
    public BowlingFrame secondPitching(Point point) {
        score = score.next(point);
        return nextFrame();
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        score = Score.first(point);
        if (score.type() == BowlingRole.STRIKE) {
            return nextFrame();
        }
        return this;
    }

    @Override
    public BowlingFrame bonusPitching(Point point) {
        return new BowlingNormalFrame(round(), score.next(point), FramePoint.first());
    }

    @Override
    public BowlingRole isType() {
        return score.type();
    }

    @Override
    public ScoreDto toDto() {
        return ScoreDto.of(score);
    }

    @Override
    public FramePoint calculateOfScore() {
        if (score.type() == BowlingRole.SPARE && getNextFrame() != null) {
            Score nextFrameScore = getNextFrame().score();
            return FramePoint.of(framePoint().toInt() + this.score.total() + nextFrameScore.firstPoint());
        }
        if (score.type() == BowlingRole.STRIKE && getNextFrame() != null) {
            Score nextFrameScore = getNextFrame().score();
            return FramePoint.of(framePoint().toInt() + this.score.total() + nextFrameScore.total());
        }
        return FramePoint.of(framePoint().toInt() + score.total());
    }

    @Override
    public Score score() {
        return score;
    }


}
