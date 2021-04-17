package bowling.domain;

import bowling.dto.ScoreDto;

public class BowlingNormalFrame extends BowlingFrame {

    private Score score;

    private BowlingNormalFrame(Round round, Score score) {
        super(round);
        this.score = score;
    }

    private BowlingNormalFrame(Round round, Score score, BowlingFrame nextFrame) {
        super(round, nextFrame);
        this.score = score;
    }

    public static BowlingNormalFrame of(int round, Point point) {
        return new BowlingNormalFrame(Round.of(round), Score.first(point));
    }

    public static BowlingNormalFrame of(Round round) {
        return new BowlingNormalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    public static BowlingNormalFrame first(Round round) {
        return new BowlingNormalFrame(round, Score.of(Point.of(0), Point.of(0)));
    }

    public static BowlingNormalFrame of(Round round, Score score) {
        return new BowlingNormalFrame(round, score);
    }

    @Override
    public BowlingFrame secondPitching(Point point) {
        score = score.next(point);
        return this;
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        score = Score.first(point);
        return this;
    }

    @Override
    public BowlingFrame bonusPitching(Point point) {
        return new BowlingNormalFrame(round(), score.next(point));
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
    public int calculateOfScore() {
        if (score.type() == BowlingRole.SPARE) {
            Score nextFrameScore = getNextFrame().score();
            return this.score.total() + nextFrameScore.firstPoint();
        }
        if (score.type() == BowlingRole.STRIKE) {
            Score nextFrameScore = getNextFrame().score();
            return score.total() + nextFrameScore.total();
        }
        return score.total();
    }

    @Override
    public Score score() {
        return score;
    }


}
