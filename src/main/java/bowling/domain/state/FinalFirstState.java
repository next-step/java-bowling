package bowling.domain.state;

import bowling.domain.score.Score;

/**
 * Created : 2020-12-22 오후 2:53
 * Developer : Seo
 */
public class FinalFirstState implements State {
    private final State state;

    public FinalFirstState(State state) {
        this.state = state;
    }

    @Override
    public Score getScore() {
        return this.state.getScore();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String getSymbol() {
        return this.state.getSymbol();
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
