package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pitch.Pitch;

public interface ScoreCalculator {
    Score calculateScore(Frame frame);

    Score calculateScore();

    ScoreCalculator add(Pitch pitch);
}
