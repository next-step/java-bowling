package bowling.domain;

import bowling.exception.CannotCalculateException;

public class Score {

    private static final String CANNOT_CALCULATE_MESSAGE = "앞 투구가 끝나지 않아 계산 할 수 없습니다.";

    private int score;
    private ScoreState scoreState;

    private Score(int score, ScoreState scoreState) {
        this.score = score;
        this.scoreState = scoreState;
    }

    public static Score of(int score, ScoreState scoreState) {
        return new Score(score, scoreState);
    }

    public int calculateScore() {
        if (!scoreState.canCalculate()) {
            throw new CannotCalculateException(CANNOT_CALCULATE_MESSAGE);
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return scoreState.canCalculate();
    }

    public void addAdditionalScore(int score) {
        if (!scoreState.canCalculate()) {
            this.score += score;
            this.scoreState = this.scoreState.changeState();
        }
    }
}
