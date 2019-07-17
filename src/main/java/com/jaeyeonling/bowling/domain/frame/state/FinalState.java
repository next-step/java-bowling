package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.BowlingSymbol;
import com.jaeyeonling.bowling.domain.count.Count;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.frame.score.FrameScore;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class FinalState implements FrameState {

    private static final int START_INDEX = 0;
    private static final int DEFAULT_SIZE = 1;
    private static final int BONUS_ROUND = 3;

    private final List<FrameState> frameStates = new ArrayList<>();
    private Count count = Count.of();

    public FinalState() {
        ready();
    }

    @Override
    public boolean isFinished() {
        if (count.isLowerAndEquals(DEFAULT_SIZE)) {
            return false;
        }
        if (count.isHigherAndEquals(BONUS_ROUND)) {
            return true;
        }
        if (isFirstStrike()) {
            return false;
        }

        return doNotHaveBonusOfCurrentFrameState();
    }

    @Override
    public String toSymbol() {
        return frameStates.stream()
                .map(FrameState::toSymbol)
                .collect(joining(BowlingSymbol.DELIMITER));
    }

    @Override
    public FrameState bowl(final KnockdownPins knockdownPins) {
        if (isFinished()) {
            throw new FinishedFrameStateException();
        }

        incrementCount();

        if (isCurrentFrameStateFinished()) {
            ready();
        }
        bowlAndReplace(knockdownPins);

        return this;
    }

    @Override
    public FrameScore calculateScore(FrameScore base) {
        for (final FrameState frameState : frameStates) {
            base = frameState.calculateScore(base);
            if (base.isComplete()) {
                return base;
            }
        }

        return base;
    }

    @Override
    public FrameScore getFrameScore() {
        if (isFinished()) {
            final int totalScore = frameStates.stream()
                    .map(FrameState::getFrameScore)
                    .mapToInt(FrameScore::getScore)
                    .sum();

            return FrameScore.of(totalScore);
        }

        return FrameScore.UN_SCORE;
    }

    private void ready() {
        frameStates.add(new Ready());
    }

    private void incrementCount() {
        count = count.up();
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

    private boolean isFirstStrike() {
        return frameStates.get(START_INDEX) instanceof Strike;
    }

    private boolean doNotHaveBonusOfCurrentFrameState() {
        return !getCurrentFrameState().getClass().isAnnotationPresent(HaveBonus.class);
    }

    private void bowlAndReplace(final KnockdownPins knockdownPins) {
        frameStates.set(currentIndex(), getCurrentFrameState().bowl(knockdownPins));
    }
}