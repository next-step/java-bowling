package bowling.domain.frame.state;

import bowling.exception.BowlingException;

import java.util.LinkedList;
import java.util.List;

public class FinalFrameStates {

    private static final int FINAL_STATE_SIZE = 2;
    private static final String FINAL_STATE_SIZE_ERR_MESSAGE = "마지막 프레임은 최대 3번 투구 가능";

    private final LinkedList<State> states;

    public FinalFrameStates(final LinkedList<State> states) {
        validateStateSize(states);
        this.states = states;
    }

    public static FinalFrameStates of() {
        LinkedList<State> states = new LinkedList<>();
        states.add(new Ready());
        return new FinalFrameStates(states);
    }

    private void validateStateSize(final List<State> states) {
        if (states.size() > FINAL_STATE_SIZE) {
            throw new BowlingException(FINAL_STATE_SIZE_ERR_MESSAGE);
        }
    }

    public FinalFrameStates addState(final State state) {
        LinkedList<State> merge = new LinkedList<>(states);
        merge.add(state);
        return new FinalFrameStates(merge);
    }

    public boolean isFinish() {
        if (isStrikeOrSpare()) {
            return false;
        }

        if (states.getLast() instanceof Miss || states.getLast() instanceof Gutter) {
            return true;
        }

        return true;
    }

    private boolean isStrikeOrSpare() {
        if (states.size() != 1) {
            return false;
        }

        if (states.getFirst() instanceof Strike || states.getFirst() instanceof Spare) {
            return true;
        }

        return false;
    }
}
