package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

public class EmptyScoreCalculator implements ScoreCalculator {

    public EmptyScoreCalculator() {
    }

    @Override public Score calculateScore(Frame frame) {
        return EmptyScore.valueOf();
    }

    @Override public Score calculateScore() {
        return EmptyScore.valueOf();
    }

    @Override public ScoreCalculator add(Pitch pitch) {
        if (pitch.isStrike()) {
            return new StrikeScoreCalculator();
        }

        return new FirstScoreCalculator(pitch.getPinCount());
    }
}
