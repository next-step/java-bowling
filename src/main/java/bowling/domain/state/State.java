package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    State play(int fallenPin);

    boolean isFinish();

    int getPitchCount();

    Score getScore();

    int getTotalCount();
}
