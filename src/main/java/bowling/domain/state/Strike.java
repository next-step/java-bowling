package bowling.domain.state;

import bowling.domain.Score;

public class Strike implements State {

    private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public int getPitchCount() {
        return 1;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public int getTotalCount() {
        return 10;
    }

    @Override
    public State play(int fallenPin) {
        throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
