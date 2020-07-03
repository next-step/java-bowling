package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.Optional;

public interface Frame {
    Frame createNext(boolean isNextLast);

    boolean canAddMoreScore();

    void addScore(Score score);

    FrameScore getFrameScore();

    Optional<Score> getBonusScore();

    Optional<Score> calculateTotalScore();

    int getIndex();
}
