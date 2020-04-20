package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Optional;

public class FinishedScore implements Score {
    private int score;

    public FinishedScore(int score) {
        this.score = score;
    }

    @Override public Optional<Integer> calculateScore(Frame frame) {
        return calculateScore();
    }

    @Override public Optional<Integer> calculateScore() {
        return Optional.of(score);
    }

    @Override public Score add(Pitch pitch) {
        return null;
    }
}
