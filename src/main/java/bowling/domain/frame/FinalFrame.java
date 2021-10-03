package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.Indication;
import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import bowling.exception.GameEndException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.frame.FrameNumber.FINAL_NUMBER;

public class FinalFrame implements Frame {
    private final List<State> states = new ArrayList<>();

    public FinalFrame() {
        this.states.add(StateFactory.ready());
    }

    @Override
    public FinalFrame play(final PinCount pinCount) {
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

    private State getCurrentState() {
        return states.get(states.size() - 1);
    }

    @Override
    public FrameNumber getFrameNumber() {
        return FrameNumber.of(FINAL_NUMBER);
    }

    @Override
    public FrameResult makeResult() {
        return new FrameResult(makeIndication());
    }

    String makeIndication() {
        return states.stream()
                .map(State::showIndication)
                .collect(Collectors.joining(Indication.SEPARATOR.toString()));
    }
}
