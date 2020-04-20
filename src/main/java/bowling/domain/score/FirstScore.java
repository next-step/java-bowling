package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Optional;

public class FirstScore implements Score {
    private int score;

    public FirstScore(int score) {
        this.score = score;
    }

    @Override public Optional<Integer> calculateScore(Frame frame) {
        return Optional.empty();
    }

    @Override public Optional<Integer> calculateScore() {
        return Optional.empty();
    }

    @Override public Score add(Pitch pitch) {
        int nextScore = this.score + pitch.getPinCount();
        if (pitch.isSpare()) {
            return new SpareScore(nextScore);
        }

        return new FinishedScore(nextScore);
    }
}
