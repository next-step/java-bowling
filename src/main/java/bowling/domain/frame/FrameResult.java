package bowling.domain.frame;

import java.util.Arrays;

public enum FrameResult {
    STRIKE(true, true, true, false),
    SPARE(false, true, false),
    GUTTER(false, true, false, true),
    MISS;

    private boolean isFirstTen;
    private boolean isSecondZero;
    private boolean isSumTen;
    private boolean isAllZero;

    FrameResult() {
    }

    FrameResult(boolean isFirstTen, boolean isSumTen, boolean isAllZero) {
        this.isFirstTen = isFirstTen;
        this.isSumTen = isSumTen;
        this.isAllZero = isAllZero;
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
                .filter(value -> value.isSumTen == isSumTen(firstPoint, secondPoint))
                .filter(value -> value.isAllZero == isAllZero(firstPoint, secondPoint))
                .findFirst()
                .orElse(MISS);
    }

    private static boolean isFirstTen(int firstPoint) {
        return firstPoint == 10;
    }

    private static boolean isSecondZero(int secondPoint) {
        return secondPoint == 0;
    }

    private static boolean isSumTen(int firstPoint, int secondPoint) {
        return (firstPoint + secondPoint) == 10;
    }

    private static boolean isAllZero(int firstPoint, int secondPoint) {
        return (firstPoint == 0 && secondPoint == 0);
    }
}