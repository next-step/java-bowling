package bowling.entity.frame;

import bowling.entity.BowlingBoard;
import bowling.entity.Pin;

public interface Frame {
    int frameNo();

    Frame bowl(Pin fallenPin);

    void addFrameResult(BowlingBoard bowlingBoard);

    BowlingBoard bowlingBoard();

    boolean bowlingGameEnd();
}
