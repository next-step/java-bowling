package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Optional;

public interface Score {
    Optional<Integer> calculateScore(Frame frame);

    Optional<Integer> calculateScore();

    Score add(Pitch pitch);
}
