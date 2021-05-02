package bowling.domain.score;

import bowling.domain.score.symbol.ScoreSymbol;

public class Score {
    private static final int SPARE_CONDITION_VALUE = 10;

    private int score;
    private ScoreSymbol symbol;

    public Score(int score, ScoreSymbol symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public boolean checkIfSpare(int nextScore) {
        return (this.score + nextScore) == SPARE_CONDITION_VALUE;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
