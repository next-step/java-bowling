package bowling.model.state.finishedState;

import bowling.model.Score;

public class Miss extends FinishedState {
    private static final String MISS_TOTAL_SCORE_ERROR = "Miss의 조건에 맞지 않습니다.";

    private Miss(Score score) {
        super(score);
    }

    public static Miss from(Score score) {
        if (score.isMaxScore()) {
            throw new IllegalArgumentException(MISS_TOTAL_SCORE_ERROR);
        }
        return new Miss(score);
    }

    @Override
    public String toString() {
        return score.toString();
    }
}
