package bowling.domain.state;

import bowling.domain.Symbol;
import bowling.domain.score.FinalScore;
import bowling.domain.score.Score;

/**
 * Created : 2020-12-22 오후 2:53
 * Developer : Seo
 */
public class FinalState implements State {
    public static final int STRIKE = 10;
    public static final int DOUBLE_STRIKE = 20;
    public static final int GUTTER = 0;
    public static final String DELIMITER = "|";

    private final State firstState;
    private final State secondState;

    private String symbol;
    private boolean finished = false;

    public FinalState(State firstState, State secondState) {
        this.firstState = firstState;
        this.secondState = secondState;
    }

    public FinalState(State firstState) {
        this.firstState = firstState;
        this.secondState = new None();
        this.symbol = firstState.getSymbol();
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
        this.symbol = firstScore + DELIMITER + secondScore;
    }

    public void setThirdSymbol() {
        this.finished = true;

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
        if (this.firstState.getSymbol().endsWith("X")) {
            return this.firstState.getSymbol();
        }
        return this.firstState.getSymbol() + DELIMITER;
    }

    public boolean isFirstStateStrike() {
        return this.firstState instanceof FinalState;
    }

    public boolean isSecondStateStrike() {
        return this.secondState.getScore().getFrameScore() == STRIKE;
    }

    public boolean isSecondStateSpare() {
        return this.firstState.getScore().getFrameScore() + this.secondState.getScore().getFrameScore() == STRIKE;
    }

    @Override
    public Score getScore() {
        int sum = firstState.getScore().getFrameScore() + secondState .getScore().getFrameScore();
        return new FinalScore(sum);
    }

    @Override
    public boolean isFinished() {
        return this.finished;
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
