package bowling.domain.Frame;

import bowling.domain.Score.Scores;

public class LastFrame extends Frame {

    private static final int TOTAL_CHANCE = 3;

    @Override
    protected int TotalChance() {
        return TOTAL_CHANCE;
    }

    public void minusChance() {
        if (this.scores.size() == 2 && !this.scores.first().isStrike() && !isSpare()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.scores.size() > 4) {
            throw new IllegalArgumentException();
        }
        if (this.scores.size() >= 2) {
            validateLastFrameSecondTimeScore(this.scores);
        }
        if (this.scores.size() >= 3) {
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
        if (this.scores.size() < 2) {
            return true;
        }
        if (this.scores.first().isStrike() || isSpare()) {
            return this.scores.size() != TOTAL_CHANCE;
        }
        return false;
    }
}
