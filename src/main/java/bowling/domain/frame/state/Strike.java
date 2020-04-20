package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Calculator;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCalculator;
import bowling.exception.BowlingException;

public class Strike implements State {

    private static final String CANT_THROW_BALL = "더이상 투구할 수 없습니다";
    private static final String PINS_STATE = "  X   ";

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException(CANT_THROW_BALL);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String getCurrentPinsState() {
        return PINS_STATE;
    }

    @Override
    public Score getCurrentScore() {
        return new Score(Pins.MAX_PIN, 2);
    }

    @Override
    public Calculator getCurrentCalculator() {
        return new ScoreCalculator(new Score(Pins.MAX_PIN), 2);
    }

    @Override
    public Score getCalculateScore(Score before) {
        return before.addScore(new Score(Pins.MAX_PIN));
    }

    @Override
    public Calculator getScoreCalculate(Calculator before) {
        return before.sumScore(new Score(Pins.MAX_PIN));
    }
}
