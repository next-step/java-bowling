package bowling.model.frame;

import bowling.model.Pins;

public interface Frame {

    Frame next(Pins countOfHit);

    boolean isEnd();

    boolean isFinal();

    FrameNumber number();
}
