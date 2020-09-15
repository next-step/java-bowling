package bowling.domain;

import java.util.Arrays;

public enum Status {
    STRIKE(true, 1),
    SPARE(true, 2),
    MISS(false, 2),
    ING(false, 1);

    private final boolean allFallenPins;
    private final int tryCount;

    Status(boolean allFallenPins, int tryCount) {
        this.allFallenPins = allFallenPins;
        this.tryCount = tryCount;
    }

    public static Status of(boolean allFallenPins, int tryCount) {
        return Arrays.stream(values())
                .filter(status -> status.allFallenPins == allFallenPins && status.tryCount == tryCount)
                .findFirst()
                .orElse(ING);
    }
}
