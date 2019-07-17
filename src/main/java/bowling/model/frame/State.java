package bowling.model.frame;

import bowling.model.DownPin;
import bowling.model.frame.state.Score;

public interface State {

    State bowl(DownPin downPin);

    Score getScore();

    Score calculate(Score score);

    String printResult();

    boolean isFinished();
}
