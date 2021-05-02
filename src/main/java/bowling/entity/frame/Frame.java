package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.Score;

public interface Frame {
    int frameNo();

    Frame bowl(Pin fallenPin);

    void addFrameResult(BowlingBoard bowlingBoard, int totalScore);

    BowlingBoard bowlingBoard();

    boolean bowlingGameEnd();

    Score calculate(Score score);
}
