package bowling.domain.concrete.frame;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Frame;
import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.StateFactory;

public class NormalFrame implements Frame {

    private State state;

    private NormalFrame(State state) {
        this.state = state;
    }

    public static NormalFrame init() {
        return new NormalFrame(StateFactory.ready());
    }

    @Override
    public void roll(RollResult rollResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        this.state = state.transit(rollResult);
    }

    @Override
    public boolean isEnded() {
        return state.isFinished();
    }

    @Override
    public String export() {
        return state.export();
    }
}
