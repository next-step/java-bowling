package com.jaeyeonling.bowling.frame.state;

import com.jaeyeonling.bowling.Counter;
import com.jaeyeonling.bowling.frame.BowlingSymbol;
import com.jaeyeonling.bowling.frame.KnockdownPins;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class FinalState extends ValidFrameState {

    private static final String DELIMITER = BowlingSymbol.DELIMITER.toString();
    private static final int START_INDEX = 0;
    private static final int DEFAULT_SIZE = 1;
    private static final int BONUS_ROUND = 3;

    private Counter roundCounter = new Counter();
    private List<FrameState> frameStates = new ArrayList<>();

    public FinalState() {
        ready();
    }

    @Override
    public boolean isFinished() {
        if (roundCounter.isLowerAndEquals(DEFAULT_SIZE)) {
            return false;
        }
        if (roundCounter.isHigherAndEquals(BONUS_ROUND)) {
            return true;
        }
        if (isFirstStrike()) {
            return false;
        }

        return isNotBonus();
    }

    @Override
    public String visualize() {
        return frameStates.stream()
                .map(FrameState::visualize)
                .collect(joining(DELIMITER));
    }

    @Override
    FrameState validBowl(final KnockdownPins knockdownPins) {
        roundCounter.count();

        if (getCurrentFrameState().isFinished()) {
            ready();
        }
        bowlAndReplace(knockdownPins);

        return this;
    }

    private FrameState getCurrentFrameState() {
        return frameStates.get(currentIndex());
    }

    private int currentIndex() {
        return frameStates.size() - DEFAULT_SIZE;
    }

    private void ready() {
        frameStates.add(new Ready());
    }

    private void bowlAndReplace(final KnockdownPins knockdownPins) {
        frameStates.set(currentIndex(), getCurrentFrameState().bowl(knockdownPins));
    }

    private boolean isFirstStrike() {
        return frameStates.get(START_INDEX) instanceof Strike;
    }

    private boolean isNotBonus() {
        return !(getCurrentFrameState() instanceof Finished);
    }
}
