package bowling.domain;

public class FinalFrame extends Frame {

    private static final int MAX_PIN_COUNT = 30;
    private static final int SECOND_TRY = 2;
    private static final int NOT_BONUS_MAX_COUNT = 10;
    private static final int MAX_TRY_COUNT = 3;
    private static final String HIT_COUNT_EXCEPTION_MESSAGE = "핀은 최대 30개까지 쓰러트릴 수 있습니다";

    private FinalFrame(Round round) {
        super(round);
    }

    public static FinalFrame from(Round round) {
        return new FinalFrame(round);
    }

    protected void validateHitCount(int hitCount) {
        if (pins.totalCount() + hitCount > MAX_PIN_COUNT) {
            throw new IllegalStateException(HIT_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public boolean roundEnded() {
        if (pins.tryCount() == SECOND_TRY) {
            return !isStrike();
        }
        return pins.tryCount() >= MAX_TRY_COUNT;
    }

    protected boolean isStrike() {
        return pins.totalCount() >= NOT_BONUS_MAX_COUNT;
    }
}
