package bowling.domain;

import bowling.exception.CannotCalculateException;

public class Score {

    private static final int NONE_OPPORTUNITY = 0;
    private static final int MAX_SCORE = 10;
    private static final int SPARE_OPPORTUNITY = 1;
    private static final int STRIKE_OPPORTUNITY = 2;
    private static final String CANNOT_CALCULATE_MESSAGE = "앞 투구가 끝나지 않아 계산 할 수 없습니다.";

    private int score;
    private int leftOpportunity;

    private Score(int score, int leftOpportunity) {
        this.score = score;
        this.leftOpportunity = leftOpportunity;
    }

    public static Score ofNone(int score) {
        return new Score(score, NONE_OPPORTUNITY);
    }

    public static Score ofSpare() {
        return new Score(MAX_SCORE, SPARE_OPPORTUNITY);
    }

    public static Score ofStrike() {
        return new Score(MAX_SCORE, STRIKE_OPPORTUNITY);
    }

    public int calculateScore() {
        if (!canCalculateScore()) {
            throw new CannotCalculateException(CANNOT_CALCULATE_MESSAGE);
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return leftOpportunity == NONE_OPPORTUNITY;
    }

    public void throwBall(int score) {
        if (this.leftOpportunity > NONE_OPPORTUNITY) {
            this.score += score;
            this.leftOpportunity -= 1;
        }
    }
}
