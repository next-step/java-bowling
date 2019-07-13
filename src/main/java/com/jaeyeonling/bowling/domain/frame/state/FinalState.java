package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.Counter;
import com.jaeyeonling.bowling.domain.frame.BowlingType;
import com.jaeyeonling.bowling.domain.frame.KnockdownPins;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class FinalState extends ValidFrameState {

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

        return nonBonusOfCurrentFrameState();
    }

    @Override
    public String toSymbol() {
        return frameStates.stream()
                .map(FrameState::toSymbol)
                .collect(joining(BowlingType.DELIMITER));
    }

    @Override
    FrameScore validCalculateScore(final FrameScore base) {
        return null;
    }

    @Override
    FrameState validBowl(final KnockdownPins knockdownPins) {
        roundCounter.count();

        if (isCurrentFrameStateFinished()) {
            ready();
        }
        bowlAndReplace(knockdownPins);

        return this;
    }

    private FrameState getCurrentFrameState() {
        return frameStates.get(currentIndex());
    }

    private boolean isCurrentFrameStateFinished() {
        return getCurrentFrameState().isFinished();
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

    private boolean nonBonusOfCurrentFrameState() {
        return !(getCurrentFrameState() instanceof Finished);
    }
}
