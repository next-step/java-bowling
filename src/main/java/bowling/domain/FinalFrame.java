package bowling.domain;

import bowling.domain.state.NotBowled;
import bowling.domain.state.State;
import bowling.exception.NoRemainingFrameException;

import java.util.LinkedList;

import static bowling.common.SymbolConstants.NOT_THROWN;
import static bowling.domain.Score.*;

public class FinalFrame implements Frame{
    private final int frameNo = 10;
    private LinkedList<State> states = new LinkedList<>();

    public FinalFrame() {
        states.add(NotBowled.init());
    }

    @Override
    public Frame bowl(int pins) {
        if(isOver()) {
            throw new NoRemainingFrameException();
        }

        State lastState = states.getLast();

        if(lastState.isOver()) {
            states.add(lastState.bowl(pins));
            return this;
        }

        states.removeLast();
        states.add(lastState.bowl(pins));
        return this;
    }

    @Override
    public boolean isOver() {
        int totalTries = states.stream()
                .map(state -> state.getNumberOfTries())
                .reduce(NO_TRY, (a, b) -> a + b);

        return totalTries == THREE_TRIES ||
                totalTries == TWO_TRIES && !isCalculating();
    }

    private boolean isCalculating() {
        return states.stream()
                .anyMatch(state -> !state.isCalculated());
    }

    @Override
    public Score getScore() {
        if(!isOver()) {
            return UNSCORED;
        }

        int totalFallenPins = states.stream()
                .map(state -> state.calculateScore())
                .map(score -> score.getFallenPins())
                .reduce(MINIMUM_VALUE, (a,b) -> a + b);

        return new Score(totalFallenPins, NO_TRY);
    }

    @Override
    public Score addBonusScore(Score prevScore) {
        Score score = prevScore;

        for(State state : states) {
            if(score.hasNoTryLeft()) {
                return score;
            }
            score = state.addBonusScore(score);
        }

        if(!score.hasNoTryLeft()) {
            return UNSCORED;
        }

        return score;
    }

    @Override
    public Frame getNext() {
        throw new NoRemainingFrameException();
    }

    @Override
    public String display() {
        return states.stream()
                .map(state -> state.display())
                .reduce(NOT_THROWN, (a, b) -> a.concat(b));
    }
}
