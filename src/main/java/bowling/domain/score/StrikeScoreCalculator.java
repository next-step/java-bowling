package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Objects;

public class StrikeScoreCalculator implements ScoreCalculator {
    private static final int DEFAULT_SCORE = 10;
    private int score;

    public StrikeScoreCalculator() {
        this.score = DEFAULT_SCORE;
    }

    @Override public Score calculateScore(Frame frame) {
        if (Objects.isNull(frame)) {
            return EmptyScore.valueOf();
        }
        return frame.getScoreForTwoPitches().add(new CompleteScore(score));
    }

    @Override public Score calculateScore() {
        return new CompleteScore(score);
    }

    @Override public ScoreCalculator add(Pitch pitch) {
        return null;
    }
}
