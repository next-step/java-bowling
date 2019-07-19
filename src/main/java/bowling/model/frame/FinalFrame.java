package bowling.model.frame;

import bowling.model.DownPin;
import bowling.model.frame.state.FinalState;
import bowling.model.frame.state.Score;

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
    Score getScore() {
        return states.getScore();
    }

    @Override
    Score calculate(Score prevScore) {
        return states.calculate(prevScore);
    }

    @Override
    public Frame bowl(DownPin downPin) {
        states.bowl(downPin);
        return this;
    }

    @Override
    public FrameResult getResult() {
        return FrameResult.of(states.getScore(), states);
    }

    @Override
    public boolean isGameOver() {
        return states.isFinished();
    }
}