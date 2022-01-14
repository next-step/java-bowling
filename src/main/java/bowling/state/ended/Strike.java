package bowling.state.ended;

import bowling.Pins;
import bowling.frame.Score;

public class Strike extends Ended {

    @Override
    public String symbol() {
        return "X";
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        if (beforeScore.isCalculatorScore()) {
            return beforeScore;
        }
        return beforeScore.bowl(Pins.MAX_PINS_COUNT);
    }
}
