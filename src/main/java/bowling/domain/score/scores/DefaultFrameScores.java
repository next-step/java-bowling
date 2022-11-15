package bowling.domain.score.scores;

public class DefaultFrameScores extends Scores {

    @Override
    public void validateScore() {
        if (this.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isNotEndScore(Scores scores) {
        return scores.isSizeEqual(0) || (scores.isSizeEqual(1) && !scores.isStrike());
    }

    @Override
    public boolean isChanceMinusTwo() {
        return this.first().isStrike();
    }
}
