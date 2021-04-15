package bowling.domain;

import bowling.dto.ScoreDto;

public class BowlingFinalFrame extends BowlingFrame {

    private final Score score;
    private final Score bonusScore;

    private BowlingFinalFrame(Round round, Score score, Score bonusScore) {
        super(round);
        this.score = score;
        this.bonusScore = bonusScore;
    }

    public static BowlingFrame of(Round round) {
        return new BowlingFinalFrame(round, Score.of(Point.of(0), Point.of(0)), Score.of(Point.of(0), Point.of(0)));
    }

    @Override
    public BowlingFrame secondPitching(Point point) {
        return null;
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        return null;
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
