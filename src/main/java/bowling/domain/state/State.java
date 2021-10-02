package bowling.domain.state;

import bowling.domain.score.Pin;
import bowling.domain.scoretmp.Score;

public interface State {

    boolean isFinished();

    Score createScore();

    State bowl(Pin pin);

}
