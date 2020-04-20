package bowling.domain.frame.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Objects;
import java.util.Optional;

public class SpareScore implements Score {
    private int score;

    public SpareScore(int score) {
        this.score = score;
    }

    @Override public Optional<Integer> calculateScore(Frame frame) {
        if (Objects.isNull(frame)) {
            return Optional.empty();
        }
        return frame.getPinCountForOnePitch()
                .map(bonus -> bonus + score);
    }

    @Override public Optional<Integer> calculateScore() {
        return Optional.of(score);
    }

    @Override public Score add(Pitch pitch) {
        return null;
    }
}
