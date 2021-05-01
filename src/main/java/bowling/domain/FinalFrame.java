package bowling.domain;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.State;
import bowling.exception.NoMoreBowlActionsException;

import java.util.Arrays;
import java.util.LinkedList;

import static bowling.util.BowlingFixture.DEFAULT_COUNT;
import static bowling.util.BowlingFixture.FRAME_LAST_INDEX;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class FinalFrame implements Frame {

    private final FinalFrameOpportunity opportunity;
    private final int index;

    private LinkedList<State> states;

    public static final Frame newInstance() {
        return new FinalFrame();
    }

    private FinalFrame() {
        this.states = new LinkedList<>(Arrays.asList(State.ready()));
        this.index = FRAME_LAST_INDEX;
        this.opportunity = FinalFrameOpportunity.initialize();
    }

    @Override
    public final Frame bowl(HitCount hitCount) {
        validateFinish();
        State state = states.getLast();
        if (state.isAllPinClear()) {
            states.add(getBonusPitch().bowl(hitCount));
            return this;
        }
        states.removeLast();
        states.add(state);
        return this;
    }

    private final State getBonusPitch() {
        return FirstBowl.from(DEFAULT_COUNT);
    }

    private final void validateFinish() {
        if (isFinish()) {
            throw new NoMoreBowlActionsException();
        }
    }

    @Override
    public final boolean isFinish() {
        return opportunity.isFinish();
    }

    @Override
    public final int index() {
        return index;
    }

}
