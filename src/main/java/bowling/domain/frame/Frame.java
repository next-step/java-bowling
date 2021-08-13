package bowling.domain.frame;

import bowling.domain.framescore.*;
import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScores;

public interface Frame {
    void bowl(final TurnScore score);

    TurnScores turnScores();

    FrameScore frameScore();

    boolean isCompleted();

    int currentFrameNumber();
}
