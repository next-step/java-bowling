package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

public class FirstScoreCalculator implements ScoreCalculator {
    private int score;

    public FirstScoreCalculator(int score) {
        this.score = score;
    }

    @Override public Score calculateScore(Frame frame) {
        return EmptyScore.valueOf();
    }

    @Override public Score calculateScore() {
        return EmptyScore.valueOf();
    }

    @Override public ScoreCalculator add(Pitch pitch) {
        int nextScore = this.score + pitch.getPinCount();
        if (pitch.isSpare()) {
            return new SpareScoreCalculator(nextScore);
        }

        return new FinishedScoreCalculator(nextScore);
    }
}
