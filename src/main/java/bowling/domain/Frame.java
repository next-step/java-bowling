package bowling.domain;

import bowling.annotations.ForUI;
import bowling.domain.state.State;

public interface Frame {
    void bowl(int knockedOutCount);

    Frame addNextFrame();

    boolean isEnd();

    boolean isNinthFrame();

    boolean isFinalFrame();

    Frame next();

    @ForUI
    int getFrameNumber();

    @ForUI
    State getState();

    @ForUI
    Score getScore();

    Score additionalCalculate(Score score);
}
