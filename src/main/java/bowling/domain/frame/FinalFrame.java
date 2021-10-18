package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import bowling.exception.GameEndException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends AbstractFrame {
    private static final String SEPARATOR = "|";
    private final List<State> states = new ArrayList<>();

    public FinalFrame(final FrameNumber frameNumber) {
        super(frameNumber);
        this.states.add(StateFactory.ready());
    }

    @Override
    public Frame play(final PinCount pinCount) {
        if (isFinished()) {
            throw new GameEndException();
        }

        State state = getCurrentState();
        if (state.isFinished()) {
            states.add(StateFactory.play(pinCount));
            return this;
        }

        states.remove(state);
        states.add(state.play(pinCount));
        return this;
    }

    public boolean isFinished() {
        State firstState = states.get(0);
        if (!firstState.isFinished()) {
            return false;
        }
        Score score = firstState.getScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateBonusScore(score);
        }
        return score.canCalculate();
    }

    @Override
    public Score calculateScore() {
        State firstState = states.get(0);
        Score score = firstState.getScore();
        if (!firstState.isFinished()) {
            return score;
        }
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateBonusScore(score);
        }
        return score;
    }

    @Override
    public Score plusBonusScore(final Score beforeScore) {
        State firstState = states.get(0);
        Score score = firstState.calculateBonusScore(beforeScore);

        if (score.canCalculate() || !hasNextState(firstState)) {
            return score;
        }
        State secondState = states.get(1);
        return secondState.calculateBonusScore(score);
    }

    private boolean hasNextState(final State currentState) {
        return states.size() > states.indexOf(currentState) + 1;
    }

    private State getCurrentState() {
        return states.get(states.size() - 1);
    }

    @Override
    public FrameResult makeResult() {
        return new FrameResult(makeIndication(), calculateScore());
    }

    String makeIndication() {
        return states.stream()
                .map(State::showIndication)
                .collect(Collectors.joining(SEPARATOR));
    }
}
