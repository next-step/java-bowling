package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreState;
import bowling.exception.CannotCalculateException;
import bowling.exception.FrameTryException;

public class NormalFrame extends Frame {

    private static final int MAX_PIN_COUNT = 10;
    private static final int MAX_TRY_COUNT = 2;
    private static final String HIT_COUNT_EXCEPTION_MESSAGE = String.format("핀은 최대 %d개 까지 쓰러트릴 수 있습니다", MAX_PIN_COUNT);
    private static final String TRY_COUNT_EXCEPTION_MESSAGE = String.format("최대 %d번 까지 시도할 수 있습니다", MAX_TRY_COUNT);
    private static final String CANNOT_CALCULATE_MESSAGE = "앞 투구가 끝나지 않아 계산 할 수 없습니다.";


    private NormalFrame() {
        super();
    }

    public static NormalFrame from() {
        return new NormalFrame();
    }

    @Override
    public int getScore() {
        if (!roundEnded()) {
            throw new CannotCalculateException(CANNOT_CALCULATE_MESSAGE);
        }
        return score.calculateScore();
    }

    @Override
    public boolean roundEnded() {
        return pins.tryCount() == MAX_TRY_COUNT || pins.isStrike();
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

    @Override
    public void createScore() {
        if (pins.isStrike() && isFirstTry()) {
            score = Score.of(MAX_PIN_COUNT, ScoreState.ofStrike());
            return;
        }
        if (pins.totalCount() == MAX_PIN_COUNT) {
            score = Score.of(MAX_PIN_COUNT, ScoreState.ofSpare());
            return;
        }
        score = Score.of(pins.totalCount(), ScoreState.ofNone());
    }
}
