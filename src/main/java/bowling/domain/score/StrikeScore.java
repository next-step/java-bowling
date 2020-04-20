package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Objects;
import java.util.Optional;

public class StrikeScore implements Score {
    private static final int DEFAULT_SCORE = 10;
    private int score;

    public StrikeScore() {
        this.score = DEFAULT_SCORE;
    }

    @Override public Optional<Integer> calculateScore(Frame frame) {
        if (Objects.isNull(frame)) {
            return Optional.empty();
        }
        return frame.getPinCountForTwoPitches()
                .map(bonus -> bonus + score);
    }

    @Override public Optional<Integer> calculateScore() {
        return Optional.of(score);
    }

    @Override public Score add(Pitch pitch) {
        return null;
    }
}
