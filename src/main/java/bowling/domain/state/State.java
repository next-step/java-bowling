package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public interface State {

    boolean isFinish();

    Score getScore();

    State next(PinCount pinCount);

    Score calculateBonusScore(Score beforeScore);

    String getDesc();
}
