package bowling.domain;

import bowling.exception.FrameTryException;

public class NormalFrame extends Frame {

    private static final int MAX_PIN_COUNT = 10;
    private static final int MAX_TRY_COUNT = 2;
    private static final String HIT_COUNT_EXCEPTION_MESSAGE = String.format("핀은 최대 %d개 까지 쓰러트릴 수 있습니다", MAX_PIN_COUNT);
    private static final String TRY_COUNT_EXCEPTION_MESSAGE = String.format("최대 %d번 까지 시도할 수 있습니다", MAX_TRY_COUNT);

    private NormalFrame(Round round) {
        super(round);
    }

    public static NormalFrame from(Round round) {
        return new NormalFrame(round);
    }

    public boolean roundEnded() {
        return pins.tryCount() == MAX_TRY_COUNT || isStrike();
    }

    protected void validateTry() {
        if (roundEnded()) {
            throw new FrameTryException(TRY_COUNT_EXCEPTION_MESSAGE);
        }
    }

    protected void validateHitCount(int hitCount) {
        if (pins.totalCount() + hitCount > MAX_PIN_COUNT) {
            throw new IllegalStateException(HIT_COUNT_EXCEPTION_MESSAGE);
        }
    }

    protected boolean isStrike() {
        return pins.totalCount() == MAX_PIN_COUNT;
    }
}
