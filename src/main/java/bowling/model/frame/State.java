package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Score;

public interface State {

    State bowl(Pins pins);

    Score getScore();

    Score calculate(Score score);

    String printResult();

    boolean isFinished();
}
