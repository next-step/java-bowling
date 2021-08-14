package bowling.domain.frame;

import bowling.domain.dto.StateData;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.Start;

import java.util.List;
import java.util.Stack;

public class LastFrame extends Frame {

    private static final int START_IDX_OF_STATES = 0;
    private static final int START_IDX_OF_SUB_STATES = 1;
    private static final int LIMIT_ATTEMPT_COUNT = 3;

    private final Stack<State> states;
    private int attemptCount;

    private LastFrame(State state) {
        states = new Stack<>();
        states.add(state);
    }

    public static LastFrame of() {
        return new LastFrame(Start.of());
    }

    @Override
    public Score getScore() {
        return subStates().stream()
                .reduce(firstState().score(),
                        (interScore, state) -> state.addScore(interScore),
                        (x, y) -> {
                            throw new IllegalStateException();
                        });
    }

    @Override
    protected Score addStateScore(Score score) {
        return states.stream()
                .reduce(score,
                        (interScore, state) -> state.addScore(interScore),
                        (x, y) -> {
                            throw new IllegalStateException();
                        });
    }

    @Override
    public boolean isBowlingFinish() {
        return attemptCount == LIMIT_ATTEMPT_COUNT || lastState().isMiss();
    }

    public void hitPins(Pins pins) {
        attemptCount += 1;
        changeLastState(lastState().hitPins(pins));

        if (!isBowlingFinish()) {
            addExtraChance();
        }
    }

    public boolean isNotAllHit() {
        return !lastState().isAllHit();
    }

    public void addExtraChance() {
        if (isNotAllHit()) {
            return;
        }

        states.add(Start.of());
    }

    public StateData getFrameStates() {
        return StateData.of(states);
    }

    private void changeLastState(State state) {
        states.pop();
        states.add(state);
    }

    private State lastState() {
        return states.peek();
    }

    private State firstState() {
        return states.get(START_IDX_OF_STATES);
    }

    private List<State> subStates() {
        return states.subList(START_IDX_OF_SUB_STATES, states.size());
    }

}
