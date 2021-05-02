package bowling.domain;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.State;
import bowling.exception.NoMoreBowlActionsException;
import bowling.exception.NoMoreCountingActionException;

import java.util.Arrays;
import java.util.LinkedList;

import static bowling.util.BowlingFixture.*;

public final class FinalFrame implements Frame {

    private final FinalFrameOpportunity opportunity;
    private final LinkedList<State> states;
    private int size;

    public static final Frame initialize() {
        return new FinalFrame();
    }

    private FinalFrame() {
        this.states = new LinkedList<>(Arrays.asList(State.ready()));
        this.size = ZERO;
        this.opportunity = FinalFrameOpportunity.initialize();
    }

    @Override
    public final Frame bowl(HitCount hitCount) {
        validateFinish();
        opportunity.next();
        size = states.size();
        State state = states.getLast();
        if (state.isAllPinClear()) {
            states.add(getBonusPitch().bowl(hitCount));
            return this;
        }
        states.add(state.bowl(hitCount));
        return this;
    }

    private final State getBonusPitch() {
        return FirstBowl.from(ZERO);
    }

    private final void validateFinish() {
        if (isFinish()) {
            throw new NoMoreBowlActionsException();
        }
    }

    @Override
    public final boolean isFinish() {
        return opportunity.isFinish(isBonusBowlExit());
    }

    private final boolean isBonusBowlExit() {
        return states.stream()
                .anyMatch(State::isAllPinClear);
    }

    @Override
    public final int index() {
        return FRAME_LAST_INDEX;
    }

    @Override
    public final int size() {
        return size;
    }

    @Override
    public final int firstCount() {
        return states.get(ONCE).firstCount();
    }

    @Override
    public final int secondCount() {
        return states.get(TWICE).secondCount();
    }

    @Override
    public final int thirdCount() {
        validateSize();
        return states.getLast().secondCount();
    }

    private final void validateSize() {
        if (!isSizeThird()) {
            throw new NoMoreCountingActionException();
        }
    }

    private final boolean isSizeThird() {
        return size == THIRD;
    }

}
