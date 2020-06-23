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

    private final Stack<State> states2 = new Stack<>();

    private FinalFrame() {
        this.states2.push(StateFactory.ready());
    }

    public static FinalFrame newInstance() {
        return new FinalFrame();
    }

    @Override
    public FinalFrame bowl(final PinCount hitCount) {
        State currentState = this.getLastState();

        if (currentState.isFinish()) {
            states2.push(StateFactory.hit(hitCount));
            return this;
        }

        this.updateLastState(currentState.bowl(hitCount));
        return this;
    }

    @Override
    public boolean isGameOver() {
        return this.isEndedBonusBowl() || getLastState().isMiss();
    }

    private boolean isEndedBonusBowl() {
        return this.states2.size() == MAX_COUNT ||
                (this.states2.stream().anyMatch(Spare.class::isInstance) && this.states2.size() == COMMON_COUNT);
    }

    private State getLastState() {
        return this.states2.peek();
    }

    private void updateLastState(final State state) {
        this.states2.pop();
        this.states2.push(state);
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
        return this.states2.stream()
                .map(State::getDesc)
                .collect(Collectors.joining("|"));
    }
}

