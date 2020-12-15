package bowling.model.state.finishedState;

import bowling.model.Score;

public class Spare extends FinishedState {
    private static final String SPARE_TOTAL_SCORE_ERROR = "Spare의 조건에 맞지 않습니다.";
    private static final String EXPRESSION = "/";

    private Spare(Score previous, Score score) {
        super(previous, score);
        this.expression = EXPRESSION;
    }

    public static Spare of(Score previous, Score totalScore) {
        if (!totalScore.isMaxScore() || previous.isMaxScore()) {
            throw new IllegalArgumentException(SPARE_TOTAL_SCORE_ERROR);
        }
        return new Spare(previous, totalScore);
    }
}
