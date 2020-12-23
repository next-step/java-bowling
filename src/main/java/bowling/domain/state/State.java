package bowling.domain.state;

import bowling.domain.Pitch;

public interface State {

    State bowl(Pitch pitch);

    boolean isFinish();

    int getScore();

    int calculateAdditionalState(int score);

    String toString();

}
