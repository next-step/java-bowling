package bowling.domain;

import bowling.exception.CannotCalculateException;
import bowling.exception.FrameTryException;

public class FinalFrame extends Frame {

    private static final int MAX_PIN_COUNT = 30;
    private static final int MAX_TRY_COUNT = 3;
    private static final int NOT_BONUS_MAX_COUNT = 10;
    private static final int SECOND_TRY = 2;
    private static final String HIT_COUNT_EXCEPTION_MESSAGE = String.format("핀은 최대 %d개 까지 쓰러트릴 수 있습니다", MAX_PIN_COUNT);
    private static final String TRY_COUNT_EXCEPTION_MESSAGE = String.format("스트라이크 일 때 최대 %d번, 아닐 시 최대 %d번 까지 시도할 수 있습니다", MAX_TRY_COUNT, SECOND_TRY);
    private static final String CANNOT_CALCULATE_MESSAGE = "앞 투구가 끝나지 않아 계산 할 수 없습니다.";

    private FinalFrame() {
        super();
    }

    public static FinalFrame getFrame() {
        return new FinalFrame();
    }

    @Override
    public int getScore() {
        if (!roundEnded()) {
            throw new CannotCalculateException(CANNOT_CALCULATE_MESSAGE);
        }
        return score.calculateScore();
    }

    @Override
    public void createScore() {
        score = Score.ofNone(pins.totalCount());
    }

    @Override
    public boolean roundEnded() {
        if (pins.tryCount() == SECOND_TRY) {
            return hasNoBonusTry();
        }
        return pins.tryCount() >= MAX_TRY_COUNT;
    }

    @Override
    protected void validateTry() {
        if (roundEnded()) {
            throw new FrameTryException(TRY_COUNT_EXCEPTION_MESSAGE);
        }
    }

    @Override
    protected void validateHitCount(int hitCount) {
        if (pins.totalCount() + hitCount > MAX_PIN_COUNT) {
            throw new IllegalStateException(HIT_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private boolean hasNoBonusTry() {
        return pins.totalCount() < NOT_BONUS_MAX_COUNT;
    }
}
