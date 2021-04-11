package bowling.domain.frame;

import bowling.domain.State.State;
import bowling.dto.FrameResult;

public interface Frame {

    void addPinCount(int pinCount);

    void addPinCount(PinCount pinCount);

    boolean isDone();

    Frame nextFrame();

    FrameNumber number();

    boolean isLast();

//    FrameResult result();

    State currentState();

}
