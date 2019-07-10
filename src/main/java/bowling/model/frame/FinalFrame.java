package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.FinalState;

import static bowling.model.frame.FrameNumber.NUMBER_OF_FINAL_FRAME;

public class FinalFrame extends Frame {

    private State states;

    private FinalFrame() {
        super(NUMBER_OF_FINAL_FRAME);
        this.states = FinalState.valueOf();
    }

    static Frame of() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(Pins downPins) {
        states.bowl(downPins);
        return this;
    }

    @Override
    public boolean isGameOver() {
        return states.isFinished();
    }

    @Override
    public String printResult() {
        return states.printResult();
    }
}