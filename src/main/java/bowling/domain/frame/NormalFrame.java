package bowling.domain.frame;

import bowling.domain.state.Ready;

public class NormalFrame extends Frame {
    NormalFrame() {
        state = new Ready();
    }

    @Override
    public Frame next(int number) {
        if(!state.finish()){
            state = state.bowl(number);
            return this;
        }
        return new NormalFrame().next(number);
    }

    @Override
    public boolean finish() {
        return state.finish();
    }

    @Override
    public boolean hasBonusFirst() {
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
