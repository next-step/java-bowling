package bowling_step3.domain.state;

import bowling_step3.domain.Score;

public interface State {
    State pitch(int fallenPins);

    boolean isFinish();

    int getPitchCount();

    Score getScore();

    int getTotalCount();
}
