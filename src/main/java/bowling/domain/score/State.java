package bowling.domain.score;

import bowling.domain.pin.Pins;

public interface State {

    State roll(Pins pin);

    boolean isGameOver();

    Score calculate(Score baseScore);

    Score getScore();

    boolean canRoll(State state);

}
