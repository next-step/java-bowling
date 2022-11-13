package bowling.domain.score.scores;

public class DefaultFrameScores extends Scores {

    @Override
    public void validateScore() {
        if (this.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isEndScore() {
        return !isSizeEqual(0) || !(isSizeEqual(1) && !this.isStrike());
    }

    @Override
    public boolean isEndScore(Scores scores) {
        return false;
    }
}
