package bowling.domain.state;

import bowling.domain.score.Score;

public class None implements State {
    @Override
    public Score getScore() {
        return new Score();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String getSymbol() {
        return "";
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
