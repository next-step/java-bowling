package bowling.domain.state;

import bowling.domain.Symbol;
import bowling.domain.score.FinalScore;
import bowling.domain.score.Score;

public class FinalSecondState implements State {
    public static final String DELIMITER = "|";
    public static final String BLANK = "";

    private final State firstState;
    private final State secondState;

    public FinalSecondState(State firstState, State secondState) {
        this.firstState = firstState;
        this.secondState = secondState;
    }

    @Override
    public Score getScore() {
        return this.secondState.getScore();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String getSymbol() {
        String secondSymbol = BLANK;
        if (this.secondState instanceof Strike) {
            secondSymbol = Symbol.STRIKE.getSymbol();
        }
        if (this.secondState instanceof Spare) {
            secondSymbol = Symbol.SPARE.getSymbol();
        }
        if (this.secondState instanceof Miss) {
            secondSymbol = String.valueOf(secondState.getScore().getFrameScore());
        }
        if (this.secondState instanceof Gutter) {
            secondSymbol = Symbol.GUTTER.getSymbol();
        }
        return this.firstState.getSymbol() + DELIMITER + secondSymbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}

