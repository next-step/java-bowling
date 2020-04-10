package bowling.domain;

import java.util.Arrays;

public enum FrameResult {
    STRIKE(true, true, true, false),
    MISS;

    private boolean isFirstTen;
    private boolean isSecondZero;
    private boolean isSumTen;
    private boolean isAllZero;

    FrameResult() {
    }

    FrameResult(boolean isFirstTen, boolean isSecondZero, boolean isSumTen, boolean isAllZero) {
        this.isFirstTen = isFirstTen;
        this.isSecondZero = isSecondZero;
        this.isSumTen = isSumTen;
        this.isAllZero = isAllZero;
    }

    public static FrameResult findResult(int firstPoint, int secondPoint) {
        return Arrays.stream(values())
                .filter(value -> value.isFirstTen == isFirstTen(firstPoint))
                .filter(value -> value.isSecondZero == isSecondZero(secondPoint))
                .findFirst()
                .orElse(MISS);
    }

    private static boolean isFirstTen(int firstPoint) {
        return firstPoint == 10;
    }

    private static boolean isSecondZero(int secondPoint) {
        return secondPoint == 0;
    }
}