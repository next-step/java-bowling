package bowling.domain.state;

import bowling.domain.Score;

public interface State {

    State bowl(int countOfPins);

    boolean isFinish();

    int getPitchCount();

    Score getScore();

    int getTotalCount();

}
