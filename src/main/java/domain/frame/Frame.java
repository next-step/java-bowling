package domain.frame;

import domain.Pins;
import domain.Score;
import domain.state.State;

public interface Frame {

    Frame fillFrame(Pins fallenPins);

    boolean isGameOver();

    FrameIndex getIndex();

    State getState();

    boolean isSameFrame(Frame targetFrame);

    Score getScore();
}
