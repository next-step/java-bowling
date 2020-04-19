package bowling.domain.score;

import bowling.exception.BowlingException;

public class ScoreCalculator implements Calculator {

    private static final String ADD_COUNT_ERR_MESSAGE = "0~2회 까지만 점수 합산이 가능 합니다.";
    private static final int MIN_ADD_COUNT = 0;
    private static final int MAX_ADD_COUNT = 2;


    private final Score score;
    private final int addCount;

    public ScoreCalculator(Score score, int addCount) {
        validateAddCount(addCount);
        this.score = score;
        this.addCount = addCount;
    }

    private void validateAddCount(int addCount) {
        if (addCount < MIN_ADD_COUNT || addCount > MAX_ADD_COUNT) {
            throw new BowlingException(ADD_COUNT_ERR_MESSAGE);
        }
    }

    public boolean canAddNextScore() {
        return addCount != 0;
    }

    public Score getScore() {
        return score;
    }
}
