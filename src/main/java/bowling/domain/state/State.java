package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;

public interface State {
    boolean isFinished();

    State bowl(Pin pin);

    String viewString();

    Score score();

    Score calculateAdditionalScore(Score beforeScore);


}
