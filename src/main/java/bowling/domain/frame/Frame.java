package bowling.domain.frame;

import bowling.domain.state.BowlingPin;

public interface Frame {
    void bowl(BowlingPin bowlingPin);
    Frame next(int size);
    boolean isDone();
    String frameState();
}
