package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.TotalScore;
import bowling.domain.score.scores.Scores;

public class LastFrame extends Frame {

    private static final int TOTAL_CHANCE = 3;
    private static final int DEFAULT_CHANCE = 2;

    public LastFrame() {
        super(new Chance(TOTAL_CHANCE), TotalScore.lastFrameTotalScore());
    }

    @Override
    public void minusChance() {
        if (this.totalScore.regularScores().isSizeEqual(DEFAULT_CHANCE)
                && !this.totalScore.regularScores().first().isStrike()
                && !this.totalScore.isSpare()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.totalScore.regularScores().isSizeOver(TOTAL_CHANCE)) {
            throw new IllegalArgumentException();
        }
        if (this.totalScore.regularScores().isSizeEqual(DEFAULT_CHANCE)) {
            validateLastFrameSecondTimeScore(this.totalScore.regularScores());
        }
        if (this.totalScore.regularScores().isSizeEqual(TOTAL_CHANCE)) {
            validateLastFrameSecondTimeScore(this.totalScore.regularScores());
            validateLastFrameThirdTimeScore(this.totalScore.regularScores());
        }
    }

    private void validateLastFrameSecondTimeScore(Scores scores) {
        if (!scores.first().isStrike() && Scores.sumScores(scores.first(), scores.second()) > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateLastFrameThirdTimeScore(Scores scores) {
        if (!scores.second().isStrike() && !this.totalScore.isSpare()
                && Scores.sumScores(scores.second(), scores.third()) > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        if (this.chance.isRemainChance()) {
            return true;
        }
        if (this.totalScore.regularScores().first().isStrike() || this.totalScore.isSpare()) {
            return !this.totalScore.regularScores().isSizeEqual(TOTAL_CHANCE);
        }
        return false;
    }
}
