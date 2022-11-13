package bowling.dto;

import bowling.domain.BowlingGameHitState;

public class BowlingGameHitDto {

    private final int value;
    private final BowlingGameHitState state;

    public BowlingGameHitDto(int value, BowlingGameHitState state) {
        this.value = value;
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public BowlingGameHitState getState() {
        return state;
    }

}
