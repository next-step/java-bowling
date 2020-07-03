package bowling.domain.state;

import bowling.common.IntegerUtils;
import bowling.domain.Pin;

public abstract class State {
    private int remainPin;

    protected State(int remainPin) {
        this.remainPin = remainPin;
    }

    public static State ofNew() {
        return new New(Pin.MAX_COUNT);
    }

    public static State ofSpare(int remainPin) {
        return new Spare(remainPin);
    }

    public static State ofFinish() {
        return new Finish(IntegerUtils.ZERO);
    }

    public int getRemainPin() {
        return remainPin;
    }

    public abstract boolean isNew();

    public abstract boolean isFinish();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return remainPin == state.remainPin;
    }

    @Override
    public int hashCode() {
        return remainPin;
    }
}
