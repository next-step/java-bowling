package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State pitch(int fallenPins);

    boolean isFinish();

    int getPitchCount();

    Score getScore();

    int getTotalCount();
}
