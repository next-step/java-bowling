package bowling.model.state.finishedState;

import bowling.model.Score;
import bowling.model.state.State;

public class Spare extends FinishedState {
    private static final String SPARE_TOTAL_SCORE_ERROR = "Spare의 조건에 맞지 않습니다.";
    private static final String EXPRESSION = "/";

    private final Score totalScore;

    private Spare(Score score, Score totalScore) {
        super(score);
        this.expression = EXPRESSION;
        this.totalScore = totalScore;
    }

    public static State of(Score score, Score totalScore) {
        if (!totalScore.isMaxScore() || score.isMaxScore()) {
            throw new IllegalArgumentException(SPARE_TOTAL_SCORE_ERROR);
        }
        return new Spare(score, totalScore);
    }

    @Override
    public boolean isMaxScore() {
        return totalScore.isMaxScore();
    }
}
