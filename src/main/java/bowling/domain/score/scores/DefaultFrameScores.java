package bowling.domain.score.scores;

public class DefaultFrameScores extends Scores {

    @Override
    public void validateScore() {
        if (this.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }
}
