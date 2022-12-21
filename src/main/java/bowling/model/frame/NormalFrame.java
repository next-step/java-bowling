package bowling.model.frame;


import bowling.model.Pin;
import bowling.model.state.Ready;
import bowling.model.state.State;

public class NormalFrame implements Frame {

    public static final int LAST_FRAME_NUMBER = 9;
    public static final int FIRST_FRAME_NUMBER = 1;

    private final int number;
    private State state;

    NormalFrame(int number) {
        this.number = number;
        this.state = new Ready();
    }

    public static NormalFrame first() {
        return new NormalFrame(FIRST_FRAME_NUMBER);
    }

    @Override
    public void bowl(Pin pin) {
        state = state.bowl(pin);
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public Frame nextFrame() {
        if (isLastFrame()) {
            return new FinalFrame();
        }
        return new NormalFrame(number + 1);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    private boolean isLastFrame() {
        return number == LAST_FRAME_NUMBER;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public State getState() {
        return state;
    }
}
