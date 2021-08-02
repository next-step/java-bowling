package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.InCalculableScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class State {

    public State downPins(DownedPins downedPins) {
        validate(downedPins);

        return nextState(downedPins);
    }

    private void validate(DownedPins downedPins) {
        if (Objects.isNull(downedPins)) {
            throw new IllegalArgumentException("DownedPin can't be null");
        }
    }

    protected abstract State nextState(DownedPins downedPins);

    public Score Score() {
        return InCalculableScore.init();
    }

    public boolean isEnd() {
        return false;
    }

    protected boolean isMiss() {
        return false;
    }

    protected boolean isClean() {
        return false;
    }

    public List<State> getState() {
        return Collections.singletonList(this);
    }

    public abstract List<Integer> getDownedPins();

}
