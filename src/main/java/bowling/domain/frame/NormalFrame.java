package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import java.util.Objects;

public class NormalFrame implements Frame {

    private static final int FIRST_ROUND = 1;

    private final int round;
    private Frame nextFrame;
    private State state;

    private NormalFrame(int round, Frame nextFrame, State state) {
        this.round = round;
        this.nextFrame = nextFrame;
        this.state = state;
    }

    static Frame from(int round, Frame frame, State state) {
        return new NormalFrame(round, frame, state);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_ROUND, null, new Ready());
    }

    @Override
    public boolean isFinishState() {
        return state.isFinished();
    }

    @Override
    public void updateStateByPin(Pin pin) {
        state = state.bowl(pin);
    }

    @Override
    public Frame createNextFrame() {
        return nextFrame = new NormalFrame(round + 1, null, new Ready());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return round == that.round && Objects.equals(nextFrame, that.nextFrame)
            && state.getClass() == that.state.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, nextFrame, state.getClass());
    }

}
