package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public interface State {
    boolean isFinished();

    State play(final PinCount pinCount);

    Score getScore();

    Score calculateBonusScore(Score score);

    String showIndication();
}
