package bowling.domain.state;

import bowling.domain.Symbol;
import bowling.domain.score.FinalScore;
import bowling.domain.score.Score;

public class FinalThirdState implements State {
    public static final String DELIMITER = "|";
    public static final String BLANK = "";

    private final State secondState;
    private final State thirdState;

    public FinalThirdState(State secondState, State thirdState) {
        this.secondState = secondState;
        this.thirdState = thirdState;
    }

    @Override
    public Score getScore() {
        return this.thirdState.getScore();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String getSymbol() {
        String thirdSymbol = BLANK;
        if (this.thirdState instanceof Strike) {
            thirdSymbol = Symbol.STRIKE.getSymbol();
        }
        if (this.thirdState instanceof Spare) {
            thirdSymbol = Symbol.SPARE.getSymbol();
        }
        if (this.thirdState instanceof Miss) {
            thirdSymbol = String.valueOf(thirdState.getScore().getFrameScore());
        }
        if (this.thirdState instanceof Gutter) {
            thirdSymbol = Symbol.GUTTER.getSymbol();
        }
        return this.secondState.getSymbol() + DELIMITER + thirdSymbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
