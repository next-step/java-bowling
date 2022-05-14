package bowling.model.frame;

import bowling.model.Pins;

public interface Frame {

    FrameNumber number();

    boolean isFinal();

    boolean isEnd();

    Frame next(Pins countOfHit);

    boolean hasRemainCount();

    Frame addScore(Pins countOfHit);
}
