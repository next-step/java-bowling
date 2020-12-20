package bowling.model.state.finished;

import bowling.model.Pins;
import bowling.model.Score;

public class Strike extends FinishedState {
    private static final String STRIKE_TOTAL_SCORE_ERROR = "Strike의 조건에 맞지 않습니다.";
    private static final String EXPRESSION = "X";

    private Strike(Pins pins) {
        super(pins);
        this.expression = EXPRESSION;
    }

    @Override
    public Score calculateScore(Score score) {
        return score.add(pins.getScore());
    }

    @Override
    public Score score() {
        return Score.of(10, 2);
    }

    public static Strike from(Pins pins) {
        if (!pins.isMaxScore()) {
            throw new IllegalArgumentException(STRIKE_TOTAL_SCORE_ERROR);
        }
        return new Strike(pins);
    }
}
