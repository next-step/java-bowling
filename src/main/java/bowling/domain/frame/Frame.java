package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.score.Calculator;

public interface Frame {

    int MAX_FRAME_NUMBER = 10;

    Frame bowl(final int pinCount);

    boolean isFinish();

    Frame createNext();

    Frame getNext();

    State getState();

    Frame findLast();

    Calculator getCurrenteCalculator();

    Calculator getScoreCalculate(Calculator calculator);
}
