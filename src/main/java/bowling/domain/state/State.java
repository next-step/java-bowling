package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.ScoreV2;

public abstract class State {

    public abstract State bowl(Pin pin);
    public abstract boolean isFinished();

    public abstract ScoreV2 getScore();

    public abstract ScoreV2 calculateAdditionalScore(ScoreV2 scoreV2);

    public abstract String describe();
}
