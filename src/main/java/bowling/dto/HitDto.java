package bowling.dto;

import bowling.domain.state.HitState;

public class HitDto {

    private final int value;
    private final HitState state;

    public HitDto(int value, HitState state) {
        this.value = value;
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public HitState getState() {
        return state;
    }

}
