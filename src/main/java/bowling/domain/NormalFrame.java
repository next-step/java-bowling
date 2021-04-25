package bowling.domain;

public class NormalFrame extends Frame {

    private static final int MAX_PIN_COUNT = 10;
    private static final int MAX_TRY_COUNT = 2;
    private static final String HIT_COUNT_EXCEPTION_MESSAGE = "핀은 최대 10개까지 쓰러트릴 수 있습니다";

    private NormalFrame(Round round) {
        super(round);
    }

    public static NormalFrame from(Round round) {
        return new NormalFrame(round);
    }

    public boolean roundEnded() {
        return pins.tryCount() == MAX_TRY_COUNT || isStrike();
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
