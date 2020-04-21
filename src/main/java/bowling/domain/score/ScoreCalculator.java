package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

import java.util.Optional;

public interface ScoreCalculator {
    Score calculateScore(Frame frame);

    Score calculateScore();

    ScoreCalculator add(Pitch pitch);
}
