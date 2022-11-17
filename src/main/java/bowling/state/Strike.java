package bowling.state;

import bowling.Pin;
import bowling.Score;

public class Strike extends Finished {

    @Override
    public Score score() {
        return Score.ofStrike();
    }

    @Override
    public Score calculatorAdditionalScore(Score beforeScore) {
        return beforeScore.addScore(Pin.MAX_PINS);
    }

    @Override
    public String getDesc() {
        return "X";
    }
}
