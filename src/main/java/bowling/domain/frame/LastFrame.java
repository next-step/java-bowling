package bowling.domain.frame;

import bowling.domain.dto.StateData;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.ProgressState;
import bowling.domain.state.ResultState;
import bowling.domain.state.State;
import bowling.domain.state.Start;

import java.util.List;
import java.util.Stack;

public class LastFrame extends Frame {

    private static final int STATES_START_INDEX = 0;
    private static final int SCORE_STATES_START_INDEX = 1;
    private static final int LIMIT_ATTEMPT_COUNT = 3;

    private final Stack<State> states;
    private int attemptCount;

    protected LastFrame(State state) {
        states = new Stack<>();
        states.add(state);
    }

    public static LastFrame of() {
        return new LastFrame(Start.of());
    }

    @Override
    public Score getScore() {
        return scoreStates().stream()
                .reduce(getStartIndex().score(),
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
        return isLimitAttemptCount() || isLastStateMiss();
    }

    public void hitPins(Pins pins) {
        attemptCount += 1;
        changeLastState(lastState().hitPins(pins));
        addExtraChance();
    }

    public StateData getFrameStates() {
        return StateData.of(states);
    }

    @Override
    public boolean isStart() {
        if (isBowlingFinish()) {
            return true;
        }

        return getStartIndex().getProgressState() == ProgressState.START;
    }

    protected boolean isLimitAttemptCount() {
        return attemptCount == LIMIT_ATTEMPT_COUNT;
    }

    protected boolean isLastStateMiss() {
        return lastState().getResultState() == ResultState.MISS;
    }

    private void changeLastState(State state) {
        states.pop();
        states.add(state);
    }

    private void addExtraChance() {
        if (isBowlingFinish()) {
            return;
        }

        if (isNotAllHit()) {
            return;
        }

        states.add(Start.of());
    }

    private State lastState() {
        return states.peek();
    }

    private List<State> scoreStates() {
        return states.subList(SCORE_STATES_START_INDEX, states.size());
    }

    private boolean isNotAllHit() {
        return lastState().getResultState() != ResultState.ALLHIT;
    }

    private State getStartIndex() {
        return states.get(STATES_START_INDEX);
    }

}
