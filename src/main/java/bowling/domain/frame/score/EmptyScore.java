package bowling.domain.frame.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Optional;

public class EmptyScore implements Score {

    public EmptyScore() {
    }

    @Override public Optional<Integer> calculateScore(Frame frame) {
        return Optional.empty();
    }

    @Override public Optional<Integer> calculateScore() {
        return Optional.empty();
    }

    @Override public Score add(Pitch pitch) {
        if (pitch.isStrike()) {
            return new StrikeScore();
        }

        return new FirstScore(pitch.getPinCount());
    }
}
