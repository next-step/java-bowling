package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    boolean isFinish();
    State bowl(Score score);
    int bonusCount();
}
