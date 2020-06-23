package bowling.domain.frame;

import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import bowling.domain.state.finish.Spare;

import java.util.Stack;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private static final int MAX_COUNT = 3;
    private static final int COMMON_COUNT = MAX_COUNT - 1;

    private final Stack<State> states = new Stack<>();

    private FinalFrame() {
        this.states.push(StateFactory.ready());
    }

    public static FinalFrame newInstance() {
        return new FinalFrame();
    }

    @Override
    public void bowl(final PinCount hitCount) {
        State currentState = this.getLastState();

        if (currentState.isFinish()) {
            states.push(StateFactory.hit(hitCount));
            return;
        }
        this.updateLastState(currentState.bowl(hitCount));
    }

    @Override
    public Frame initNextFrame() {
        return this;
    }

    @Override
    void addFrame(Frames frames) {
    }

    @Override
    public boolean isGameOver() {
        return this.isEndedBonusBowl() || getLastState().isMiss();
    }

    private boolean isEndedBonusBowl() {
        return this.states.size() == MAX_COUNT ||
                (this.states.stream().anyMatch(Spare.class::isInstance) && this.states.size() == COMMON_COUNT);
    }

    private State getLastState() {
        return this.states.peek();
    }

    private void updateLastState(final State state) {
        this.states.pop();
        this.states.push(state);
    }

    @Override
    public int getFrameNo() {
        return FrameNumber.MAX_NUMBER;
    }

    @Override
    public FrameResult getFrameResult() {
        return FrameResult.of(getDesc());
    }

    private String getDesc() {
        return this.states.stream()
                .map(State::getDesc)
                .collect(Collectors.joining("|"));
    }
}

