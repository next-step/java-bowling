package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Objects;

public class SpareScoreCalculator implements ScoreCalculator {
    private int score;

    public SpareScoreCalculator(int score) {
        this.score = score;
    }

    @Override public Score calculateScore(Frame frame) {
        if (Objects.isNull(frame)) {
            return EmptyScore.valueOf();
        }
        return frame.getScoreForOnePitch().add(new CompleteScore(score));
    }

    @Override public Score calculateScore() {
        return new CompleteScore(score);
    }

    @Override public ScoreCalculator add(Pitch pitch) {
        return null;
    }
}
