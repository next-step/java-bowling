package bowling.domain.state;

import bowling.domain.score.Score;

public abstract class State {
    public static int MIN_LEFT_TRY = 0;
    protected int leftTry;

    protected State(int leftTry) {
        this.leftTry = leftTry;
    }

    public abstract State record(int pins);

    public abstract Score getScore();

    public boolean isFinished() {
        return leftTry == MIN_LEFT_TRY;
    }
}
