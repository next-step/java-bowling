package bowling.domain.state;

import bowling.domain.Point;
import bowling.exception.IllegalBowlCountException;
import bowling.exception.OutOfBowlCountException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 17:01
 */
public class FinalState implements State {
    public static final String DELIMITER = "|";
    private static final int EXCLUDE_LAST_INDEX = 1;
    public static final int MAX_BOWL_COUNT = 3;
    private List<State> state;

    private State states;
    private Point thirdBowl;

    public FinalState() {
        this.state = new ArrayList<>(Arrays.asList(InitState.of()));
    }

    public State update(Point fallCount, boolean isFinalFrame) {
        State tempState = state.get(lastIndex());
        if (tempState.isOver()) {
            tempState = InitState.of();
        }
        state.add(tempState.update(fallCount, isFinalFrame));

        return this;
    }

    @Override
    public boolean isOver() {
        if (states instanceof Strike) {

        }
        return Boolean.TRUE;
    }

    @Override
    public Point getFirstBowl() {
        return states.getFirstBowl();
    }

    @Override
    public Point getSecondBowl() {
        return states.getSecondBowl();
    }

    private boolean isFinalGmaeOver() {
        if(state.size() > MAX_BOWL_COUNT
                || lastOfStateState() instanceof Miss
                || lastOfStateState() instanceof DoubleGutter) {
            return true;
        }
        return false;
    }

    public String printState() {
        StringBuilder sb = new StringBuilder();
        sb.append(state.stream()
                .filter(source -> !isUnFinishState(source) && !(source instanceof InitState))
                .map(State::printState)
                .collect(Collectors.joining(DELIMITER)));

        if (isUnFinishState(lastOfStateState())) {
            sb.append(DELIMITER);
            sb.append(lastOfStateState().printState());
        }

        return sb.toString();
    }

    public State lastOfStateState() {
        return state.get(lastIndex());
    }

    private boolean isUnFinishState(State chekeState) {
        if (chekeState instanceof Gutter
            || chekeState instanceof Hit) {
            return true;
        }
        return false;
    }

    private int lastIndex() {
        int lastIndex = state.size();
        return lastIndex - EXCLUDE_LAST_INDEX;
    }
}
