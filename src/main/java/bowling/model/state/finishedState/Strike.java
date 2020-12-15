package bowling.model.state.finishedState;

import bowling.model.Score;

public class Strike extends FinishedState {
    private static final String STRIKE_TOTAL_SCORE_ERROR = "Strike의 조건에 맞지 않습니다.";
    private static final String EXPRESSION = "X";

    private Strike(Score score) {
        super(score);
        this.expression = EXPRESSION;
    }

    public static Strike from(Score score) {
        if (!score.isMaxScore()) {
            throw new IllegalArgumentException(STRIKE_TOTAL_SCORE_ERROR);
        }
        return new Strike(score);
    }
}
