package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;

public interface State {

    State bowl(Pin pin);

    boolean isFinished();

    Score getScore();

    Score addBonusScore(Score score);
}
