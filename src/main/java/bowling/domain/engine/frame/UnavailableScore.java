package bowling.domain.engine.frame;

import bowling.domain.RollResult;

public final class UnavailableScore extends Score {

    protected UnavailableScore(int value, int injectionCount) {
        super(value, injectionCount);
    }

    public static UnavailableScore init() {
        return new UnavailableScore(-1, -1);
    }

    public static UnavailableScore of(int value) {
        return new UnavailableScore(value, -1);
    }

    @Override
    public Score add(RollResult rollResult) {
        throw new IllegalStateException("점수 계산이 허용되지 않습니다.");
    }

    @Override
    public boolean isCalculationCompleted() {
        return true;
    }

}
