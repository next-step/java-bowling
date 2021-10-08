package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    public static final int FIRST_ROUND = 1;
    public static final int FINAL_ROUND = 10;

    private final int round;
    private State state;

    AbstractFrame(int round, State state) {
        this.round = round;
        this.state = state;
    }

    @Override
    public int round() {
        return round;
    }

    public abstract Frame createNextFrame();

    @Override
    public Frame bowling(Pin pin) {
        state = state.bowl(pin);
        if (state.isFinished()) {
            return createNextFrame();
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractFrame that = (AbstractFrame) o;
        return round == that.round && state.getClass() == that.state.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, state.getClass());
    }

}
