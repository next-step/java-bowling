package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public abstract class State {

    public abstract State bowl(Pin pin);
    public abstract boolean isFinished();
    public abstract Score getScore();
    public abstract Score calculateAdditionalScore(Score score);
    public abstract String describe();
}
