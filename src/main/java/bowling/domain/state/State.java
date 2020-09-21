package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public interface State {
    State bowl(Pin pin);

    boolean isFinish();

    Score getScore();

    Score calculateAdditionalScore(Score score);

    String record();

    boolean canBowlFinalFrame();

    boolean isStrike();

    boolean isSpare();
}
