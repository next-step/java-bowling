package bowling.dto;

import bowling.domain.BowlingGameHitResult;

public class BowlingGameHitDto {

    private final int value;
    private final BowlingGameHitResult result;

    public BowlingGameHitDto(int value, BowlingGameHitResult result) {
        this.value = value;
        this.result = result;
    }

    public int getValue() {
        return value;
    }

    public BowlingGameHitResult getResult() {
        return result;
    }

}
