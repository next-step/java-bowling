package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public abstract class State {
    public abstract State bowl(Pin pin);
    public abstract boolean isFinished();
    public abstract Score getScore();
    public abstract Score calculateAdditionalScore(Score score);
    public abstract List<Pin> pins();
    public abstract boolean isReady();
    public abstract boolean isFirstBowl();
    public abstract boolean isSpare();
    public abstract boolean isStrike();
}
