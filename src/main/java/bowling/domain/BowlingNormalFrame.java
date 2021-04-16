package bowling.domain;

import bowling.dto.ScoreDto;

public class BowlingNormalFrame extends BowlingFrame {

    private final Score score;

    public BowlingNormalFrame(Round round, Score score) {
        super(round);
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


    @Override
    public BowlingFrame secondPitching(Point point) {
        return new BowlingNormalFrame(getRound(), score.next(point));
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        return new BowlingNormalFrame(getRound(), Score.first(point));
    }

    @Override
    public BowlingFrame bonusPitching(Point point) {
        return new BowlingNormalFrame(getRound(), score.next(point));
    }
    
    @Override
    public BowlingRole isType() {
        return score.type();
    }

    @Override
    public ScoreDto toDto() {
        return ScoreDto.of(score);
    }


}
