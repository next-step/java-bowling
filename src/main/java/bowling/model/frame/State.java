package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.frame.state.Score;

public interface State {

    State bowl(Pin pin);

    Score getScore();

    Score calculate(Score score);

    String printResult();

    boolean isFinished();
}
