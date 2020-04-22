package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

public class FinishedScoreCalculator implements ScoreCalculator {
    private int score;

    public FinishedScoreCalculator(int score) {
        this.score = score;
    }

    @Override public Score calculateScore(Frame frame) {
        return calculateScore();
    }

    @Override public Score calculateScore() {
        return new CompleteScore(score);
    }

    @Override public ScoreCalculator add(Pitch pitch) {
        return null;
    }
}
