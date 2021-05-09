package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Score;

public interface Frame {
    Frame next(int index);
    Frame roll(HitNumber rollNumber);
    Frame addScore(int score);
    boolean isFinished();
    boolean canAccumulate();
    Score totalScore();
}
