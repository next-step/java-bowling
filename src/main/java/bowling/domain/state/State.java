package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score2;

public interface State {

    boolean isFinish();

    Score2 getScore();

    State next(PinCount pinCount);
}
