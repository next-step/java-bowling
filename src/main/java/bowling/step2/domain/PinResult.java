package bowling.step2.domain;

import java.util.Arrays;

public enum PinResult {
    STRIKE(10), SPARE(10), MISS(10), GUTTER(0);

    private int count;

    PinResult(int count) {
        this.count = count;
    }

    public PinResult findResultOf(PinResult preResult, int count) {


        return Arrays.stream(values())
                .filter(pinResult -> pinResult.count == count)
                .findFirst()
                .orElse(PinResult.MISS);
    }
}
