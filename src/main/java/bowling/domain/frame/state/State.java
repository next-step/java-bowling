package bowling.domain.frame.state;

import bowling.domain.Score;

public interface State {
    State nextState();

    boolean hasNext();

    void bowl(int killedPins);

    Score calculateScore();

    boolean isCalculable();
}
