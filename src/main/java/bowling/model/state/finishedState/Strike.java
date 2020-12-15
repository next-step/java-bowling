package bowling.model.state.finishedState;

import bowling.model.Score;

public class Strike extends FinishedState {
    private static final String STRIKE_TOTAL_SCORE_ERROR = "Strike의 조건에 맞지 않습니다.";
    private static final String EXPRESSION = "X";

    private Strike(Score previous, Score score) {
        super(previous, score);
        this.expression = EXPRESSION;
    }

    public static Strike of(Score previous, Score totalScore) {
        if (previous.isMinScore() && totalScore.isMaxScore()) {
            return new Strike(previous, totalScore);
        }

        throw new IllegalArgumentException(STRIKE_TOTAL_SCORE_ERROR);
    }

    public static Strike from(Score totalScore) {
        return new Strike(Score.from(0), totalScore);
    }
}
