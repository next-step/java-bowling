package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private FinalFitch state;

    public FinalFrame() {
        state = new FinalFitch();
    }

    @Override
    public void bowl(Pins pins) {
        state.bowl(pins);
    }

    @Override
    public Pins getFirstPin() {
        return state.getFirstPin();
    }

    @Override
    public Pins getSecondPin() {
        return state.getSecondPin();
    }

    @Override
    public Pins getBonusPin() {
        return state.getBonusPins();
    }

    @Override
    public boolean isFirstFitchDone() {
        return state.isFirstFitchDone();
    }

    @Override
    public boolean isSecondFitchDone() {
        return state.isSecondFitchDone();
    }

    @Override
    public boolean isBonusFitchDone() {
        return state.isBonusFitchDone();
    }


    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame next() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
