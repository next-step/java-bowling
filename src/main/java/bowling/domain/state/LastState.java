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
    public static final int GUTTER = 0;
    public static final String DELIMITER = "|";

    private final State firstState;
    private final State secondState;
    private String symbol;
    private boolean isThird = false;

    public LastState(State firstState, State secondState) {
        this.firstState = firstState;
        this.secondState = secondState;
    }

    public void setSecondSymbol() {
        int firstScore = firstState.getScore().getFrameScore();
        int secondScore = secondState.getScore().getFrameScore();

        // XX
        if (firstScore + secondScore == DOUBLE_STRIKE) {
            this.symbol = Symbol.STRIKE.getSymbol() + Symbol.STRIKE.getSymbol();
            return;
        }
        // X|*
        if (firstScore == STRIKE && secondScore < STRIKE && secondScore != GUTTER) {
            this.symbol = Symbol.STRIKE.getSymbol() +  DELIMITER + secondScore;
            return;
        }
        // *|X
        if (firstScore < STRIKE && secondScore == STRIKE && firstScore != GUTTER) {
            this.symbol = firstScore +  DELIMITER + Symbol.STRIKE.getSymbol();
            return;
        }
        // X|-
        if (firstScore == STRIKE && secondScore == GUTTER) {
            this.symbol = Symbol.STRIKE.getSymbol() +  DELIMITER + Symbol.GUTTER.getSymbol();
            return;
        }
        // -|X
        if (firstScore == GUTTER && secondScore == STRIKE) {
            this.symbol = Symbol.GUTTER.getSymbol() +  DELIMITER + Symbol.STRIKE.getSymbol();
            return;
        }
        // *|-
        if (firstScore > GUTTER && secondScore == GUTTER) {
            this.symbol = firstScore +  DELIMITER + Symbol.GUTTER.getSymbol();
            return;
        }
        // -|*
        if (firstScore == GUTTER && secondScore > GUTTER) {
            this.symbol = Symbol.GUTTER.getSymbol() +  DELIMITER + secondScore;
            return;
        }
        // *|/
        if (firstScore + secondScore == STRIKE) {
            this.symbol = firstScore + DELIMITER + Symbol.SPARE.getSymbol();
            return;
        }
        // *|*
        this.symbol = secondScore + DELIMITER + secondScore;
    }

    public void setThirdSymbol() {
        this.isThird = true;
        int secondScore = this.secondState.getScore().getFrameScore();

        String secondSymbol = String.valueOf(secondScore);
        if (secondScore == STRIKE) {
            this.symbol = getFormattedSymbol() + Symbol.STRIKE.getSymbol();
            return;
        }
        if (secondScore == GUTTER) {
            this.symbol = getFormattedSymbol() + Symbol.GUTTER.getSymbol();
            return;
        }

        this.symbol = this.firstState.getSymbol() + DELIMITER + secondSymbol;
    }

    private String getFormattedSymbol() {
        System.out.println(this.firstState);
        if (this.firstState.getSymbol().endsWith("X")) {
            return this.firstState.getSymbol();
        }
        return this.firstState.getSymbol() + DELIMITER;
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
