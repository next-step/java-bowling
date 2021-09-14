package bowling.domain.frame;

import bowling.domain.state.State;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

public abstract class Frame {
    protected State state;

    public abstract Frame next(int number);

    public abstract boolean finish();

    public State getState() {
        return state;
    }

    public boolean hasFirstCount(){
        return !ObjectUtils.isEmpty(state.getFirstPin());
    }

    public int firstCount(){
        return state.getFirstPin().count();
    }

    public boolean hasSecondCount(){
        return !ObjectUtils.isEmpty(state.getSecondPin());
    }

    public int secondCount(){
        return state.getSecondPin().count();
    }

    public abstract boolean hasBonusFirst();

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
