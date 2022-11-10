package bowling.domain.frame.state;

import bowling.domain.frame.Score;

public interface State {
    State bowl(int pins);

    Score createScore();

    boolean isFinish();

    boolean canBonusBowl();
}
