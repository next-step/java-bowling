package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.Score;
import bowling.entity.TotalScore;

public interface Frame {
    int frameNo();

    Frame bowl(Pin fallenPin);

    void addFrameResult(BowlingBoard bowlingBoard, TotalScore totalScore);

    BowlingBoard bowlingBoard();

    boolean bowlingGameEnd();

    void addScore(TotalScore totalScore);
}
