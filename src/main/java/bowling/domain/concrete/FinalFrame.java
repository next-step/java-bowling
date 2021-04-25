package bowling.domain.concrete;

import bowling.domain.engine.frame.Frame;
import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.StateFactory;
import bowling.domain.engine.roll.RollResult;


public class FinalFrame implements Frame {

    private State state;

    private FinalFrame(State state) {
        this.state = state;
    }

    public static FinalFrame init() {
        return new FinalFrame(StateFactory.ready());
    }

    @Override
    public void roll(RollResult rollResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        if (state.isFinished() && state.canPromoteToBonusState()) {
            state = state.continueInBonus().transit(rollResult);
            return ;
        }

        state = state.transit(rollResult);
    }

    @Override
    public boolean isEnded() {
        return state.isFinished() && !state.canPromoteToBonusState();
    }

    @Override
    public String export() {
        return state.export();
    }
}
