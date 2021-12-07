package bowling.domain.state;

import bowling.domain.value.Score;

public abstract class InprogressState implements State {
    private static final String INVALID_SCORE = "아직 Frame이 완료되지 않아서 계산할 수 없습니다.";

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public Score calculateScore() {
        throw new IllegalArgumentException(INVALID_SCORE);
    }
}
