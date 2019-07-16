package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.None;
import bowling.model.frame.state.Score;

import static bowling.model.frame.FrameNumber.NUMBER_OF_START_FRAME;
import static bowling.model.frame.state.Score.DEFAULT;
import static java.lang.Boolean.FALSE;


public class NormalFrame extends Frame {

    private Frame next;
    private State state;

    private NormalFrame(FrameNumber frameNumber) {
        super(frameNumber);
        this.state = None.getInstance();
    }

    private Frame nextFrame() {
        FrameNumber nextFrameNumber = nextFrameNumber();
        if (nextFrameNumber.isFinalNumber()) {
            return FinalFrame.of();
        }
        return NormalFrame.of(nextFrameNumber);
    }

    static Frame ofFirst() {
        return of(NUMBER_OF_START_FRAME);
    }

    static Frame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    Score getScore() {
        Score score = state.getScore();
        if (score.isCompleted()) {
            return score;
        }
        return next.calculate(score);
    }

    @Override
    Score calculate(Score prevScore) {
        Score calculatedScore = state.calculate(prevScore);
        if (calculatedScore.isCompleted()) {
            return calculatedScore;
        }

        if (next == null) {
            return DEFAULT;
        }
        return next.calculate(calculatedScore);
    }

    @Override
    public Frame bowl(Pins downPins) {
        state = state.bowl(downPins);
        if (state.isFinished()) {
            this.next = nextFrame();
            return next;
        }
        return this;
    }

    @Override
    public FrameResult getResult() {
        return FrameResult.of(getScore(), state);
    }

    @Override
    public boolean isGameOver() {
        return FALSE;
    }
}