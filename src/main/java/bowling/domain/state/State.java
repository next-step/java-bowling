package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

public abstract class State {
    protected Pin firstPin;
    protected Pin secondPin;
    protected Score score;

    public abstract State bowl(int pin);

    public abstract Score getScore();

    public abstract boolean stateFinish();

    public boolean scoreFinish() {
        if (ObjectUtils.isEmpty(score)) {
            return false;
        }
        return score.finish();
    }

    public boolean hasFirstPin(){
        return !ObjectUtils.isEmpty(firstPin);
    }

    public boolean hasSecondPin(){
        return !ObjectUtils.isEmpty(secondPin);
    }

    public int getFirstCount() {
        return firstPin.count();
    }

    public int getSecondCount() {
        return secondPin.count();
    }

    public int getScoreCount() {
        return score.getScore();
    }

    public int getBonusCount() {
        return score.getBonusCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(firstPin, state.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
