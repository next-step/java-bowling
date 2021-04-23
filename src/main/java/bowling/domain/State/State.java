package bowling.domain.State;

import bowling.domain.PinCount.PinCount;
import bowling.domain.score.Score;

public interface State {

    State newState(PinCount pinCount);

    boolean isClosed();

    String stateInString();

    Score score();

    Score calculatedScore(Score scoreToCalculate);

}
