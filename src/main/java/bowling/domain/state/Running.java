package bowling.domain.state;

import bowling.domain.Score;

abstract public class Running implements Status {

    public static final String CANNOT_CALCULATE_SCORE_ERROR_MESSAGE = "현재 상태에서는 점수를 계산할 수 없습니다.";

    @Override
    public Score score() {
        throw new IllegalStateException(CANNOT_CALCULATE_SCORE_ERROR_MESSAGE);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
