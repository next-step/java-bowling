package bowling.domain.state;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;

public interface State {

    State bowl(Pins pins);

    boolean isFinish();

    Score getScore();

    Score calculateAdditionalScore(Score score);
}
