package bowling.domain.engine.frame;

import bowling.domain.RollResult;

public class Score {

    private static final int MAX_PINS = 10;
    private static final int STRIKE_INJECTION_COUNT = 2;
    private static final int SPARE_INJECTION_COUNT = 1;
    private static final int NO_INJECTION_COUNT = 0;

    private final int value;
    private final int injectionCount;

    private Score(int value, int injectionCount) {
        this.value = value;
        this.injectionCount = injectionCount;
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

    public Score inject(RollResult rollResult) {
        if (isCalculationCompleted()) {
            throw new IllegalStateException("이미 점수 계산이 완료된 상태입니다.");
        }
        return new Score(this.value + rollResult.getValue(), this.injectionCount - 1);
    }

    public int getValue() {
        if (!isCalculationCompleted()) {
            throw new IllegalStateException("아직 점수 계산이 완료되지 않았습니다.");
        }
        return value;
    }

    public boolean isCalculationCompleted() {
        return injectionCount == 0;
    }

}
