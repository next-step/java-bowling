package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;

public interface Frame {
    int frameNo();

    Frame pinResult(Pin fallenPin);

    void addFrameResult(BowlingBoard bowlingBoard);

    BowlingBoard bowlingBoard();

    boolean bowlingGameEnd();
}
