package bowling.domain.frame.state;

import bowling.exception.BowlingException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FinalFrameStates {

    private static final int FINAL_STATE_SIZE = 2;
    private static final String FINAL_STATE_SIZE_ERR_MESSAGE = "마지막 프레임은 최대 3번 투구 가능";

    private final LinkedList<State> states;

    public FinalFrameStates(final LinkedList<State> states) {
        validateStateSize(states);
        this.states = new LinkedList<>(states);
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
        if (states.getFirst() instanceof Ready) {
            return false;
        }

        if (isNormalTurnStrikeOrSpareOrFirstBowl()) {
            return false;
        }

        if (isNormalTurnMissOrGutter()) {
            return true;
        }

        if (states.size() == FINAL_STATE_SIZE) {
            return false;
        }

        return true;
    }

    private boolean isNormalTurnStrikeOrSpare() {
        if (states.size() != 1) {
            return false;
        }

        if (states.getFirst() instanceof Strike
                || states.getFirst() instanceof Spare) {
            return true;
        }

        return false;
    }

    private boolean isNormalTurnStrikeOrSpareOrFirstBowl() {
        if (states.size() != 1) {
            return false;
        }

        if (isNormalTurnStrikeOrSpare() || states.getFirst() instanceof FirstBowl) {
            return true;
        }

        return false;
    }

    private boolean isNormalTurnMissOrGutter() {
        if (states.getLast() instanceof Miss || states.getLast() instanceof Gutter) {
            return true;
        }

        return false;
    }

    public FinalFrameStates bowl(final int pinCount) {
        if (isFinish()) {
            throw new BowlingException(State.CANT_THROW_BALL);
        }

        if (isNormalTurnStrikeOrSpare()) {
            State third = new Ready();
            third = third.bowl(pinCount);
            return addState(third);
        }

        State bowl = states.getFirst().bowl(pinCount);
        LinkedList<State> states = new LinkedList<>(Arrays.asList(bowl));
        return new FinalFrameStates(states);
    }

//    @Override
//    public String getCurrentPinsState() {
//        for (State state : states) {
//            state.
//            return String.format(PINS_STATE, firstPins);
//        }
//    }
}
