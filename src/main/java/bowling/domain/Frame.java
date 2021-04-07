package bowling.domain;

import java.util.List;

public interface Frame {

    void addPinCount(int pinCount);

    void addPinCount(PinCount pinCount);

    boolean isDone();

    Frame nextFrame();

    FrameNumber number();

    boolean isLast();

    List<Integer> pinCounts();
}
