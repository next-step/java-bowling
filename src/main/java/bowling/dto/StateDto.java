package bowling.dto;

import bowling.state.State;

import java.util.List;

public class StateDto {
    private static final int FIRST_DOWNED_PINS_INDEX = 0;
    private static final int SECOND_DOWNED_PINS_INDEX = 1;

    private final List<Integer> scores;
    private final Class<? extends State> stateType;

    private StateDto(final State state) {
        this.scores = state.getScore();
        this.stateType = state.getClass();
    }

    public static StateDto from(final State state) {
        return new StateDto(state);
    }

    public int getFirstDownedPins() {
        return scores.get(FIRST_DOWNED_PINS_INDEX);
    }

    public int getSecondDownedPins() {
        return scores.get(SECOND_DOWNED_PINS_INDEX);
    }

    public Class<? extends State> getStateType() {
        return stateType;
    }
}
