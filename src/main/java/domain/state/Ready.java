package domain.state;

import domain.score.Score;

import static io.OutputResult.EMPTY_SPACE;

public class Ready implements State {

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.EMPTY;
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        return Score.EMPTY;
    }

    @Override
    public String toSymbol() {
        return EMPTY_SPACE;
    }
}
