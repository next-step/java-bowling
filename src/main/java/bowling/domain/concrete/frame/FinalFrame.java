package bowling.domain.concrete.frame;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.FinalFrameCount;
import bowling.domain.engine.frame.Frame;
import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.StateFactory;
import bowling.dto.FinalFrameExporter;

import java.util.LinkedList;


public class FinalFrame implements Frame {

    private final LinkedList<State> states;
    private final FinalFrameCount finalFrameCount;

    private FinalFrame(LinkedList<State> states) {
        this.states = states;
        this.finalFrameCount = new FinalFrameCount();
    }

    public static FinalFrame init() {
        LinkedList<State> states = new LinkedList<>();
        states.add(StateFactory.ready());
        return new FinalFrame(states);
    }

    @Override
    public void roll(RollResult rollResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        State lastState = states.removeLast().transit(rollResult);
        finalFrameCount.changeCount(lastState);
        states.add(lastState);

        if (lastState.isFinished() && !finalFrameCount.isFinished()) {
            states.add(StateFactory.ready());
        }
    }

    @Override
    public boolean isEnded() {
        return finalFrameCount.isFinished();
    }

    @Override
    public String export() {
        return FinalFrameExporter.export(states);
    }
}
