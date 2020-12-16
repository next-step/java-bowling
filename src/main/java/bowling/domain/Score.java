package bowling.domain;

import bowling.exception.ScorePointRangeOutBoundException;

public class Score {

    private static final int MIN_SCORE_COUNT = 0;
    private static final int MAX_SCORE_COUNT = 10;

    private final int point;

    private Score(int point) {
        this.point = point;
    }

    public static Score stringOf(String point) {
        return valueOf(Integer.parseInt(point));
    }

    private static Score valueOf(int point) {
        validPoint(point);
        return new Score(point);
    }

    private static void validPoint(int point) {
        if (point < MIN_SCORE_COUNT || point > MAX_SCORE_COUNT) {
            throw new ScorePointRangeOutBoundException();
        }
    }

    public Score sumScore(int value) {
        return valueOf(point + value);
    }

}
