package bowling.frame;

import bowling.frame.state.Set;
import bowling.frame.state.State;
import bowling.score.Pin;

import java.util.LinkedList;

public class FinalFrame extends Frame {

    public static final int FINISHED_FRAME_NUMBER = 11;

    private LinkedList<State> states = new LinkedList<>();

    private FinalFrame(int frameNumber) {
        super(frameNumber);
        states.add(this.state);
    }

    private FinalFrame() {
        super(FINAL_FRAME_NUMBER);
        states.add(this.state);
    }

    public static Frame create(int frameNumber) {
        return new FinalFrame(frameNumber);
    }

    public static Frame init() {
        return new FinalFrame();
    }

    @Override
    protected Frame bowl(String fellPins) {
        State currentState = states.getLast();
        Pin pin = Pin.bowl(fellPins);

        if (currentState.isFinish()) {
            states.add(Set.init().bowl(pin));
            return this;
        }

        states.removeLast();
        states.add(currentState.bowl(pin));
        return this;
    }

    @Override
    protected Frame next() {
        return FinalFrame.create(this.frameNumber + INCREASE_FRAME_NUMBER);
    }

    @Override
    public boolean isFinish() {
        return this.frameNumber == FINISHED_FRAME_NUMBER;
    }

    @Override
    public State getState() {
        return this.states.getLast();
    }

}
