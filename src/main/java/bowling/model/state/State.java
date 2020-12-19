package bowling.model.state;

import bowling.model.Pins;
import bowling.model.Score;

public interface State {

    Score calculateScore(Score score);

    boolean isFinished();

    State bowling(Pins fallenPin);

    Score score();
}
