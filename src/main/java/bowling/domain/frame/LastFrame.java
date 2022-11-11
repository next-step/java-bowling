package bowling.domain.frame;

import bowling.domain.score.Scores;

public class LastFrame extends Frame {

    private static final int TOTAL_CHANCE = 3;
    private static final int DEFAULT_CHANCE = 2;

    @Override
    protected int TotalChance() {
        return TOTAL_CHANCE;
    }

    public void minusChance() {
        if (this.scores.isSizeEqual(DEFAULT_CHANCE) && !this.scores.first().isStrike() && !isSpare()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.scores.isSizeOver(TOTAL_CHANCE)) {
            throw new IllegalArgumentException();
        }
        if (this.scores.isSizeEqual(DEFAULT_CHANCE)) {
            validateLastFrameSecondTimeScore(this.scores);
        }
        if (this.scores.isSizeEqual(TOTAL_CHANCE)) {
            validateLastFrameSecondTimeScore(this.scores);
            validateLastFrameThirdTimeScore(this.scores);
        }
    }

    private void validateLastFrameSecondTimeScore(Scores scores) {
        if (!scores.first().isStrike() && Scores.sumScores(scores.first(), scores.second()) > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateLastFrameThirdTimeScore(Scores scores) {
        if (!scores.second().isStrike() && !isSpare()
                && Scores.sumScores(scores.second(), scores.third()) > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        if (this.scores.isSizeUnder(DEFAULT_CHANCE)) {
            return true;
        }
        if (this.scores.first().isStrike() || isSpare()) {
            return !this.scores.isSizeEqual(TOTAL_CHANCE);
        }
        return false;
    }
}
