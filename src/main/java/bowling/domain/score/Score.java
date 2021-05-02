package bowling.domain.score;

import bowling.domain.score.symbol.ScoreSymbol;

public class Score {
    private int score;
    private ScoreSymbol symbol;

    public Score(int score, ScoreSymbol symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public boolean checkIfSpare(int nextScore) {
        return (this.score + nextScore) == 10;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
