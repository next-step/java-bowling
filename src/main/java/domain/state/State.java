package domain.state;

import domain.Pins;
import domain.Score;

public interface State {

    State update(Pins fallenPins);

    boolean isClosed();

    String printState();

    Score getScore();

    Score updateScore(Score score);
}
