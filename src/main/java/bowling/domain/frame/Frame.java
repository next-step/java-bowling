package bowling.domain.frame;

import bowling.domain.state.State;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

public abstract class Frame {
    protected State state;
    protected Frame nextFrame;

    public abstract Frame next(int number);

    public abstract boolean finish();

    public abstract int total();

    public abstract int total(int beforTotal, int leftCount);

    public boolean hasNextFrame() {
        return !ObjectUtils.isEmpty(nextFrame);
    }

    public State getState() {
        return state;
    }

    public boolean hasFirstPin() {
        return state.hasFirstPin();
    }

    public int firstCount() {
        return state.getFirstCount();
    }

    public boolean hasSecondPin() {
        return state.hasSecondPin();
    }

    public int secondCount() {
        return state.getSecondCount();
    }

    public abstract boolean hasBonus();

    public abstract int bonusFirstCount();

    public abstract boolean hasBonusSecond();

    public abstract int bonusSecondCount();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(state, frame.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
