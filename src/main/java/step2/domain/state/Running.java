package step2.domain.state;

import step2.domain.Score;

public abstract class Running implements State {

    private static final int DEFAULT_SCORE = 0;
    private static final int DEFAULT_CHANCE = 2;

    public boolean isFinish() {
        return false;
    }

    public Score getScore() {
        return Score.of(DEFAULT_SCORE, DEFAULT_CHANCE);
    }

}
