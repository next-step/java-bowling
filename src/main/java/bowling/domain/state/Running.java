package bowling.domain.state;

import bowling.domain.Score;

public abstract class Running implements State {

    private static final String CAN_NOT_SCORE_MESSAGE = "[ERROR] Frame이 종료되기 전에는 점수를 구할 수 없습니다.";

    @Override
    public Score score() {
        throw new IllegalArgumentException(CAN_NOT_SCORE_MESSAGE);
    }

    @Override
    public boolean finish() {
        return false;
    }
}
