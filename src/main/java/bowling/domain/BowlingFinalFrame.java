package bowling.domain;

import bowling.dto.ScoreDto;

public class BowlingFinalFrame extends BowlingFrame {

    private static final int FINAL_ROUND = 10;

    private final Score score;
    private final BonusScore bonusScore;

    private BowlingFinalFrame(FinalRound round, Score score, BonusScore bonusScore) {
        super(round);
        this.score = score;
        this.bonusScore = bonusScore;
    }

    public static BowlingFinalFrame first(FinalRound round) {
        return new BowlingFinalFrame(round, Score.of(Point.of(0), Point.of(0)), BonusScore.empty());
    }

    public static BowlingFrame of(FinalRound round, Score score) {
        return new BowlingFinalFrame(round, score, BonusScore.empty());
    }

    public static BowlingFrame of(FinalRound round, Score score, BonusScore bonusScore) {
        return new BowlingFinalFrame(round, score, bonusScore);
    }

    @Override
    public BowlingFrame secondPitching(Point point) {
        return BowlingFinalFrame.of(FinalRound.of(), score.next(point));
    }

    @Override
    public BowlingFrame firstPitching(Point point) {
        return BowlingFinalFrame.of(FinalRound.of(), Score.first(point));
    }

    @Override
    public BowlingFrame bonusPitching(Point point) {
        return BowlingFinalFrame.of(FinalRound.of(), score, BonusScore.of(FinalRound.of(), score, point));
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
