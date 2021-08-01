package bowling.dto;

import bowling.domain.state.State;

public class StateDto {
    private static final int INDEX_OF_FIRST_DOWNED_PINS = 0;
    private static final int INDEX_OF_SECOND_DOWNED_PINS = 1;

    private final State state;

    public StateDto(State state) {
        this.state = state;
    }

    public static StateDto from(State state) {
        return new StateDto(state);
    }

    public int getFirstDownedPins() {
        return state.getDownedPins()
                .get(INDEX_OF_FIRST_DOWNED_PINS);
    }

    public int getSecondDownedPins() {
        return state.getDownedPins()
                .get(INDEX_OF_SECOND_DOWNED_PINS);
    }
}
