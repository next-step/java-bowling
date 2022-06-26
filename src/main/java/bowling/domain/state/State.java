package bowling.domain.state;

import bowling.domain.Score;

public interface State {

    AbstractState bowl(int fallenPins);

    Score getScore();
}
