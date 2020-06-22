package bowling.domain.frame;

import bowling.domain.dto.FrameResult;
import bowling.domain.pin.PinCount;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import bowling.domain.state.finish.Spare;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private static final int MAX_COUNT = 3;
    private static final int COMMON_COUNT = MAX_COUNT - 1;

    private final LinkedList<State> states = new LinkedList<>();

    private FinalFrame() {
        this.states.add(StateFactory.ready());
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public FinalFrame bowl(final PinCount hitCount) {
        State currentState = this.getLastState();

        if (currentState.isFinish()) {
            states.add(StateFactory.hit(hitCount));
            return this;
        }

        this.states.removeLast();
        this.states.add(currentState.bowl(hitCount));
        return this;
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
        return this.states.getLast();
    }

    @Override
    public int getNo() {
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

