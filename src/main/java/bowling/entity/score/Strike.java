package bowling.entity.score;

import bowling.entity.Pin;
import bowling.entity.Score;

import java.util.Objects;

import static bowling.entity.Pin.MAX_PIN_COUNT;

public class Strike extends Finish {
    private static final String STRIKE_SYMBOL = "X";
    private static final int STRIKE_SCORE_REMAIN = 2;

    @Override
    public String scoreResult() {
        return STRIKE_SYMBOL;
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        if (fallenPin.pin() == MAX_PIN_COUNT) {
            return new Strike();
        }
        return new NormalScore(fallenPin);
    }

    @Override
    public Score score() {
        return new Score(MAX_PIN_COUNT, STRIKE_SCORE_REMAIN);
    }

    @Override
    public Score calculate(Score beforeScore) {

        if (beforeScore.calculatePossible()) {
            return beforeScore;
        }

        return beforeScore.calculate(MAX_PIN_COUNT);
    }
}
