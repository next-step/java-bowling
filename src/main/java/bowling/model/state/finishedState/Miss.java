package bowling.model.state.finishedState;

import bowling.model.Score;

public class Miss extends FinishedState {
    private static final String MISS_TOTAL_SCORE_ERROR = "Miss의 조건에 맞지 않습니다.";

    private Miss(Score previous, Score totalScore) {
        super(previous, totalScore);
        this.expression = previous.toString();
    }

    public static Miss of(Score previous, Score totalScore) {
        if (totalScore.isMaxScore() || previous.isMaxScore()) {
            throw new IllegalArgumentException(MISS_TOTAL_SCORE_ERROR);
        }

        return new Miss(previous, totalScore);
    }

    @Override
    public String toString() {
        Score now = score.subtraction(previousScore);
        return now.toString();
    }
}
