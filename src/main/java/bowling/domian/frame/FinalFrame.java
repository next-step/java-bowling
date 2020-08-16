package bowling.domian.frame;

import bowling.domian.frame.exception.FrameEndException;
import bowling.domian.state.running.Ready;
import bowling.domian.state.finished.Spare;
import bowling.domian.state.State;
import bowling.domian.state.finished.Strike;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private State normalState;
    private State bonusState;

    public FinalFrame() {
        this.normalState = new Ready();
        this.bonusState = new Ready();
    }

    @Override
    public int getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame bowl(int falledPinsCount) {
        if (!normalState.isFinished()) {
            normalState = normalState.bowl(falledPinsCount);
            return this;
        }

        if (isNormalStrikeOrSpare() && isBonusReady()) {
            bonusState = bonusState.bowl(falledPinsCount);
            return this;
        }

        throw new FrameEndException();
    }

    public boolean isFrameEnd() {
        if (!normalState.isFinished()) {
            return false;
        }

        return !isNormalStrikeOrSpare() || !isBonusReady();
    }

    private boolean isNormalStrikeOrSpare() {
        return normalState instanceof Spare ||
                normalState instanceof Strike;
    }

    private boolean isBonusReady() {
        return bonusState instanceof Ready;
    }

}
