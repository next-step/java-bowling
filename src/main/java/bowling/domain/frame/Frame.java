package bowling.domain.frame;

import bowling.domain.PinCount.PinCount;
import bowling.domain.State.State;
import bowling.domain.score.Score;

public interface Frame {

    void addPinCount(int pinCount);

    void addPinCount(PinCount pinCount);

    boolean isDone();

    Frame nextFrame();

    FrameNumber number();

    boolean isLast();

    State currentState();

    Score score();

    Score calculatedScore(Score scoreToCalculate);

}
