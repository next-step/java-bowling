package bowling.model.state;

import bowling.CannotBowlException;
import bowling.model.Score;

public interface State {
    State bowl(int countOfPin) throws CannotBowlException;

    boolean isFinish();

    Score calculateAdditionalScore(Score score);

    Score getScore();

    String getDesc();

}
