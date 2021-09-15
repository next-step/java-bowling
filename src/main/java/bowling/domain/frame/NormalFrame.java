package bowling.domain.frame;

import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;

public class NormalFrame extends Frame {
    private static final int MAX_NORMAL_FRAME_NO = 9;
    private int frameNo;

    NormalFrame() {
        state = new Ready();
        frameNo = 1;
    }

    NormalFrame(int frameNo) {
        state = new Ready();
        this.frameNo = frameNo;
    }

    @Override
    public Frame next(int number) {
        if (!state.stateFinish()) {
            state = state.bowl(number);
            return this;
        }
        if (frameNo == MAX_NORMAL_FRAME_NO) {
            nextFrame = new FinalFrame().next(number);
            return nextFrame;
        }
        nextFrame = new NormalFrame(frameNo + 1).next(number);
        return nextFrame;
    }

    public int total() {
        if (state.scoreFinish()) {
            return state.getScoreCount();
        }
        if (hasNextFrame()) {
            return nextFrame.total(state.getScoreCount(), state.getBonusCount() - 1);
        }
        return 0;
    }

    public int total(int beforTotal, int leftCount) {
        if (leftCount == 1 && (state instanceof Miss || state instanceof Spare)) {
            return beforTotal + state.getScoreCount();
        }
        if (leftCount == 0) {
            return beforTotal + state.getFirstCount();
        }
        if (hasNextFrame()) {
            return nextFrame.total(beforTotal + state.getScoreCount(), leftCount - 1);
        }
        return 0;
    }

    @Override
    public boolean finish() {
        return state.stateFinish();
    }

    @Override
    public boolean hasBonus() {
        return false;
    }

    @Override
    public int bonusFirstCount() {
        return 0;
    }

    @Override
    public boolean hasBonusSecond() {
        return false;
    }

    @Override
    public int bonusSecondCount() {
        return 0;
    }
}
