package bowling.domain.score.scores;


public class LastFrameScores extends Scores {
    private static final int TOTAL_CHANCE = 3;
    private static final int DEFAULT_CHANCE = 2;

    @Override
    public void validateScore() {
        if (this.isSizeOver(TOTAL_CHANCE)) {
            throw new IllegalArgumentException();
        }
        if (this.isSizeEqual(DEFAULT_CHANCE)) {
            validateLastFrameSecondTimeScore();
        }
        if (this.isSizeEqual(TOTAL_CHANCE)) {
            validateLastFrameSecondTimeScore();
            validateLastFrameThirdTimeScore();
        }
    }

    private void validateLastFrameSecondTimeScore() {
        if (!this.first().isStrike() && Scores.sumScores(this.first(), this.second()) > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateLastFrameThirdTimeScore() {
        if (!this.second().isStrike() && !this.isSpare()
                && Scores.sumScores(this.second(), this.third()) > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }
}
