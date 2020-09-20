package bowling.domain.state;

import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;

public interface State {

    State bowl(Pin felledPin);

    boolean isEnd();

    Score calculate(Score baseScore);

    Score getScore();

    default boolean isFrameFinish(State state) {
        return state instanceof Miss || state instanceof Gutter;
    }
}
