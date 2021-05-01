package bowling.domain.engine.frame;

import bowling.domain.RollResult;

public class Score {

    private static final int MAX_PINS = 10;
    private static final int STRIKE_INJECTION_COUNT = 2;
    private static final int SPARE_INJECTION_COUNT = 1;
    private static final int NO_INJECTION_COUNT = 0;

    private final int value;
    private final int completionCount;

    protected Score(int value, int completionCount) {
        this.value = value;
        this.completionCount = completionCount;
    }

    public static Score initStrikeScore() {
        return new Score(MAX_PINS, STRIKE_INJECTION_COUNT);
    }

    public static Score initSpareScore() {
        return new Score(MAX_PINS, SPARE_INJECTION_COUNT);
    }

    public static Score initReadyToUseScore(int numberOfPins) {
        return new Score(numberOfPins, NO_INJECTION_COUNT);
    }

    public Score add(RollResult rollResult) {
        if (isCalculationCompleted()) {
            throw new IllegalStateException("이미 점수 계산이 완료된 상태입니다.");
        }
        return new Score(this.value + rollResult.getValue(), this.completionCount - 1);
    }

    public int getValue() {
        return value;
    }

    public boolean isCalculationCompleted() {
        return completionCount == 0;
    }

    public boolean isUnavailable() {
        return completionCount == -1;
    }

}
