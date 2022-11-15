package bowling.dto;

import bowling.domain.state.FrameState;

public class HitDto {

    private final int value;
    private final FrameState state;

    public HitDto(int value, FrameState state) {
        this.value = value;
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public FrameState getState() {
        return state;
    }

}
