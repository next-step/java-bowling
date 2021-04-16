package bowling.domain;

import bowling.dto.ScoreDto;

public class BowlingFinalFrame extends BowlingFrame {

    private static final int FINAL_ROUND = 10;

    private final Score score;
    private final BonusScore bonusScore;

    private BowlingFinalFrame(Round round, Score score, BonusScore bonusScore) {
        super(round);
        valid(round);
        this.score = score;
        this.bonusScore = bonusScore;
    }

    private void valid(Round round) {
        if (!round.equals(Round.of(FINAL_ROUND))) {
            throw new IllegalArgumentException("Final 라운드는 10라운드 여야 합니다.");
        }
    }

    public static BowlingFinalFrame first(Round round) {
        return new BowlingFinalFrame(round, Score.of(Point.of(0), Point.of(0)), BonusScore.empty());
    }

    public static BowlingFrame of(Round round, Score score) {
        return new BowlingFinalFrame(round, score, BonusScore.empty());
    }

    public static BowlingFrame of(Round round, Score score, BonusScore bonusScore) {
        return new BowlingFinalFrame(round, score, bonusScore);
    }

    @Override
    public BowlingFrame secondPitching(Point point) {
        return BowlingFinalFrame.of(getRound(), score.next(point));
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        return BowlingFinalFrame.of(getRound(), Score.first(point));
    }

    @Override
    public BowlingFrame bonusPitching(Point point) {
        return BowlingFinalFrame.of(getRound(), score, BonusScore.of(getRound(), score, point));
    }

    @Override
    public BowlingRole isType() {
        return score.type();
    }

    @Override
    public ScoreDto toDto() {
        return ScoreDto.of(score, bonusScore);
    }
}
