package bowling.domain.state;

import bowling.domain.score.Score;

public class None implements State {
    public static final String BLANK = "";

    @Override
    public Score getScore() {
        return new Score();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String getSymbol() {
        return BLANK;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
