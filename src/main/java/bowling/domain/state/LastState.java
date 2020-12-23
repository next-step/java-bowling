package bowling.domain.state;

import bowling.domain.Symbol;
import bowling.domain.score.LastScore;
import bowling.domain.score.Score;

/**
 * Created : 2020-12-22 오후 2:53
 * Developer : Seo
 */
public class LastState implements State {
    public static final int STRIKE = 10;
    public static final int DOUBLE_STRIKE = 20;
    public static final int SECOND_GUTTER = 0;
    public static final int SECOND_SPARE = 20;

    private State firstState;
    private State secondState;
    private String symbol;
    private boolean isThird = false;

    public LastState(State firstState, State secondState) {
        this.firstState = firstState;
        this.secondState = secondState;
    }

    public void setSecondSymbol() {
        int firstScore = firstState.getScore().getFrameScore();
        int secondScore = secondState.getScore().getFrameScore();

        String firstSymbol = firstState.getSymbol();
        String secondSymbol = String.valueOf(secondScore);
        if (secondScore == DOUBLE_STRIKE) {
            secondSymbol = Symbol.STRIKE.getSymbol();
        }
        if (secondScore == SECOND_GUTTER) {
            secondSymbol = Symbol.GUTTER.getSymbol();
        }
        if (firstScore + secondScore == SECOND_SPARE) {
            secondSymbol = Symbol.SPARE.getSymbol();
        }

        this.symbol = firstSymbol + secondSymbol;
    }

    public void setThirdSymbol() {
        this.isThird = true;
        int secondScore = secondState.getScore().getFrameScore();

        String firstSymbol = firstState.getSymbol();
        String secondSymbol = String.valueOf(secondScore);
        if (secondScore == STRIKE) {
            secondSymbol = Symbol.STRIKE.getSymbol();
        }
        if (secondScore == SECOND_GUTTER) {
            secondSymbol = Symbol.GUTTER.getSymbol();
        }

        this.symbol = firstSymbol + secondSymbol;
    }

    @Override
    public Score getScore() {
        int sum = firstState.getScore().getFrameScore() + secondState.getScore().getFrameScore();
        return new LastScore(sum);
    }

    @Override
    public boolean isFinished() {
        return isThird;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
