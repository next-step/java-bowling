package bowling.entity.score;

import bowling.entity.Pin;
import bowling.entity.Score;

import java.util.Objects;

import static bowling.entity.Pin.MAX_PIN_COUNT;

public class Strike extends Finish {
    private static final String STRIKE_SYMBOL = "X";
    private static final int STRIKE_SCORE_REMAIN = 2;

    private final int remain;

    public Strike() {
        this.remain = STRIKE_SCORE_REMAIN;
    }

    public Strike(int remain) {
        this.remain = remain;
    }

    @Override
    public String scoreResult() {
        return STRIKE_SYMBOL;
    }

    @Override
    public Score score() {
        return new Score(MAX_PIN_COUNT, remain);
    }

    @Override
    public Score calculate(Score beforeScore) {

        if (beforeScore.calculatePossible()) {
            return beforeScore;
        }

        return beforeScore.calculate(MAX_PIN_COUNT);
    }

    @Override
    public Score frameScore() {
        return new Score(MAX_PIN_COUNT, remain);
    }
}
