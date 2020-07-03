package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.FrameScore;

import java.util.Optional;

public interface Frame {
    Frame createNext(boolean isNextLast);

    boolean canAddMoreScore();

    void addScore(Score score);

    FrameScore getFrameScore();

    Optional<Score> calculateTotalScore();

    int getIndex();
}
